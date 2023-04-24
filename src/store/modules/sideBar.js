import { defineStore } from 'pinia'

const sidebarRouters = defineStore('sidebar', {
  state: () => ({
    sideRouters: []
  }),
  actions: {
    getSideRouters(){
      return this.sideRouters
    },
    setSideRouters(router){
      this.sideRouters = router
    },
    initSideRouters() {}
  }
})
export default sidebarRouters
