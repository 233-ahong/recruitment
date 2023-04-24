import request from '@/utils/request.js'

export function saveResume(data) {
  return request({
    url: '/system/resumes',
    method: 'post',
    data: data,
  })
}
export function updateResume(data) {
  return request({
    url: '/system/resumes',
    method: 'put',
    data: data,
  })
}
