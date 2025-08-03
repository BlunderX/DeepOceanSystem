<template>
  <el-menu mode="horizontal">
    <el-menu-item v-for="item in menuList" v-bind:index="item.index" v-show="!(item.ischild)">
      <el-icon>
        <component :is="item.component"></component>
      </el-icon>
      {{item.name}}
    </el-menu-item>
    <el-sub-menu v-for="subitem in menuList" v-bind:index="subitem.index" v-show="subitem.ischild">

      <template #title>{{ subitem.name }}</template>
      <el-menu-item v-for="child in subitem.child" v-bind:index="child.index" @click="goto(child.url)">
        {{child.name}}
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>
<script setup lang="javascript">
import {reactive, ref} from "vue";
import router from "@/router/index.js";

const menuList = ref([])
const goto = (url) => {
  router.push(url);
}
export default {
  data() {
    return {
      menuList,
      goto
    }
  },
  mounted() {
    setMenu()
  }

}
export function setMenu(){
  console.log("setMenu");
  const menu = [{
    index: "1",
    name:"主页",
    component:"House",
    url:"null",//null,goto,function
    ischild: false,
    child:[]
  },{
    index: "2",
    name:"用户",
    component:"User",
    url:"null",//当ischild为true时，func则失效
    ischild: true,
    child:[{
      index: "2-2",
      name:"登录",
      url:"/user/login",
    },{
      index: "2-1",//子编号不可与父编号存在冲突
      name:"注册",
      url:"/user/register",
    }]
  }]
  menuList.value = menu
}
</script>
<style scoped>
.el-menu--horizontal > .el-menu-item:first-of-type {
  margin-right: 75% ;
}
</style>