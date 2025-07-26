<template>
  <h1>登录到DeepOS</h1>
  <div class="form-el">
    <el-form :model="form">
      <el-form-item label="账户">
        <el-input v-model="form.id" placeholder="输入邮件地址" size="large"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" placeholder="输入密码" type="password" size="large"></el-input>
      </el-form-item>
    </el-form>
  </div>
  <div class="functions">
        <el-button type="success" size="large" style="margin-right: 25%" @click="login">登录</el-button>
        <el-button type="primary" size="large" @click="register">注册</el-button>
  </div>
</template>
<script setup lang="javascript">
import {reactive} from "vue";
import router from "@/router/index.js";
const register = () => {
  router.push("register");
}
const form = reactive({
  id:'',
  password:'',
})
export default {
  data() {
    return {
      form,
      register,
      login
    }
  },
  setup(){

  }
}
async function login() {
  const res = await fetch("/api/login", {
    method: "POST",
    headers:{
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email:form.id,
      password:form.password
    })
  })
  console.log(res)
  const token = await res.text()
  console.log(token)
  // localStorage.setItem("token", token)

}
</script>
<style scoped>
h1{
  margin-bottom: 15%;
}
 .form-el{
   width: 85%;

 }
 .functions{
   margin-top: 15%;
   width: 85%;
   display: flex;
   align-items: center;
   justify-content: center;
 }
</style>