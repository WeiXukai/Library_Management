<template>
  <div class="recommend" style="padding: 10px">

    <!-- 推荐方式切换 -->
    <div style="margin-bottom: 16px;">
      <el-radio-group v-model="recommendType" @change="load">
        <el-radio-button label="hot">热门推荐</el-radio-button>
        <el-radio-button label="cf">协同过滤推荐</el-radio-button>
      </el-radio-group>
    </div>

    <!-- 查询部分（可选：只对热门推荐开放搜索） -->
    <div style="margin: 10px 0;" v-if="recommendType === 'hot'">
      <el-form :inline="true" size="small">
        <el-form-item label="图书名称">
          <el-input v-model="searchName" placeholder="输入图书名称" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="mini" @click="load">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="mini" type="danger" @click="clear">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 推荐图书数据表格 -->
    <el-table :data="recommendData" stripe border :default-sort="{prop: 'borrowCount', order: 'descending'}">
      <el-table-column prop="isbn" label="图书编号" sortable />
      <el-table-column prop="title" label="图书名称" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="publisher" label="出版社" />
      <el-table-column
        label="推荐指数"
        :sortable="true"
        :formatter="recommendIndexFormatter"
      />
    </el-table>

    <!-- 分页 -->
    <div style="margin: 10px 0">
      <el-pagination
        v-model:currentPage="currentPage"
        :page-sizes="[5, 10, 12]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>

  </div>
</template>

<script>
import request from "../utils/request";
import { ElMessage } from "element-plus";
import moment from "moment";
import router from "@/router";

export default {
  name: "Recommend",
  data() {
    return {
      user: {},
      searchName: "",
      recommendType: "hot",
      recommendData: [],
      total: 0,
      currentPage: 1,
      pageSize: 5,
    };
  },
  created() {
    // 首先检查用户是否已经登录
    let userJson = sessionStorage.getItem("user");
    if(!userJson) {
      router.push("/login");
    } else {
      this.user = JSON.parse(userJson);
    }
    // 初始化加载推荐数据
    this.load();
  },
  methods: {
    /**
     * 加载推荐图书数据
     * 当searchName不为空时，会带上查询条件
     */
    load() {
      let params = {
        type: this.recommendType,
        pageNum: this.currentPage,
        pageSize: this.pageSize,
      };
      if (this.recommendType === "cf" && this.user.id) {
        params.userId = this.user.id;
      }
      if (this.recommendType === "hot" && this.searchName) {
        params.name = this.searchName;
      }
      request.get("/recommend", { params })
        .then((res) => {
          if (res.code === "0") {
            this.recommendData = res.data.records;
            this.total = res.data.total;
          } else {
            ElMessage.error(res.msg || "加载推荐数据失败");
          }
        })
        .catch((err) => {
          console.error(err);
          ElMessage.error("获取推荐数据时出现异常");
        });
    },

    /**
     * 清空搜索条件并重新加载数据
     */
    clear() {
      this.searchName = "";
      this.load();
    },

    /**
     * 分页 - 改变每页显示条数
     */
    handleSizeChange(size) {
      this.pageSize = size;
      this.load();
    },

    /**
     * 分页 - 改变当前页
     */
    handleCurrentChange(page) {
      this.currentPage = page;
      this.load();
    },

    /**
     * 推荐指数格式化：协同过滤推荐显示recommendIndex，热门推荐显示borrowCount
     */
    recommendIndexFormatter(row) {
      if (this.recommendType === 'cf') {
        return row.recommendIndex !== undefined && row.recommendIndex !== null
          ? Number(row.recommendIndex).toFixed(2)
          : '-';
      } else {
        return row.borrowCount !== undefined && row.borrowCount !== null
          ? row.borrowCount
          : '-';
      }
    },
  },
};
</script>

<style scoped>
/* 若有需要，可在此定义局部样式 */
</style>
