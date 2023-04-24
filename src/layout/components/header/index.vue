<template>
  <div class="navbar">
    <hamburger id="hamburger-container" class="hamburger-container" @toggleClick="toggleSideBar"/>
  </div>
</template>

<script setup>
import Hamburger from '@/components/Hamburger'
import useAppStore from '@/store/modules/app'

const appStore = useAppStore()
function toggleSideBar() {
  appStore.toggleSideBar()
}
</script>

<style lang='scss' scoped>
.hamburger-container {
  line-height: 46px;
  height: 100%;
  float: left;
  cursor: pointer;
  transition: background 0.3s;
  -webkit-tap-highlight-color: transparent;

&:hover {
   background: rgba(0, 0, 0, 0.025);
 }
}
</style>
