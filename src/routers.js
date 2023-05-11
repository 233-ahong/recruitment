import router from './router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth.js'
NProgress.configure({ showSpinner: false });
import sidebarRouters from '@/store/modules/sideBar'
import { getRouters } from '@/api/user.js'
const whiteList = ['/login', '/auth-redirect', '/bind', '/register'];
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    if (sidebarRouters().sideRouters.length===0){
      getRouters().then(res => {
        sidebarRouters().setSideRouters(res.data)
      })
    }
    /* has token*/
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else {
      next()
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${to.fullPath}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})
