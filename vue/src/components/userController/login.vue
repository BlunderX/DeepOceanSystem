<template>
  <h1>登录到DeepOS</h1>
  <div class="form-el">
    <el-form :model="form" :rules="rules" ref="formREF">
      <el-form-item label="账户" prop="id">
        <el-input v-model="form.id" placeholder="输入邮件地址" size="large" :disabled="isDisabled"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" placeholder="输入密码" type="password" size="large" :disabled="isDisabled"></el-input>
      </el-form-item>
    </el-form>
  </div>
  <div class="functions">
    <el-button type="success" size="large" style="margin-right: 25%" @click="login" :loading="isLoading">登录</el-button>
    <el-button type="primary" size="large" @click="reg">注册</el-button>
  </div>
</template>

<script setup lang="javascript">
import {onMounted, reactive, ref} from "vue";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";
import {sleep} from "@/global/global.js"

const isDisabled = ref(false);
const isLoading = ref(false);
const toggleisDisabled = () =>{
  isDisabled.value = !isDisabled.value;
  isLoading.value = !isLoading.value;
}
const back = () =>{
  router.push("/");
}
const form = reactive({
  id:'',
  password:''
})
const reg = () =>{
  router.push('register');
}
const formREF = ref(null);
const rules = {
  id: [
    { required: true, message: "用户名不能为空", trigger: "blur" },
    { min: 4,message: "邮件长度需在至少4位", trigger: "blur" },
    { pattern: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, message: "应为常见的邮件格式", trigger: "blur" }
  ],
  password: [
    { required: true, message: "密码不能为空", trigger: "blur" },
    { min: 6, message: "密码长度不能少于6位", trigger: "blur" },
    { pattern: /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d!@#$%^&*()_+{}:"<>?~`|\\[\]';.,/=-]{6,16}$/, message: "密码必须包含字母和数字和!@#$%^&*(),且所有类型至少有一个字符存在", trigger: "blur" }
]}
export default{
  data(){
    return {
      form,
      rules,
      isDisabled,
      isLoading,
      login,
      reg,
      toggleisDisabled
    }
  },
}
async function login(){
  toggleisDisabled()

  let regexID = rules.id[2].pattern
  let regexPwd = rules.password[2].pattern
  regexID.test(form.id)
  if(regexID.test(form.id) && regexPwd.test(form.password)){
    const res = await fetch("/api/login",{
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: form.id,
        password: form.password,
      })
    })
    console.log(await res.text())
    // localStorage.setItem(token, await res.text())
  }else {
    ElMessage.error({
      message:'验证失败,请检查红框内容'
    })
  }
  await sleep(1000)
  toggleisDisabled()
}
</script>

<style scoped>
h1 {
  margin-bottom: 15%;
}
.form-el {
  width: 85%;
}
.functions {
  margin-top: 15%;
  width: 85%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>