<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px);"
        :default-active="path"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        router
        background-color="#30333c"
        text-color="#fff"
    >

      <!-- 新增：推荐图书（放在数据可视化上方）-->
      <el-menu-item index="/recommend">
        <!-- 这里的图标 use xlink:href="#iconbook " 只是示例，可使用项目中已有的其他图标 -->
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#iconbook "></use>
        </svg>
        <span>推荐图书</span>
      </el-menu-item>

      <!-- 原有：数据可视化 -->
      <el-menu-item index="/dashboard">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icondashboard "></use>
        </svg>
        <span>数据可视化</span>
      </el-menu-item>

      <el-sub-menu index="2" text-color="#fff">
        <template #title>
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-mingpian "></use>
          </svg>
          <span>个人信息</span>
        </template>
        <el-menu-item index="/person" style="font-color: white">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-a-bianji1 "></use>
          </svg>
          <span>修改个人信息</span>
        </el-menu-item>
        <el-menu-item index="/password">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-mima "></use>
          </svg>
          <span>修改密码</span>
        </el-menu-item>
      </el-sub-menu>

      <el-menu-item index="/user" v-if="user.role == 1">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#iconreader "></use>
        </svg>
        <span>读者管理</span>
      </el-menu-item>

      <el-menu-item index="/book" v-if="user.role == 1">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#iconbook "></use>
        </svg>
        <span>书籍管理</span>
      </el-menu-item>

      <el-menu-item index="/book" v-if="user.role == 2">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#iconbook "></use>
        </svg>
        <span>图书查询</span>
      </el-menu-item>

      <el-menu-item index="/bookwithuser">
        <el-icon><grid /></el-icon>
        <span>借阅历史</span>
      </el-menu-item>

    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  components: {},
  created() {
    let userStr = sessionStorage.getItem("user") || "{}";
    this.user = JSON.parse(userStr);
  },
  data() {
    return {
      user: {},
      path: this.$route.path
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      // 可以在此处写一些切换菜单展开时的逻辑
    },
    handleClose(key, keyPath) {
      // 可以在此处写一些切换菜单收起时的逻辑
    }
  }
};
</script>

<style scoped>
.icon {
  width: 30px;
  height: 30px;
  padding-top: 5px;
  padding-right: 10px;
}
</style>
