<script setup>

</script>

<template>
  <div id="app" >
    <router-view name="header"></router-view>
    <el-container class="AppContainer">
      <router-view name="aside"></router-view>
      <router-view></router-view>

    </el-container>
    <router-view name="footer"></router-view>

  </div>
</template>

<style>
*{
  margin: 0;
  padding: 0;
}
#app{
  height: 100vh;
  width: 100vw;
  background-color: #ececec;
}
.AppContainer{
  height: 92vh;
  width: 100%;
}
</style>
