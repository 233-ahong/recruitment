<template>
  <div>
    <el-container>
      <el-container>
        <el-main style='padding: 10px'>
          <el-row>
            <el-col :span='2' />
            <el-col :span='21' class='resume-main'>
              <div class='resume-div'>
                <h3 ref='上传简历'>上传简历</h3>
                <el-upload class='upload-demo' drag multiple>
                  <el-icon class='el-icon--upload'>
                    <upload-filled />
                  </el-icon>
                  <div class='el-upload__text'>拖拽或 <em>点击</em>上传简历</div>
                  <template #tip>
                    <div class='el-upload__tip'>
                      支持doc、docx、pdf、html、htm、txt、rtf、eml、wps、jpeg、jpg、png、ppt、pptx格式文件上传，最大不超过50MB
                    </div>
                  </template>
                </el-upload>
              </div>
              <div class='resume-div'>
                <h3 ref='基本信息'>基本信息</h3>
                <div>
                    <div class="resume-info">
                      <div class="resume-info-item">
                        <span class="resume-info-label">姓名：</span>
                        <span class="resume-info-value">{{ resume }}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">性别：</span>
                        <span class="resume-info-value">{{ resume === 1 ? '男' : '女' }}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">出生日期：</span>
                        <span class="resume-info-value">{{ resume }}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">电话号码：</span>
                        <span class="resume-info-value">{{ resume }}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">电子邮件：</span>
                        <span class="resume-info-value">{{ resume }}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">照片：</span>
                        <img :src="resume" alt="照片">
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">籍贯：</span>
                        <span class="resume-info-value">{{ resume }}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">最高学历：</span>
                        <span class="resume-info-value">{{  }}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">毕业院校：</span>
                        <span class="resume-info-value">{{ resume }}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">专业类别：</span>
                        <span class="resume-info-value">{{123}}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">政治面貌：</span>
                        <span class="resume-info-value">{{123}}</span>
                      </div>
                      <div class="resume-info-item">
                        <span class="resume-info-label">毕业时间：</span>
                        <span class="resume-info-value">{{123}}</span>
                      </div>
                    </div>
                </div>
              </div>
              <div class='resume-div'>
                <h3 ref='照片'>照片</h3>
              </div>
              <div class='resume-div'>
                <h3 ref='教育背景'>教育背景</h3>
              </div>
              <div class='resume-div'>
                <h3 ref='实习/工作'>实习/工作</h3>
              </div>
              <div class='resume-div'>
                <h3 ref='校园经历'>校园经历</h3>
              </div>
              <div class='resume-div'>
                <h3 ref='项目/比赛'>项目/比赛</h3>
              </div>
              <div class='resume-div'>
                <h3 ref='荣誉奖励'>荣誉奖励</h3>
              </div>
              <div class='resume-div'>
                <h3 ref='其他信息'>其他信息</h3>
                <div class='div-2'></div>
              </div>
            </el-col>
            <el-col :span='1' class='resume-main' />
          </el-row>
        </el-main>
        <el-aside style='padding: 10px' width='250px'>
          <div class='resume-aside'>
            <div class='resume-son' style='width: 230px'>
              <h4>板块管理</h4>
              <nav>
                <ul class='toc-items'>
                  <li v-for='(item,index) in section_management' :class='{active: false ? true:index===divIndex}'
                      class='toc-item'>
                    <a :title='item.label' class='toc-link' @click='pageJump(item.label,index)'>{{ item.label }}</a>
                  </li>
                </ul>
                <div :style='getDivStyle' class='toc-marker'></div>
              </nav>
            </div>
          </div>
        </el-aside>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { UploadFilled } from '@element-plus/icons-vue'
import { computed, getCurrentInstance, onBeforeUnmount, onMounted, ref } from 'vue'

const { proxy } = getCurrentInstance()
const { section_management } = proxy.useDict('section_management')
const divIndex = ref(0)
const resume = ref(1234)

function pageJump(ref, index) {
  divIndex.value = index
  proxy.$refs[ref].scrollIntoView()
  window.scrollBy(0, -75)
}

const getDivStyle = computed(() => {
  if (divIndex.value > 8) {
    divIndex.value = 8
  }
  const value = divIndex.value * 32 + 122
  return 'opacity: 1; top: ' + value + 'px'
})
const handleScroll = () => {
  const scrollHeight = window.scrollY
  for (let i = section_management.value.length - 1; i >= 0; i--) {
    const heading = section_management.value[i]
    const headingEl = proxy.$refs[heading.label].offsetTop
    if (scrollHeight > headingEl - 75) {
      divIndex.value = i
      break
    }
  }
}

onMounted(() => {
  window.addEventListener('scroll', handleScroll)
})

onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll)
})
</script>

<style lang='scss' scoped>
.resume-main {
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  background-color: white;
  height: 100%;
  margin: 5px;
}

.el-upload__text {
  font-size: 20px;
}

.el-upload__tip {
  text-align: center;
  font-size: 15px;
}

.resume-div {
  padding: 5px;
  width: 100%;
}

.toc-marker {
  opacity: 1;
  position: fixed;
  left: 1270px;
  background-color: #409EFF;
  border-radius: 4px;
  width: 4px;
  height: 16px;
  top: 122px;
  z-index: 2;
  transition: top .25s cubic-bezier(0, 1, .5, 1), opacity .25s, background-color .5s;
}
.toc-items li.active {
  background-color: #409EFF;
}
h3 {
  font-weight: bold;
  margin: 1px;
  padding: 5px;
}
h4 {
  margin: 5px;
}
ul {
  list-style: none;
  padding: 5px;
}
.resume-aside {
  position: fixed;
}
.div-2 {
  height: 100vh;
}
.toc-item {
  color: #409EFF;
}
.resume-info {
  display: flex;
  flex-wrap: wrap;
}
.resume-info-item {
  width: 33%;
  padding: 10px;
  box-sizing: border-box;
}
.resume-info-label {
  font-weight: bold;
  font-size: 15px;
}
.resume-info-value {
  margin-left: 5px;
}
</style>
