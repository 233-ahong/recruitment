<template xmlns:dict-tag='http://www.w3.org/1999/html'>
  <div>
    <el-container>
      <el-container>
        <el-main style="padding: 10px">
          <el-row>
            <el-col :span="2" />
            <el-col :span="21" class="resume-main">
              <div class="resume-div">
                <h3 ref="上传简历">上传简历</h3>
                <el-upload class="upload-demo" drag multiple>
                  <el-icon class="el-icon--upload">
                    <upload-filled />
                  </el-icon>
                  <div class="el-upload__text">
                    拖拽或 <em>点击</em>上传简历
                  </div>
                  <template #tip>
                    <div class="el-upload__tip">
                      支持doc、docx、pdf、html、htm、txt、rtf、eml、wps、jpeg、jpg、png、ppt、pptx格式文件上传，最大不超过50MB
                    </div>
                  </template>
                </el-upload>
              </div>
              <div class="resume-div">
                <el-row>
                  <el-col :span="20">
                    <h3 ref="基本信息">基本信息</h3>
                  </el-col>
                  <el-col :span="4">
                    <el-button type="primary" @click="editShowData('isEditResumeInfo')">编辑</el-button>
                  </el-col>
                </el-row>
                <div :hidden='!showData.isEditResumeInfo.value'>
                  <el-row>
                    <el-col :span="2" />
                    <el-col :span='20'>
                      <el-descriptions border :column="3" style="width: 100%">
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-user-circle" />
                                </el-icon>
                                姓名
                              </div>
                            </template>
                            {{ resumeInfo.name }}
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-venus-mars" />
                                </el-icon>
                                性别
                              </div>
                            </template>
                            <dict-tag :options='sys_user_sex' :value='resumeInfo.gender'/>
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-image" />
                                </el-icon>
                                照片
                              </div>
                            </template>
                            <img :src="resumeInfo.picture" alt="" style="width: 60px; height: 60px;">
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-birthday-cake" />
                                </el-icon>
                               出生日期
                              </div>
                            </template>
                            {{ parseTime(resumeInfo.birthday,'{y}-{m}-{d}') }}
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-phone" />
                                </el-icon>
                                电话
                              </div>
                            </template>
                            {{ resumeInfo.phone }}
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-envelope" />
                                </el-icon>
                                邮箱
                              </div>
                            </template>
                            {{ resumeInfo.email }}
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-map-marker-alt" />
                                </el-icon>
                                籍贯
                              </div>
                            </template>
                            {{ resumeInfo.nativePlace }}
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-graduation-cap" />
                                </el-icon>
                               最高学历
                              </div>
                            </template>
                            <dict-tag class="resume-info-value" :options='sys_educational_background' :value='resumeInfo.education'/>
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-building" />
                                </el-icon>
                                毕业院校
                              </div>
                            </template>
                            {{ resumeInfo.university }}
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-flag" />
                                </el-icon>
                                政治面貌
                              </div>
                            </template>
                            <dict-tag class="resume-info-value" :options='sys_political_status' :value='resumeInfo.politicalStatus'/>
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                  <font-awesome-icon icon="fa-solid fa-book-open-reader" />
                                </el-icon>
                                专业类别
                              </div>
                            </template>
                            {{ resumeInfo.major }}
                          </el-descriptions-item>
                          <el-descriptions-item>
                            <template #label>
                              <div class="cell-item">
                                <el-icon>
                                 <font-awesome-icon icon="fa-solid fa-calendar-alt" />
                                </el-icon>
                                毕业时间
                              </div>
                            </template>
                            {{ parseTime(resumeInfo.graduationTime,'{y}-{m}') }}
                          </el-descriptions-item>
                        </el-descriptions>
                    </el-col>
                    <el-col :span="2" />
                  </el-row>
                </div>
                <div style='height: 100%' :hidden='showData.isEditResumeInfo.value'>
                  <el-row>
                    <el-col :span='2'/>
                    <el-col :span='20' style='background-color: #F8F8F8'>
                      <el-form ref="resumeInfoForm" :model="resumeInfo" :rules="resumeFormRules" label-width="80px">
                        <el-form-item label="照片" prop="picture">
                          <el-upload
                            style='background-color: white;border: 2px dashed var(--el-border-color);margin: 10px'
                            class="avatar-uploader"
                            :before-upload="(file) => beforeAvatarUpload(file)"
                            :action="uploadUrl"
                            :data='uploadData'
                            :http-request="uploadAction"
                            ref='upload'
                            :on-success="handleAvatarSuccess"
                            :on-error="handleAvatarError">
                            <el-image v-if="resumeInfo.picture" :src="resumeInfo.picture" class="avatar" />
                            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                          </el-upload>
                          <el-button v-if='resumeInfo.picture' size="small" type="primary" @click='updatePicture'>修改照片</el-button>
                        </el-form-item>
                        <el-form-item label="姓名" prop="name">
                          <el-input v-model="resumeInfo.name" placeholder="请输入姓名" />
                        </el-form-item>
                        <el-form-item label="性别" prop='gender'>
                          <el-radio-group v-model="resumeInfo.gender">
                            <el-radio
                              v-for="dict in sys_user_sex"
                              :key="dict.value"
                              :label="parseInt(dict.value)"
                            >{{dict.label}}</el-radio>
                          </el-radio-group>
                        </el-form-item>
                        <el-form-item label="出生日期" prop="birthday">
                          <el-date-picker
                            clearable
                            v-model="resumeInfo.birthday"
                            type="date"
                            placeholder="请选择出生日期">
                          </el-date-picker>
                        </el-form-item>
                        <el-form-item label="电话号码" prop="phone">
                          <el-input v-model="resumeInfo.phone" placeholder="请输入电话号码" />
                        </el-form-item>
                        <el-form-item label="电子邮件" prop="email">
                          <el-input v-model="resumeInfo.email" placeholder="请输入电子邮件" />
                        </el-form-item>
                        <el-form-item label="籍贯" prop="nativePlace">
                          <el-input v-model="resumeInfo.nativePlace" placeholder="请输入籍贯" />
                        </el-form-item>
                        <el-form-item label="最高学历" prop="education">
                          <el-select v-model="resumeInfo.education" placeholder="请选择最高学历">
                            <el-option
                              v-for="dict in sys_educational_background"
                              :key="dict.value"
                              :label="dict.label"
                              :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="毕业院校" prop="university">
                          <el-input v-model="resumeInfo.university" placeholder="请输入毕业院校" />
                        </el-form-item>
                        <el-form-item label="专业类别" prop="major">
                          <el-select v-model="resumeInfo.major" placeholder="请选择专业类别">
                            <el-option
                              v-for="dict in sys_user_sex"
                              :key="dict.value"
                              :label="dict.label"
                              :value="dict.value"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="政治面貌" prop="politicalStatus">
                          <el-select v-model="resumeInfo.politicalStatus" placeholder="请选择政治面貌">
                            <el-option
                              v-for="dict in sys_political_status"
                              :key="dict.value"
                              :label="dict.label"
                              :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="毕业时间" prop="graduationTime">
                          <el-date-picker
                            v-model="resumeInfo.graduationTime"
                            type="month"
                            placeholder="请选择毕业时间"
                            />
                        </el-form-item>
                      </el-form>
                      <div slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="saveResumeInfo">确 定</el-button>
                        <el-button @click="cancel('isEditResumeInfo')">取 消</el-button>
                      </div>
                    </el-col>
                    <el-col :span='2'/>
                  </el-row>
                </div>
              </div>
              <div class="resume-div">
                <el-row>
                  <el-col :span='20'>
                    <h3 ref="教育背景">教育背景</h3>
                  </el-col>
                  <el-col :span='4'>
                    <el-button type="primary" @click='editShowData("isEducationBackground")'>新增</el-button>
                  </el-col>
                </el-row>
                <div :hidden='!showData.isEducationBackground.value'>
                  <el-row>
                    <el-col :span='2'/>
                    <el-col :span='20'>
                      <el-descriptions v-for='item in educationBackground' border :column="3" style="width: 100%" :title='item.school'>
                        <template #extra>
                          <el-button type="default" @click='editEducationBackground(item)'>修改</el-button>
                        </template>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <office-building/>
                              </el-icon>
                              学校
                            </div>
                          </template>
                          {{ item.school }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-book-open-reader" />
                              </el-icon>
                              专业类别
                            </div>
                          </template>
                          {{ item.majorCategory }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <collection-tag/>
                              </el-icon>
                              专业名称
                            </div>
                          </template>
                          {{ item.majorName }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <medal/>
                              </el-icon>
                              学位
                            </div>
                          </template>
                          <dict-tag :options='sys_educational_background' :value='item.degree'/>
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <font-awesome-icon icon="fa-solid fa-calendar" />
                              开始时间
                            </div>
                          </template>
                          {{ item.startTime }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-calendar-days"/>
                              </el-icon>
                              结束时间
                            </div>
                          </template>
                          {{ item.endTime }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <document/>
                              </el-icon>
                              专业描述
                            </div>
                          </template>
                          {{ item.comment }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-school" />
                              </el-icon>
                              学习方式
                            </div>
                          </template>
                          <dict-tag :options='sys_learning_method' :value='item.learningMethod'/>
                        </el-descriptions-item>
                      </el-descriptions>
                    </el-col>
                    <el-col :span='2'/>
                  </el-row>
                </div>
                <div :hidden='showData.isEducationBackground.value'>
                  <el-row>
                    <el-col :span='2'/>
                    <el-col :span='20'>
                      <el-form :model='educationBackgroundInfo' :rules='educationBackgroundRules' ref='educationBackgroundForm'>
                        <el-form-item label="学校" prop="school">
                          <el-input v-model="educationBackgroundInfo.school" placeholder="请输入学校" />
                        </el-form-item>
                        <el-form-item label="专业类别" prop="majorCategory">
                          <el-input v-model="educationBackgroundInfo.majorCategory" placeholder="请输入专业类别" />
                        </el-form-item>
                        <el-form-item label="专业名称" prop="majorName">
                          <el-input v-model="educationBackgroundInfo.majorName" placeholder="请输入专业名称" />
                        </el-form-item>
                        <el-form-item label="学位" prop="degree">
                          <el-select v-model="educationBackgroundInfo.degree" placeholder="请选择学位">
                            <el-option
                              v-for="dict in sys_educational_background"
                              :key="dict.value"
                              :label="dict.label"
                              :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="开始时间" prop="startTime">
                          <el-date-picker
                            v-model="educationBackgroundInfo.startTime"
                            type="month"
                            placeholder="请选择开始时间"
                            />
                        </el-form-item>
                        <el-form-item label="结束时间" prop="endTime">
                          <el-date-picker
                            v-model="educationBackgroundInfo.endTime"
                            type="month"
                            placeholder="请选择结束时间"
                            />
                        </el-form-item>
                        <el-form-item label="专业描述" prop="comment">
                          <el-input v-model="educationBackgroundInfo.comment" placeholder="请输入专业描述" />
                        </el-form-item>
                        <el-form-item label="学习方式" prop="learningMethod">
                          <el-select v-model="educationBackgroundInfo.learningMethod" placeholder="请选择学习方式">
                            <el-option
                              v-for="dict in sys_learning_method"
                              :key="dict.value"
                              :label="dict.label"
                              :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                      </el-form>
                      <div slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="saveEducationBackground">确 定</el-button>
                        <el-button @click="cancel('isEducationBackground')">取 消</el-button>
                      </div>
                    </el-col>
                    <el-col :span='2'/>
                  </el-row>
                </div>
              </div>
              <div class="resume-div">
                <el-row>
                  <el-col :span='20'>
                    <h3 ref="实习/工作">实习/工作</h3>
                  </el-col>
                  <el-col :span='4'>
                    <el-button type="primary" @click="editShowData('isWorkExperience')">添加</el-button>
                  </el-col>
                </el-row>
                <div :hidden='!showData.isWorkExperience.value'>
                  <el-row>
                    <el-col :span='2'/>
                    <el-col :span='20'>
                      <el-descriptions v-for='item in workExperience' border column='3' :title='item.companyName'>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-building" />
                              </el-icon>
                              公司名称
                            </div>
                          </template>
                          {{ item.companyName }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-briefcase" />
                              </el-icon>
                              职位名称
                            </div>
                          </template>
                          {{ item.positionName }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-calendar" />
                              </el-icon>
                              经历类型
                            </div>
                          </template>
                          {{ item.experienceType }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-calendar" />
                              </el-icon>
                              开始时间
                            </div>
                          </template>
                          {{ item.startTime }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-calendar-days"/>
                              </el-icon>
                              结束时间
                            </div>
                          </template>
                          {{ item.endTime }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                          <template #label>
                            <div class="cell-item">
                              <el-icon>
                                <font-awesome-icon icon="fa-solid fa-clipboard-list" />
                              </el-icon>
                              工作描述
                            </div>
                          </template>
                          {{ item.jobDescription }}
                        </el-descriptions-item>
                      </el-descriptions>
                    </el-col>
                    <el-col :span='2'/>
                  </el-row>
                </div>
                <div :hidden='showData.isWorkExperience.value'>
                  <el-row>
                    <el-col :span='2'/>
                    <el-col :span='20'>
                      <el-form :model="workExperienceInfo" :rules="workExperienceRules" ref="workExperienceForm" label-width="100px">
                        <el-form-item label="公司名称" prop="companyName">
                          <el-input v-model="workExperienceInfo.companyName" placeholder="请输入公司名称" />
                        </el-form-item>
                        <el-form-item label="职位名称" prop="positionName">
                          <el-input v-model="workExperienceInfo.positionName" placeholder="请输入职位名称" />
                        </el-form-item>
                        <el-form-item label="经历类型" prop="experienceType">
                          <el-select v-model="workExperienceInfo.experienceType" placeholder="请选择经历类型">
                            <el-option
                              v-for="dict in sys_experience_type"
                              :key="dict.value"
                              :label="dict.label"
                              :value="parseInt(dict.value)"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="开始时间" prop="startTime">
                          <el-date-picker
                            v-model="workExperienceInfo.startTime"
                            type="month"
                            placeholder="请选择开始时间"
                            />
                        </el-form-item>
                        <el-form-item label="结束时间" prop="endTime">
                          <el-date-picker
                            v-model="workExperienceInfo.endTime"
                            type="month"
                            placeholder="请选择结束时间"
                            />
                        </el-form-item>
                        <el-form-item label="工作描述" prop="jobDescription">
                          <el-input :rows="3" type="textarea" maxlength="500" v-model="workExperienceInfo.jobDescription" placeholder="请输入工作描述" />
                        </el-form-item>
                      </el-form>
                      <div slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="saveWorkExperience">确 定</el-button>
                        <el-button @click="cancel('isWorkExperience')">取 消</el-button>
                      </div>
                    </el-col>
                    <el-col :span='2'/>
                  </el-row>
                </div>
              </div>
              <div class="resume-div">
                <h3 ref="校园经历">校园经历</h3>
              </div>
              <div class="resume-div">
                <h3 ref="项目/比赛">项目/比赛</h3>
              </div>
              <div class="resume-div">
                <h3 ref="荣誉奖励">荣誉奖励</h3>
              </div>
              <div class="resume-div">
                <h3 ref="其他信息">其他信息</h3>
                <div class="div-2"></div>
              </div>
            </el-col>
            <el-col :span="1" class="resume-main" />
          </el-row>
        </el-main>
        <el-aside style="padding: 10px" width="250px">
          <div class="resume-aside">
            <div class="resume-son" style="width: 230px">
              <h4>板块管理</h4>
              <nav>
                <ul class="toc-items">
                  <li
                    v-for="(item, index) in section_management"
                    :class="{ active: index === divIndex }"
                    class="toc-item"
                  >
                    <a
                      :title="item.label"
                      class="toc-link"
                      @click="pageJump(item.label, index)"
                      >{{ item.label }}</a
                    >
                  </li>
                </ul>
                <div :style="getDivStyle" class="toc-marker"></div>
              </nav>
            </div>
          </div>
        </el-aside>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { CollectionTag, Document, Medal, OfficeBuilding, Plus, UploadFilled } from '@element-plus/icons-vue'
import { v4 as uuidv4 } from 'uuid'
import { computed, getCurrentInstance, onBeforeUnmount, onMounted, reactive, ref, toRefs } from 'vue'
import { getUploadImageInfo, getUserResumeInfo } from '@/api/user.js'
import DictTag from '@/components/DictTag/index.vue'
import { parseTime } from '@/utils/recruitment.js'
import { ElMessage } from 'element-plus'
import { saveResume, updateResume } from '@/api/resume.js'
import { saveEducation, updateEducation } from '@/api/educationBackground.js'
import { saveWork, updateWork } from '@/api/workExperience.js'
const { proxy } = getCurrentInstance()
const {
  section_management,
  sys_user_sex,
  sys_educational_background,
  sys_political_status,
  sys_learning_method,
  sys_experience_type
} = proxy.useDict(
  'section_management',
  'sys_user_sex',
  'sys_educational_background',
  'sys_political_status',
  'sys_learning_method',
  'sys_experience_type'
)
const divIndex = ref(0)
const showData = {
  isEditResumeInfo: ref(true),
  isEducationBackground: ref(true),
  isWorkExperience: ref(true),
}
const data =reactive({
  uploadData: {
    key: null,
    policy: null,
    OSSAccessKeyId: null,
    success_action_status: null,
    signature: null
  },
  resumeInfo: {
    name: '',
    gender: '',
    picture: '',
    birthday: '',
    phone: '',
    email: '',
    nativePlace: '',
    education: '',
    university: '',
    major: '',
    politicalStatus: '',
    graduateTime: ''
  },
  educationBackgroundInfo: {
    school: '',
    majorCategory: '',
    majorName: '',
    degree: '',
    learningMethod: '',
    comment: '',
    startTime: '',
    endTime: ''
  },
  workExperienceInfo: {
    companyName: '',
    positionName: '',
    startTime: '',
    endTime: '',
    experienceType: '',
    jobDescription: ''
  },
  educationBackground: [],
  workExperience: [],
  projectExperience: [],
  honor: [],
  otherInfo: [],
  studentWorkExperience: [],
})
const resumeFormRules = ref({
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
  ],
  gender: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  birthday: [
    { required: true, message: '请选择出生日期', trigger: 'change' }
  ],
  phone: [
    { required: true, message: '请输入电话号码', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入电子邮件', trigger: 'blur' },
    { type: 'email', message: '电子邮件格式不正确', trigger: 'blur' }
  ],
  picture: [
    { required: true, message: '请上传照片', trigger: 'change' }
  ],
  nativePlace: [
    { required: true, message: '请输入籍贯', trigger: 'blur' }
  ],
  education: [
    { required: true, message: '请选择最高学历', trigger: 'change' }
  ],
  university: [
    { required: true, message: '请输入毕业院校', trigger: 'blur' }
  ],
  major: [
    { required: true, message: '请选择专业类别', trigger: 'change' }
  ],
  politicalStatus: [
    { required: true, message: '请选择政治面貌', trigger: 'change' }
  ],
  graduationTime: [
    { required: true, message: '请选择毕业时间', trigger: 'change' }
  ]
})
const educationBackgroundRules = ref({
  school: [
    { required: true, message: '请输入学校', trigger: 'blur' }
  ],
  majorCategory: [
    { required: true, message: '请选择专业类别', trigger: 'change' }
  ],
  majorName: [
    { required: true, message: '请输入专业名称', trigger: 'blur' }
  ],
  degree: [
    { required: true, message: '请选择学历', trigger: 'change' }
  ],
  learningMethod: [
    { required: true, message: '请选择学习方式', trigger: 'change' }
  ],
  comment: [
    { required: true, message: '请输入备注', trigger: 'blur' }
  ],
  startTime: [
    { required: true, message: '请选择开始时间', trigger: 'change' }
  ],
  endTime: [
    { required: true, message: '请选择结束时间', trigger: 'change' }
  ]
})
const workExperienceRules = ref({
  companyName: [
    { required: true, message: '请输入公司名称', trigger: 'blur' }
  ],
  positionName: [
    { required: true, message: '请输入职位名称', trigger: 'blur' }
  ],
  startTime: [
    { required: true, message: '请选择开始时间', trigger: 'change' }
  ],
  endTime: [
    { required: true, message: '请选择结束时间', trigger: 'change' }
  ],
  experienceType: [
    { required: true, message: '请选择工作类型', trigger: 'change' }
  ],
  jobDescription: [
    { required: true, message: '请输入工作描述', trigger: 'blur' }
  ]
})
const uploadUrl = ref('')
const imageUrl = ref('')
const { resumeInfo, educationBackground, educationBackgroundInfo, workExperience, projectExperience, honor, otherInfo, studentWorkExperience,uploadData,workExperienceInfo } = toRefs(data)
const handleAvatarSuccess = function (response, uploadFile) {
  resumeInfo.value.picture = URL.createObjectURL(uploadFile.raw)
}
const uploadAction = (option) => {
  const { onSuccess, onError, onProgress, file, filename, headers, withCredentials, action, data, method, filename: name, } = option
  const formData = new FormData()
  if (uploadData) {
    Object.keys(uploadData).forEach(key => {
      formData.append(key, uploadData[key])
    })
  }
  formData.append(name, file)
  const request = new XMLHttpRequest()
  request.open(method, action, true)
  request.withCredentials = withCredentials || false
  Object.keys(headers).forEach(key => {
    request.setRequestHeader(key, headers[key])
  })
  request.upload.onprogress = function (e) {
    onProgress({ percent: e.loaded / e.total * 100 })
  }
  request.onerror = function (e) {
    onError(e)
  }
  request.onload = function () {
    if (request.status < 200 || request.status >= 300) {
      return onError(getError(action, option, request))
    }
    onSuccess(getBody(request))
  }
  request.send(formData)
}
const beforeAvatarUpload = (rawFile) => {
  const type = rawFile.type.split('/')[1]
  return new Promise((resolve, reject) => {
    getUploadImageInfo().then(res => {
      let data = res.data
      let uuid = uuidv4()
      uploadData.key = data.dir + uuid + '.' + type
      uploadData.policy = data.encodedPolicy
      uploadData.OSSAccessKeyId = data.accessId
      uploadData.signature = data.signature
      uploadData.success_action_status = '200'
      uploadUrl.value = data.host
      imageUrl.value = data.host + '/' + data.dir + uuid + '.' + type
      if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png' && rawFile.type !== 'image/jpg') {
        ElMessage.error('照片只能是jpg/png/jpeg格式!')
        reject()
      } else if (rawFile.size / 1024 / 1024 > 5) {
        ElMessage.error('照片大小不能超过 5MB!')
        reject()
      }
      resolve(true)
    }).catch(err => {
      console.log(err)
      reject()
    })
  })
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
function getInfo() {
  getUserResumeInfo().then(res => {
    if (res.resumes === null||res.resumes === undefined) {
      return
    }
    resumeInfo.value = res.resumes
    educationBackground.value = res.educationBackground
    workExperience.value = res.workExperience
    projectExperience.value = res.projectExperience
    honor.value = res.honor
    otherInfo.value = res.otherInfo
    studentWorkExperience.value = res.studentWorkExperience
  })
}
function pageJump(ref, index) {
  divIndex.value = index
  proxy.$refs[ref].scrollIntoView()
  window.scrollBy(0, -75)
}
function cancel (isShow){
  showData[isShow].value = true
  if (isShow === 'isEducationBackground') {
    educationBackgroundInfo.value = {
      id: '',
      majorCategory: '',
      majorName: '',
      degree: '',
      learningMethod: '',
      comment: '',
      startTime: '',
      endTime: ''
    }
  }
}
function editShowData(isShow) {
  if (showData[isShow].value) {
    showData[isShow].value = false
  }
}
function saveResumeInfo() {
  resumeInfo.value.picture = imageUrl.value
  proxy.$refs['resumeInfoForm'].validate((valid) => {
    if (valid) {
      if (resumeInfo.value.id === ''|| resumeInfo.value.id === undefined||resumeInfo.value.id === null) {
        saveResume(resumeInfo.value).then(res => {
          ElMessage.success('保存成功')
          if (!showData.isEditResumeInfo.value) {
            showData.isEditResumeInfo.value = true
          }
        })
      }else {
        updateResume(resumeInfo.value).then(res => {
          ElMessage.success('修改成功')
          if (!showData.isEditResumeInfo.value) {
            showData.isEditResumeInfo.value = true
          }
        })
      }
    }
  })
}
function handleAvatarError(error, file) {
  ElMessage.error(error+file)
}
function saveEducationBackground() {
  proxy.$refs['educationBackgroundForm'].validate((valid) => {
    if (valid) {
      if (educationBackgroundInfo.value.id === '' || educationBackgroundInfo.value.id === undefined || educationBackgroundInfo.value.id === null) {
        saveEducation(educationBackgroundInfo.value).then(res => {
          ElMessage.success('保存成功')
          if (!showData.isEducationBackground.value) {
            showData.isEducationBackground.value = true
          }
        })
      } else {
        updateEducation(educationBackgroundInfo.value).then(res => {
          ElMessage.success('修改成功')
          educationBackgroundInfo.value = {}
          if (!showData.isEducationBackground.value) {
            showData.isEducationBackground.value = true
          }
        })
      }
    }
  })
}
function editEducationBackground(data){
  educationBackgroundInfo.value = data
  if (showData.isEducationBackground.value) {
    showData.isEducationBackground.value = false
  }
}
function getBody(request) {
  return new Promise((resolve, reject) => {
    let body = []
    request.on('data', chunk => {
      body.push(chunk)
    })
    request.on('end', () => {
      body = Buffer.concat(body).toString()
      resolve(body)
    })
    request.on('error', err => {
      reject(err)
    })
  })
}
function getError(option, xhr) {
  const msg = `cannot post ${option.action} ${xhr.status}'`
  const err = new Error(msg)
  err.status = xhr.status
  err.method = 'post'
  err.url = option.action
  return err
}
function updatePicture(){
  resumeInfo.value.picture = ''
}
function saveWorkExperience() {
  proxy.$refs['workExperienceForm'].validate((valid) => {
    if (valid) {
      if (workExperienceInfo.value.id === '' || workExperienceInfo.value.id === undefined || workExperienceInfo.value.id === null) {
        saveWork(workExperienceInfo.value).then(res => {
          ElMessage.success('保存成功')
          if (!showData.isWorkExperience.value) {
            showData.isWorkExperience.value = true
          }
        })
      } else {
        updateWork(workExperienceInfo.value).then(res => {
          ElMessage.success('修改成功')
          workExperienceInfo.value = {}
          if (!showData.isWorkExperience.value) {
            showData.isWorkExperience.value = true
          }
        })
      }
    }
  })
}
onMounted(() => {
  window.addEventListener('scroll', handleScroll)
})
onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll)
})
getInfo()
</script>

<style lang="scss" scoped>
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
  background-color: #409eff;
  border-radius: 4px;
  width: 4px;
  height: 16px;
  top: 122px;
  z-index: 2;
  transition: top 0.25s cubic-bezier(0, 1, 0.5, 1), opacity 0.25s,
    background-color 0.5s;
}
.toc-items li.active {
  background-color: #409eff;
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
  color: #409eff;
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
  display: inline-block;
  margin-left: 5px;
}
.avatar-uploader .el-upload {
  border: 2px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
