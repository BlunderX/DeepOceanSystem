<template>
<el-main class="el-main">
  <el-scrollbar class="el-scrollbar-demo">
    <el-page-header @back="this.$router.back(-1)">
      <template #content>
        <span class="text-large font-600 mr-3"> 用户组配置项 </span>
      </template>
    </el-page-header>
    <el-divider></el-divider>
    <router-view></router-view>
  </el-scrollbar>
</el-main>
</template>
<script setup lang="ts">

function getlist(){

}
</script>

<style scoped>
.el-main{
  height: 95%;
  width: 90%;
  margin: 1%;
  padding: 1%;
  border: 0;
  border-radius: 15px;
  background-color: #ffffff;
}
.el-scrollbar-demo{
  /*height: 88vh;*/
  height: 100%;
  width: 100%;
}
</style>