<template>
  <div class="container">

      <el-scrollbar>
        <div class="page">
          <el-button type="success" >添加对象</el-button>
          <el-table :data="form">
            <el-table-column prop="id" label="用户组ID"></el-table-column>
            <el-table-column prop="name" label="名称"></el-table-column>
            <el-table-column prop="despriction" label="描述"></el-table-column>
            <el-table-column label="功能区">
              <template #default="scope">
                <el-button type="primary" @click="editObject(scope.row)">查看/编辑对象</el-button>
                <el-button type="danger">删除对象</el-button>
              </template>

            </el-table-column>
          </el-table>
        </div>
      </el-scrollbar>
    <el-dialog v-model="viewObject" width="70%">
      <template v-slot:header="{close,title}">
        <h3 v-bind="title">查看：{{viewTitle}}</h3>
        <p style="color: red;font-size: 15px">没有提交之前任何更改都不会被保存</p>
      </template>
      <el-button type="success" @click="viewAddObject = true">添加对象</el-button>
      <el-table :data="viewObjectData">
        <el-table-column prop="index" label="等级"></el-table-column>
        <el-table-column prop="name" label="标题"></el-table-column>
        <el-table-column prop="url" label="链接地址"></el-table-column>
        <el-table-column prop="component" label="图标"></el-table-column>
        <el-table-column prop="ischild" label="子页面"></el-table-column>
        <el-table-column label="功能区">
          <template #default="scope">
            <el-button type="primary">编辑</el-button>
            <el-button type="danger">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <template v-slot:footer="{close,title}">
        <el-button type="danger">提交</el-button>
        <el-button type="primary">返回(不保存)</el-button>
      </template>
    </el-dialog>
    <el-dialog v-model="viewAddObject">
      <template v-slot:header="{close,title}">
        <h3 v-bind="title">添加新的对象</h3>
      </template>
      <el-form :model="addObject" :rules="addObjectRules">
        <el-form-item prop="isChild" label="是否有二级菜单">
          <el-radio-group v-model="addObject.ischild">
            <el-radio :value="true" label="存在(true)"></el-radio>
            <el-radio :value="false" label="不存在(false)"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="name" label="名称">
          <el-input type="text" v-model="addObject.name" placeholder="必填，输入名称" clearable></el-input>
        </el-form-item>
        <el-form-item prop="icon" label="图标">
          <IconSelectorBT v-model="icon"/>
        </el-form-item>
        <el-form-item prop="index" label="等级">
          <el-input type="text" v-model="addObject.index" placeholder="必填，接受一个数字,表示在页面的顺序,1最大也最靠前，最大为99" maxlength="2" clearable></el-input>
        </el-form-item>
        <el-form-item prop="url" label="访问地址">
          <el-input type="text" v-model="addObject.url" placeholder="可选，输入有效的访问地址，没有则留空,当存在二级菜单时自动禁用" :disabled="addObject.ischild" clearable></el-input>
        </el-form-item>
        <el-form-item prop="despriction" label="描述">
          <el-input type="textarea" v-model="addObject.despriction" placeholder="必填，输入描述" clearable autosize></el-input>
        </el-form-item>
      </el-form>
      <template v-slot:footer="{close,title}">
        <el-button type="success" @click="addObjFunction">提交</el-button>
        <el-button type="danger" @click="viewAddObject = false">返回(不保存)</el-button>
      </template>
    </el-dialog>
  </div>
</template>
<script setup lang="ts">
import {onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import IconSelectorBT from '@/components/public/iconSelector.vue'

const viewTitle = ref("")
const form = ref([]);//服务器拉取的全部对象数据
const icon = ref();

const viewObjectData = ref([])//header的对象
const viewObjectAside = ref([])//侧边栏

const addObject = ref({})//为header添加对象使用的对象

const viewObject = ref(false);//查看组内对象的页面开关对象
const viewAddObject = ref(false);//组内对象添加对象页面的开关对象

//添加用户页面表单规则
const addObjectRules = {
  isChild:[{
    required: true,
    message:"必选",
    trigger:"blur"
  }],
  name:[{
    required: true,
    message:"名称不得为空",
    trigger:"blur"
  }],
  index:[{
    required: true,
    message:"等级不得为空",
    trigger:"blur"
  },
    {
      pattern:/^\d*$/,
      message: "必须是一个有效数字",
      trigger: "blur"
    }],
  despriction: [{
    required: true,
    message:"描述不得为空",
    trigger:"blur"
  }]
}

function editObject(obj :object) {
  viewTitle.value = obj.name;
  viewObjectData.value=JSON.parse(obj.data);
  viewObject.value = true
}
function addObjFunction(){
  console.log("addObjFunction");
  let values = Object.values(addObject.value)

  for(let i=0;i<viewObjectData.value.length;i++){
    if(viewObjectData.value[i].index == addObject.value.index){
      ElMessage.error("等级值不可以相同，请修改")
    } else {
      let str = /^\d*$/
      if(!(str.test(addObject.value.index))){
        ElMessage.error("等级必须是一个数字")
        break;
      }
    }
  }

  if(values.length < 4){
    ElMessage.error("表单审查不通过，请检查是否有未填项")
  } else {
    for(let i=0;i<values.length;i++){
      if(values[i] == null || values[i] === ''){
        ElMessage.error("表单审查不通过，存在空字符串或null")
        break;
      }
    }
    if (addObject.value.isChild){
      addObject.value.push({"child":[]})
    }
    viewObjectData.value.push(addObject.value);
    console.log(viewObjectData.value)
    viewAddObject.value = false;
    ElMessage.success("添加成功")
    addObject.value = null;
  }

}

onMounted(() =>{
  getAllGroups()
})
async function getAllGroups() {
  const res = await fetch("/api/admin/groups/getall",{
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    }
  });
  const data = await res.text();
  form.value = JSON.parse(data);
}
</script>
<style scoped>
</style>