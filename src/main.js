import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router'
import '@/assets/js/jy'
import './routers'
import '@/assets/styles/index.scss'
import { useDict } from '@/utils/dict'

import 'virtual:svg-icons-register'
import SvgIcon from '@/components/SvgIcon'
import elementIcons from '@/components/SvgIcon/svgicon'
import DictTag from '@/components/DictTag'
import RightToolbar from '@/components/RightToolbar'
import { createPinia } from 'pinia'
import { handleTree,addDateRange } from '@/utils/recruitment.js'
import Pagination from '@/components/Pagination'

const app = createApp(App)
const pinia = createPinia()

app.config.globalProperties.useDict = useDict
app.config.globalProperties.handleTree = handleTree
app.config.globalProperties.addDateRange = addDateRange
app.use(pinia)
app.use(ElementPlus)
app.use(elementIcons)
app.use(router)
app.component('SvgIcon', SvgIcon)
app.component('DictTag', DictTag)
app.component("RightToolbar",RightToolbar)
app.component('Pagination', Pagination)

app.mount('#app')
