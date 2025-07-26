<template>
  <h1>注册到DeepOS</h1>
  <div class="form-el">
    <el-form :model="form">
      <el-form-item label="邮件地址">
        <el-input v-model="form.email" placeholder="邮件地址" size="large" clearable></el-input>
      </el-form-item>
      <el-form-item label="手机号码">
        <el-input v-model="form.phone" placeholder="手机号码" size="large" clearable></el-input>
      </el-form-item>
      <el-form-item label="你的密码">
        <el-input v-model="form.password" placeholder="你的密码" type="password" size="large" clearable></el-input>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input v-model="form.repassword" placeholder="你的密码" type="password" size="large" clearable></el-input>
      </el-form-item>
    </el-form>
  </div>
  <div class="functions">
    <el-button type="success" size="large" style="margin-right: 25%" @click="register">确认注册</el-button>
    <el-button type="primary" size="large" @click="login">返回登录</el-button>
  </div>
</template>


<script setup lang="javascript">
import {reactive} from "vue";
import router from "@/router/index.js";
const login = () => {
  router.push("login");
}
const form = reactive({
  email:'',
  phone:'',
  password:'',
  repassword:'',
})
export default {
  data() {
    return {
      form,
      login,
      register
    }
  },
  setup(){
  }
}
async function register() {
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