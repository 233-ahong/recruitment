<template>
  <div>
    <router-link  style='text-decoration: none;' to='/'>
      <div v-if='!isCollapse' class='side-icon'>
        <svg class='side-svg' width="30" height="30" xmlns="http://www.w3.org/2000/svg">
          <g>
            <title>background</title>
            <rect fill="#fff" id="canvas_background" height="30" width="30" y="-1" x="-1"/>
            <g display="none" overflow="visible" y="0" x="0" height="100%" width="100%" id="canvasGrid">
              <rect fill="url(#gridpattern)" stroke-width="0" y="0" x="0" height="100%" width="100%"/>
            </g>
          </g>
          <g>
            <title>Layer 1</title>
            <rect stroke="#000" id="svg_1" height="30" width="30" y="0" x="0" stroke-width="1.5" fill="#0fffff"/>
            <text font-style="normal" font-weight="bold" xml:space="preserve" text-anchor="start" font-family="'Palatino Linotype', 'Book Antiqua', Palatino, serif" font-size="20" id="svg_2" y="22" x="5" stroke-width="0" stroke="#000" fill="#000000">招</text>
          </g>
        </svg>
        <h1 class='side-text'>后台管理</h1>
      </div>
      <div v-else class='side-icon'>
        <svg class='side-svg' width="30" height="30" xmlns="http://www.w3.org/2000/svg">
          <g>
            <title>background</title>
            <rect fill="#fff" id="canvas_background" height="30" width="30" y="-1" x="-1"/>
            <g display="none" overflow="visible" y="0" x="0" height="100%" width="100%" id="canvasGrid">
              <rect fill="url(#gridpattern)" stroke-width="0" y="0" x="0" height="100%" width="100%"/>
            </g>
          </g>
          <g>
            <title>Layer 1</title>
            <rect stroke="#000" id="svg_1" height="30" width="30" y="0" x="0" stroke-width="1.5" fill="#0fffff"/>
            <text font-style="normal" font-weight="bold" xml:space="preserve" text-anchor="start" font-family="'Palatino Linotype', 'Book Antiqua', Palatino, serif" font-size="20" id="svg_2" y="22" x="5" stroke-width="0" stroke="#000" fill="#000000">招</text>
          </g>
        </svg>
      </div>
    </router-link>
    <el-scrollbar class="scrollbar-wrapper">
      <el-menu
        :collapse="isCollapse"
        background-color='#304156'
        text-color='#bfcbd9'
        active-text-color='#409eff'
        :unique-opened='true'
        :router='true'
        mode='vertical'>
        <template v-for="item in sidebarRouters">
          <el-sub-menu :key="item.path" v-if="item.children">
            <template #title>
              <svg-icon :icon-class="item.meta.icon"/>
              <span>{{ item.meta.title }}</span>
            </template>
            <template v-for="child1 in item.children">
              <el-sub-menu :key="child1.path" v-if="child1.children">
                <template #title>
                  <svg-icon :icon-class="child1.meta.icon"/>
                  <span>{{ child1.meta.title }}</span>
                </template>
                <el-menu-item-group>
                  <template v-for="child2 in child1.children" :key="child2.path">
                    <el-menu-item :index="child2.path">
                      <svg-icon :icon-class="child2.meta.icon"/>
                      <span>{{ child2.meta.title }}</span>
                    </el-menu-item>
                  </template>
                </el-menu-item-group>
              </el-sub-menu>
              <el-menu-item class='menu-color' :key="child1.path" v-else :index="child1.path">
                <svg-icon :icon-class="child1.meta.icon"/>
                <span>{{ child1.meta.title }}</span>
              </el-menu-item>
            </template>
          </el-sub-menu>
          <el-menu-item :key="item.path" v-else :index="item.path">
            <svg-icon :icon-class="item.meta.icon"/>
            <span>{{ item.meta.title }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import sideBar from '@/store/modules/sideBar'
import SvgIcon from '@/components/SvgIcon/index.vue'
import useAppStore from '@/store/modules/app'

const appStore = useAppStore()
const isCollapse = computed(() => !appStore.sidebar.opened);
const sideStore = sideBar()
const sidebarRouters = computed(() => sideStore.sideRouters)
</script>

<style lang='scss' scoped>
.side-icon{
  width: 200px;
  height: 50px;
  display: flex;
  justify-content: center;
  .side-svg{
    margin-right: 10px;
    margin-top: 10px;
  }
  .side-text{
    display: inline-block;
    margin-top: 15px;
    font-size: 20px;
    color: #409EFF;
  }
}
.menu-color{
  background-color: #1F2D3D;
}
</style>
