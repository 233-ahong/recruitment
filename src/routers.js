import router from './router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth.js'
NProgress.configure({ showSpinner: false });
import sidebarRouters from '@/store/modules/sideBar'
import { getRouters } from '@/api/user.js'
const whiteList = ['/login', '/auth-redirect', '/bind', '/register'];
const modules = import.meta.glob('@/views/**/*.vue')
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

function buildRouters(routers){
  return routers.filter(router => {
    if (router.children){
      router.children = buildChildren(router.children)
    }
    if (router.component) {
      router.component = loadView(router.component)
    }
    if (router.children != null && router.children && router.children.length) {
      router.children = buildRouters(router.children)
    } else {
      delete router['children']
      delete router['redirect']
    }
    return true
  })
}
function buildChildren(childrenMap, lastRouter = false) {
  var children = []
  childrenMap.forEach((el, index) => {
    if (el.children && el.children.length) {
        el.children.forEach(c => {
          c.path = el.path + '/' + c.path
          if (c.children && c.children.length) {
            children = children.concat(buildChildren(c.children, c))
            return
          }
          children.push(c)
        })
        return
    }
    if (lastRouter) {
      el.path = lastRouter.path + '/' + el.path
    }
    children = children.concat(el)
  })
  return children
}

function loadView(component) {
  let res;
  for (const path in modules) {
    const dir = path.split('views/')[1].split('.vue')[0];
    if (dir === component) {
      res = () => modules[path]();
    }
  }
  return res;
}

router.afterEach(() => {
  NProgress.done()
})
