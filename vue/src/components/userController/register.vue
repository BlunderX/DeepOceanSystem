<template>
  <h1>注册到DeepOS</h1>
  <div class="form-el">
    <el-form :model="form" :rules="rules" ref="formREF">
      <el-form-item label="邮件地址" prop="email">
        <el-input v-model="form.email" placeholder="邮件地址" size="large" maxlength="32" clearable :disabled="isDisabled"></el-input>
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input v-model="form.phone" placeholder="手机号码" size="large" maxlength="11" clearable :disabled="isDisabled"></el-input>
      </el-form-item>
      <el-form-item label="你的密码" prop="password">
        <el-input v-model="form.password" placeholder="你的密码" type="password" size="large" clearable :disabled="isDisabled"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="repassword">
        <el-input v-model="form.repassword" placeholder="确认密码" type="password" size="large" clearable :disabled="isDisabled"></el-input>
      </el-form-item>
    </el-form>
  </div>
  <div class="functions">
    <el-button type="success" size="large" style="margin-right: 25%" @click="reg" :loading="isLoading">确认注册</el-button>
    <el-button type="primary" size="large" @click="login">返回登录</el-button>
  </div>
</template>

<script setup lang="javascript">
import { reactive, ref } from 'vue';
import router from '@/router/index.js';
import { ElMessage } from 'element-plus';
import {el} from "element-plus/es/locale/index";
import {sleep} from "@/global/global.js";


const formREF = ref(null);
const isDisabled = ref(false);
const isLoading = ref(false);
const email = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const phone = /^[1-9]\d{10}$/; // 修正手机号码正则表达式
const password = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d!@#$%^&*()_+{}:"<>?~`|\\[\]';.,/=-]{6,16}$/;

const rules = reactive({
  email: [
    { required: true, message: '请输入邮件地址', trigger: 'blur' },
    { type: 'email', message: '请输入有效的邮件地址', trigger: 'blur' },
  ],
  phone: [
    { required: true, message: '请输入手机号码', trigger: 'blur' },
    { pattern: phone, message: '请输入有效的手机号码', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 8, message: '密码长度不能少于 8 位', trigger: 'blur' },
    { pattern: password, message: '密码必须包含大小写字母、数字和特殊字符', trigger: 'blur' }
  ],
  repassword: [
    {validator: (rule,value,callback) =>{
      value !== form.password ? callback('两次密码输入密码不相符') : callback();
      },trigger:'blur'},
    { required: true, message: '请再次输入密码', trigger: 'blur' },
  ]
});
const toggleisDisabled = () =>{
  isDisabled.value = !isDisabled.value;
  isLoading.value = !isLoading.value;
}
const form = reactive({
  email: '',
  phone: '',
  password: '',
  repassword: ''
});
const login = () => {
  router.push('login');
};
export default {
  data(){
    return {
      form,
      rules,
      phone,
      password,
      isDisabled,
      isLoading,
      login,
      reg,
      toggleisDisabled,
    }
  }
}
async function reg(){
  toggleisDisabled()
  if (email.test(form.email) && phone.test(form.phone) && password.test(form.repassword)){
    const res = await fetch("/api/register",{
      method: 'post',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: form.email,
        phone: form.phone,
        password: form.password,
      })
    })
    console.log(res)
    ElMessage.success({
      message:'注册成功'
    })
  }else {
    ElMessage.error({
      message:'验证失败，请检查红框内容！'
    })
  }
  await sleep(1000);
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