import request from '@/utils/request.js'

export function listCompany(data) {
  return request({
    url: '/system/company',
    method: 'get',
    params: data
  })
}

export function getCompany(data) {
  return request({
    url: '/system/company/' + data,
    method: 'get',
  })
}

export function updateCompany(data) {
  return request({
    url: '/system/company',
    method: 'put',
    data: data,
  })
}
