<template>
  <div class="app-wrapper">
    <el-container>
      <system-aside class='sidebar-container' />
      <el-container class='main-container' >
        <el-header height='50px' ><system-header/></el-header>
        <el-main class='main'><router-view/></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import SystemAside from './components/aside'
import SystemHeader from './components/header'
</script>

<style lang="scss" scoped>
.common-layout{
  position: relative;
  height: 100%;
  width: 100%;
}
.main{
  padding: 0;
  margin: 0;
}
</style>
