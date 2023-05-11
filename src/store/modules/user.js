import { defineStore } from 'pinia'

const useUserStore = defineStore('user',{
  state: () => ({
    userInfo: {},
    userType: '',
  }),
  actions: {
    setUserInfo(userInfo) {
      this.userInfo = userInfo
    },
    setUserType(userType) {
      this.userType = userType
    },
    getUserInfo() {
      return this.userInfo
    },
    getUserType() {
      return this.userType
    }
  }
})
export default useUserStore
