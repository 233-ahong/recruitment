<template>
  <div class="login">
    <el-form
      ref="loginRef"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
    >
      <h3 class="title">招聘后台管理系统</h3>
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          type="text"
          size="large"
          auto-complete="off"
          placeholder="请输入账号"
        >
          <template #prefix>
            <svg-icon icon-class="user" class="el-input__icon input-icon" />
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          size="large"
          auto-complete="off"
          placeholder="请输入密码"
        >
          <template #prefix
            ><svg-icon icon-class="password" class="el-input__icon input-icon"
          /></template>
        </el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button
          :loading="loading"
          size="large"
          type="primary"
          style="width: 100%"
        >
          <span v-if="!loading" @click="handleLogin">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { getCurrentInstance, ref } from 'vue'
import { setToken } from '@/utils/auth.js'
import { getRouters, login } from '@/api/user.js'
import useSideStore from '@/store/modules/sideBar'
import router from '@/router/index.js'
const { proxy } = getCurrentInstance()
const sideStore = useSideStore();

const loading = ref(false)
const loginForm = ref({
  username: 'admin',
  password: 'admin123',
  captchaOutput: '',
  genTime: '',
  lotNumber: '',
  passToken: '',
})
const loginRules = ref({
  username: [{ required: true, trigger: 'blur', message: '请输入您的账号' }],
  password: [{ required: true, trigger: 'blur', message: '请输入您的密码' }],
})
function handleLogin() {
  proxy.$refs.loginRef.validate((valid) => {
    if (valid) {
      loading.value = true
      initGeetest4(
        {
          captchaId: '58ebeae93ede25fece1f63d416dcdbd3',
          language: 'zho',
          product: 'bind',
          userInfo: {
            username: loginForm.value.username,
          },
        },
        function (captchaObj) {
          captchaObj.showCaptcha()
          captchaObj.onSuccess(function () {
            let result = captchaObj.getValidate()
            loginForm.value.captchaOutput = result.captcha_output
            loginForm.value.genTime = result.gen_time
            loginForm.value.lotNumber = result.lot_number
            loginForm.value.passToken = result.pass_token
            login(loginForm.value)
              .then((res) => {
                setToken(res.data.access_token)
                getRouters().then(res => {
                  sideStore.setSideRouters(res.data)
                })
                router.push({ path: '/' })
              })
              .catch(() => {
                loading.value = false
              })
          })
        }
      )
    }
  })
}
</script>

<style lang="scss" scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  background-image: url('@/assets/images/login-background.jpg');
  background-size: cover;
}
.title {
  margin: 0 auto 30px auto;
  text-align: center;
  color: #707070;
}
.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 350px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 40px;
    input {
      height: 40px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 0;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 37%;
  height: 40px;
  float: right;
  .login-code-img {
    height: 40px;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: cornflowerblue;
  font-family: Arial, serif;
  font-size: 12px;
  letter-spacing: 1px;
}
</style>
