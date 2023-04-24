import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/components/login/index.vue'
import Index from '@/views/index.vue'
import Layout from '@/layout'

export const routerList = [
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/',
    component: Layout,
    redirect: '/system/index',
    children: [
      {
        path: '/system/index',
        component: () => import('@/views/index'),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true,activeMenu: '/index'}
      },
      {
        path: '/system/user',
        component: () => import('@/views/system/user')
      },
      {
        path: '/system/menu',
        component: () => import('@/views/system/menu')
      },
      {
        path: '/system/dict',
        component: () => import('@/views/system/dict')
      },
      {
        path: '/system/:dictId(\\d+)',
        component: () => import('@/views/system/dict/data'),
      }
    ]
  },
]

export const index = createRouter({
  history: createWebHistory(),
  routes: routerList,
})

export default index
