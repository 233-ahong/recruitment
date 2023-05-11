import request from '@/utils/request.js'
export function login(data) {
  return request({
    url: '/auth/loginByPassword',
    method: 'post',
    data: data,
  })
}

export function logOut() {}

export function getRouters() {
  return request({
    url: '/system/sys/menu/getRouters',
    method: 'get'
  })
}

export function getUserInfo() {
  return request({
    url: '/system/sys/use/getUserInfo',
    method: 'get'
  })
}
