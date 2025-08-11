<template>
<el-aside width="15vw">
  <el-scrollbar height="92vh">
    <el-menu
        mode="vertical"
        class="el-menu-vertical-demo"
        >
      <el-menu-item v-for="item in asideList" v-bind:index="item.index" v-show="!(item.ischild)" @click="goto(item.url)">
        <el-icon>
          <component :is="item.component"></component>
        </el-icon>
        {{item.name}}
      </el-menu-item>
      <el-sub-menu v-for="subitem in asideList" v-bind:index="subitem.index" v-show="subitem.ischild" class="el-sub-menu--aside">
        <template #title>
          <el-icon>
            <component :is="subitem.component"></component>
          </el-icon>
          {{ subitem.name }}</template>

        <el-menu-item v-for="child in subitem.child" v-bind:index="child.index" @click="goto(child.url)">
          <el-icon>
            <component :is="child.component"></component>
          </el-icon>
          {{child.name}}
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </el-scrollbar>
</el-aside>
</template>
<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {goto} from '@/global/global.js'
import {getMenu, urls} from "@/global/global.js"

const router = useRouter()
const asideList = ref([])
onMounted(() => {
  getAside()
})
function getAside(){
  const params = {
    id:2
  }
  const aside = getMenu(urls.getHeader,params)
  asideList.value = aside.aside
}
</script>

<style scoped>
.el-menu-vertical-demo {
  width: 15vw;
  height: 92vh;
  border: 0;
}
</style>