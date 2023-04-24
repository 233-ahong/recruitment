<template>
  <div class="job-header">
    <el-row>
      <el-col :span="2" />
      <el-col class="header-el-col" :span="20">
        <el-row type="flex" justify="center">
          <el-col :span="18">
            <el-button-group>
              <router-link
                v-for="(item, index) in sys_navigation_bar"
                :to="item.elTagClass"
              >
                <el-button color="#99bbff" class="el-li-front">
                  {{ item.label }}
                </el-button>
              </router-link>
            </el-button-group>
          </el-col>
          <el-col :span="2.5" class="link-login header-el-col">
            <el-link href="/login" :underline="false">
              <el-button type="primary" size="large" round plain>
                企业登录
              </el-button>
            </el-link>
          </el-col>
          <el-col :span="2.5" class="link-login header-el-col">
            <el-link href="/login" :underline="false">
              <el-button type="primary" size="large" round plain>
                学生登录
              </el-button>
            </el-link>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="2" />
    </el-row>
  </div>
</template>

<script setup>
import { getCurrentInstance } from 'vue'
const { proxy } = getCurrentInstance()
const { sys_navigation_bar } = proxy.useDict('sys_navigation_bar')
</script>

<style lang="scss" scoped>
.job-header {
  background-color: #99bbff;
  width: 100%;
  height: 60px;
  padding: 0;
}
.header-el-col {
  height: 60px;
}
.link-login {
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-li-front {
  height: 60px;
  width: 100px;
}
</style>
