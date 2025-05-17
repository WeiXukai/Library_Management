<template>
  <div class="home" style ="padding: 10px">
    <!-- 搜索-->
    <div style="margin: 10px 0;">
      <el-form inline="true" size="small" >
        <el-form-item label="图书编号" >
          <el-input v-model="searchBookId" placeholder="请输入图书编号"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search/></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="用户编号" v-if="user.role == 1">
          <el-input v-model="searchUserId" placeholder="请输入用户编号"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="图书名称">
          <el-input v-model="searchBookTitle" placeholder="请输入图书名称" clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="onlyUnreturned">只看未归还</el-checkbox>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="margin-left: 1%" @click="load" size="mini">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="mini"  type="danger" @click="clear">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 按钮-->
    <div style="margin: 10px 0;" >
      <!-- 批量归还暂不支持 -->
    </div>
    <!-- 数据字段-->
    <el-table :data="tableData" stripe border="true" @selection-change="handleSelectionChange">
      <el-table-column v-if="user.role ==1"
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="isbn" label="图书编号" sortable />
      <el-table-column prop="title" label="图书名称" />
      <el-table-column prop="userId" label="用户编号" sortable />
      <el-table-column prop="borrowTime" label="借阅时间">
        <template #default="scope">
          {{ formatDate(scope.row.borrowTime) }}
        </template>
      </el-table-column>
      <el-table-column prop="dueTime" label="应还时间">
        <template #default="scope">
          {{ formatDate(scope.row.dueTime) }}
        </template>
      </el-table-column>
      <el-table-column prop="returnTime" label="归还时间">
        <template #default="scope">
          {{ formatDate(scope.row.returnTime) }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status == 0" type="warning">借出</el-tag>
          <el-tag v-else type="success">已归还</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" >
        <template v-slot="scope">
          <el-popconfirm title="确认归还?" @confirm="handleReturn(scope.row)" v-if="scope.row.status == 0">
            <template #reference>
              <el-button type="danger" size="mini" >归还</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--    分页-->
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import request from "../utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";
export default {
  created(){
    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)
    let userJson = sessionStorage.getItem("user")
    if(!userJson)
    {
      router.push("/login")
    }
    this.load()
  },
  name: 'bookwithuser',
  methods: {
    handleSelectionChange(val){
      this.forms = val
    },
    load(){
      if(this.user.role == 1){
        // 管理员查询所有借阅记录
        request.get("/borrow/allBorrowRecords",{
          params:{
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            searchUser: this.searchUserId, // 用户编号
            searchBook: this.searchBookId,  // 图书编号（ISBN）
            searchTitle: this.searchBookTitle, // 图书名称
            onlyUnreturned: this.onlyUnreturned
          }
        }).then(res =>{
          this.tableData = res.data.records
          this.total = res.data.total
        })
      }else{
        // 普通用户查询自己的借阅历史，支持isbn和title搜索
        request.get("/borrow/myBorrowHistory",{
          params:{ userId: this.user.id, isbn: this.searchBookId, title: this.searchBookTitle, onlyUnreturned: this.onlyUnreturned }
        }).then(res =>{
          this.tableData = res.data
          this.total = res.data.length
        })
      }
    },
    clear(){
      this.searchBookId = ""
      this.searchUserId = ""
      this.searchBookTitle = ""
      this.onlyUnreturned = false
      this.load()
    },
    handleReturn(row){
      request.post("/borrow/returnBook",{
        userId: row.userId,
        bookId: row.bookId
      }).then(res =>{
        if(res.code == 0 ){
          ElMessage.success("归还成功");
          this.load();
        }
        else {
          ElMessage.error(res.msg)
        }
      })
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum
      this.load()
    },
    formatDate(val) {
      if (!val) return '';
      const date = new Date(val);
      const pad = n => n < 10 ? '0' + n : n;
      return `${date.getFullYear()}-${pad(date.getMonth()+1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
    },
  },
  data() {
    return {
      searchBookId: '',
      searchUserId: '',
      searchBookTitle: '',
      onlyUnreturned: false,
      total:10,
      currentPage:1,
      pageSize: 10,
      tableData: [],
      user:{},
      forms:[],
    }
  },
}
</script>
