import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/components/login/index.vue'
import Index from '@/views/index.vue'
import Layout from '@/layout'

export const routerList = [
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
      },
      {
        path: '/system/company',
        component: () => import('@/views/system/company')
      },
      {
        path: '/system/advert',
        component: () => import('@/views/system/advert')
      },
      {
        path: '/system/position',
        component: () => import('@/views/system/position')
      },
      {
        path: '/system/logger',
        component: () => import('@/views/system/logger')
      }
    ]
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: "/:pathMatch(.*)*",
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error/401'),
    hidden: true
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes: routerList,
})

export default router
