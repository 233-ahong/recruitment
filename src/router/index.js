import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/components/login/index.vue'
import Index from '@/views/index.vue'

export const routerList = [
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/',
    component: Index,
    children: [
      {
        path: '/school',
        component: () => import('@/views/pages/school'),
      },
      {
        path: '/strongest',
        component: () => import('@/views/pages/strongest'),
      },
      {
        path: '/government',
        component: () => import('@/views/pages/government'),
      },
      {
        path: '/resume',
        component: () => import('@/views/pages/resume'),
      }
    ],
  },
]

export const index = createRouter({
  history: createWebHistory(),
  routes: routerList,
})

export default index
