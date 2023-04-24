<template>
  <div class="container">
    <el-container>
      <el-header style="padding: 0" class="fixed-col"><job-header /></el-header>
      <el-main
        class="fixed-col-main"
        style="padding: 0; background-color: #f1f3f5"
      >
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import JobHeader from '@/components/layout/JobHeader.vue'
import DictTag from '@/components/DictTag/index.vue'
import { getCurrentInstance } from 'vue'
const { proxy } = getCurrentInstance()
const { sys_yes_no } = proxy.useDict('sys_yes_no')
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  .fixed-col {
    position: fixed;
    width: 100%;
    z-index: 500;
  }
  .fixed-col-main {
    position: absolute;
    top: 60px;
    width: 100%;
    height: auto;
  }
}
</style>
