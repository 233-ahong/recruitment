import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router'
import '@/assets/styles/index.scss' // global css
import '@/assets/js/jy'
import store from './store'
import { useDict } from '@/utils/dict'
//svg-ico
import 'virtual:svg-icons-register'
import SvgIcon from '@/components/SvgIcon'
import elementIcons from '@/components/SvgIcon/svgicon'

import DictTag from '@/components/DictTag'
import FileUpload from "@/components/FileUpload"

const app = createApp(App)
app.use(elementIcons)
app.use(router)
app.use(store)
app.use(ElementPlus)

app.config.globalProperties.useDict = useDict

app.component('svg-icon', SvgIcon)
app.component('DictTag', DictTag)
app.component('FileUpload', FileUpload)
app.mount('#app')
