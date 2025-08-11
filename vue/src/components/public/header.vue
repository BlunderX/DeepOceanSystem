<template>
  <el-menu mode="horizontal" :ellipsis="false">
    <el-menu-item v-for="item in menuList" v-bind:index="item.index" v-show="!(item.ischild)" @click="goto(item.url)">
      <el-icon>
        <component :is="item.component"></component>
      </el-icon>
      {{item.name}}
    </el-menu-item>
    <el-sub-menu v-for="subitem in menuList" v-bind:index="subitem.index" v-show="subitem.ischild">
      <template #title>
        <el-icon>
          <component :is="subitem.component"></component>
        </el-icon>
        {{ subitem.name }}</template>

      <el-menu-item v-for="child in subitem.child" v-bind:index="child.index" @click="goto(child.url)">
        <el-icon>
          <component :is="subitem.component"></component>
        </el-icon>
        {{child.name}}
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>
<script setup lang="ts">
import {onMounted, ref} from "vue";
import router from "@/router/index.js";
import {getMenu, urls} from "@/global/global.js"

const menuList = ref([])
const goto = (url) => {
  router.push(url);
}
onMounted(() =>{
  setMenu()
})
function setMenu(){
  let params = {
    id:2
  }
  const menu = getMenu(urls.getHeader,params)
  menuList.value = menu.data
}
</script>
<style scoped>
.el-menu--horizontal {
  background: #fff;
  height: 8vh;
}
.el-menu--horizontal > .el-menu-item:first-of-type {
  margin-right: auto;
}
</style>