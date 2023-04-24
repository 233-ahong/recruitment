import request from '@/utils/request.js'
export function saveWork(data) {
  return request({
    url: '/system/workExperience',
    method: 'post',
    data: data,
  })
}
export function updateWork(data) {
  return request({
    url: '/system/workExperience',
    method: 'put',
    data: data,
  })
}
