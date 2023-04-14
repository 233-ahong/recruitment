import request from '@/utils/request.js'

export function loginByPassword(user) {
  return request({
    url: '/auth/loginByPassword',
    method: 'post',
    data: user,
  })
}

export function logout() {
  return request({
    url: '/auth/logOut',
    method: 'delete',
    headers: {
      isToken: false
    }
  })
}

export function code(data) {
  return request({
    url: '/code',
    method: 'post',
    data: data,
    headers: {
      isToken: false
    }
  })
}

export function loginByPhone(data) {
  return request({
    url: '/auth/loginByPhone',
    method: 'post',
    data: data
  })
}
