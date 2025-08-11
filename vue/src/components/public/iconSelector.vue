
<template>
  <div id="IconSelector">
    <el-button type="default" @click="viewDialogs = true">选择图标</el-button>

    <el-dialog v-model="viewDialogs"
                width="85%"
               :show-close="false">
      <template #header="{ close,titleId,titleClass }">
        <div class="title-div">
          <h1 :id="titleId" :class="titleClass">图标选择器</h1>
          <div>
            <el-button type="success">commit</el-button>
            <el-button type="danger" @click="close">close</el-button>
          </div>
        </div>
      </template>
      <el-input type="text" placeholder="在这里可以检索需要的图标" style="width: 250px"></el-input>
      <div class="icons">
        <template class="icon-list" v-for="(icons,name,i) in AllIcons">
          <div class="iconContainer" @click="ElMessage.primary('selected '+ name)">
            <div class="icon">
              <component :is="icons" class="icon-table"></component>
            </div>
            {{name}}
          </div>
        </template>
      </div>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import * as ElementIcons from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";

const viewDialogs = ref(false);

const AllIcons = ref(ElementIcons);

const icon = defineModel('iconSelect')
</script>
<style scoped>
.title-div{
  display: flex;
  justify-items: right;
}
.icons{
  display: grid;
  grid-template-columns: repeat(8,1fr);
  text-align: center;
  grid-gap: unset;

  .iconContainer{
    display: flex;
    flex-direction: column;
    align-items: center;
    height: auto;
    width: 100%;
    transition: background-color 0.5s;
    .icon{
      width: 25%;
    }
  }
  .iconContainer:hover{
    background-color: rgba(0, 200, 255, 0.75);
    transition: background-color 0.5s;
  }
}

</style>