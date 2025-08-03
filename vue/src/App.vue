<script setup>

</script>

<template>
  <div id="app" >
    <router-view name="header"></router-view>
    <router-view></router-view>
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
  background-color: #2c3e50;
}
</style>
