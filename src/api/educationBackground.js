import request from '@/utils/request.js'

export function saveEducation(data) {
  return request({
    url: '/system/educationBackground',
    method: 'post',
    data
  })
}

export function updateEducation(data) {
  return request({
    url: '/system/educationBackground',
    method: 'put',
    data
  })
}
