<template>
  <div class="login">
    <el-form
      ref="loginRef"
      :model="loginForm"
      :rules="loginRules"
      class="login-form">
      <h3 class="title">若依后台管理系统</h3>
      <div v-if="isPassword">
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            type="text"
            size="large"
            auto-complete="off"
            placeholder="请输入账号"
          >
            <template #prefix
              ><svg-icon icon-class="user" class="el-input__icon input-icon"
            /></template>
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
              ><svg-icon
                icon-class="password"
                class="el-input__icon input-icon"
            /></template>
          </el-input>
        </el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" style="margin: 0 0 25px 0"
          >记住密码</el-checkbox
        >
      </div>
      <div v-else>
        <el-form-item prop="phone">
          <el-input
            v-model="loginForm.phone"
            type="text"
            size="large"
            auto-complete="off"
            placeholder="请输入手机号"
          >
            <template #prefix
              ><svg-icon icon-class="phone" class="el-input__icon input-icon"
            /></template>
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-input
            v-model="loginForm.code"
            size="large"
            auto-complete="off"
            placeholder="验证码"
            style="width: 63%"
          >
            <template #prefix
              ><svg-icon
                icon-class="validCode"
                class="el-input__icon input-icon"
            /></template>
          </el-input>
          <div class="login-code">
            <el-button
              style="float: right"
              :disabled="codeOff"
              @click="getCode"
              class="login-code-img"
              >{{ codeString }}</el-button
            >
          </div>
        </el-form-item>
      </div>
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
        <div style="width: 350px">
          <el-row>
            <el-col :span="6">
              <router-link class="link-type" :to="'/register'"
                >立即注册</router-link
              >
            </el-col>
            <el-col :span="12" />
            <el-col :span="6">
              <el-link @click="editLoginType" class="login-type">{{
                loginType
              }}</el-link>
            </el-col>
          </el-row>
        </div>
      </el-form-item>
    </el-form>
    <div class="el-login-footer">
      <span>recruitment-ui</span>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { computed, getCurrentInstance, ref } from 'vue'
import Cookies from 'js-cookie'
import { code, loginByPassword, loginByPhone } from '@/api/user.js'
import { encrypt } from '@/utils/jsencrypt.js'
import { ElMessage } from 'element-plus'
import { setToken } from '@/utils/auth.js'
const { proxy } = getCurrentInstance()
const router = useRouter()

const loginForm = ref({
  username: 'admin',
  phone: '13312345678',
  password: 'admin123',
  rememberMe: false,
  code: '',
  uuid: '',
  captchaOutput: '',
  genTime: '',
  lotNumber: '',
  passToken: '',
})
const loginRules = {
  username: [{ required: true, trigger: 'blur', message: '请输入您的账号' }],
  password: [{ required: true, trigger: 'blur', message: '请输入您的密码' }],
  code: [{ required: true, trigger: 'change', message: '请输入验证码' }],
  phone: [
    {
      pattern: /^1[3-9]\d{9}$/,
      trigger: 'blur',
      message: '请输入正确的电话号码',
    },
    { required: true, trigger: 'blur', message: '请输入电话号码' },
  ],
}
const codeString = ref('获取验证码')
const codeOff = ref(false)
const seconds = ref(60)
const loading = ref(false)
const isPassword = ref(true)
const redirect = ref(undefined)
const loginType = computed(() => {
  return isPassword.value ? '手机号登录' : '账号密码登录'
})
function dateDown(i) {
  if (i <= 0) {
    codeOff.value = false
    codeString.value = '获取验证码'
  } else {
    codeOff.value = true
    codeString.value = i + '秒后重新获取'
    i--
    setTimeout(() => {
      dateDown(i)
    }, 1000)
  }
}
function getCode() {
  const rep = /^1[3-9]\d{9}$/
  if (rep.test(loginForm.value.phone)) {
    initGeetest4(
      {
        captchaId: '58ebeae93ede25fece1f63d416dcdbd3',
        language: 'zho',
        product: 'bind',
        userInfo: {
          username: loginForm.value.phone,
        },
      },
      function (captchaObj) {
        captchaObj.showCaptcha()
        captchaObj.onSuccess(function () {
          var result = captchaObj.getValidate()
          loginForm.value.captchaOutput = result.captcha_output
          loginForm.value.genTime = result.gen_time
          loginForm.value.lotNumber = result.lot_number
          loginForm.value.passToken = result.pass_token
          code(loginForm.value).then((res) => {
            dateDown(seconds.value)
            loginForm.value.uuid = res.msg
          })
        })
      }
    )
  } else {
    ElMessage.error('请输入正确的手机号')
  }
}
function handleLogin() {
  proxy.$refs.loginRef.validate((valid) => {
    if (valid) {
      loading.value = true
      if (isPassword.value) {
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
              var result = captchaObj.getValidate()
              loginForm.value.captchaOutput = result.captcha_output
              loginForm.value.genTime = result.gen_time
              loginForm.value.lotNumber = result.lot_number
              loginForm.value.passToken = result.pass_token
              if (loginForm.value.rememberMe) {
                Cookies.set('username', loginForm.value.username, {
                  expires: 30,
                })
                Cookies.set('password', encrypt(loginForm.value.password), {
                  expires: 30,
                })
                Cookies.set('rememberMe', loginForm.value.rememberMe, {
                  expires: 30,
                })
              } else {
                Cookies.remove('username')
                Cookies.remove('password')
                Cookies.remove('rememberMe')
              }
              loginByPassword(loginForm.value)
                .then((res) => {
                  setToken(res.data.access_token)
                  router.push({ path: redirect.value || '/' })
                })
                .catch(() => {
                  loading.value = false
                })
            })
          }
        )
      } else {
        loginByPhone(loginForm.value)
          .then((res) => {
            setToken(res.data.access_token)
            router.push({ path: redirect.value || '/' })
          })
          .catch(() => {
            loading.value = false
          })
      }
    }
  })
}
function editLoginType() {
  isPassword.value = !isPassword.value
}
</script>

<style lang="scss" scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
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
  width: 400px;
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
  color: #fff;
  font-family: Arial, serif;
  font-size: 12px;
  letter-spacing: 1px;
}
</style>
