import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'
import router from './router'
import '@fortawesome/fontawesome-free/css/all.css'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import '@/assets/styles/index.scss' // global css
import '@/assets/js/jy'
import store from './store'
import { useDict } from '@/utils/dict'
//svg-ico
import 'virtual:svg-icons-register'
import SvgIcon from '@/components/SvgIcon'
import elementIcons from '@/components/SvgIcon/svgicon'

import DictTag from '@/components/DictTag'
import FileUpload from '@/components/FileUpload'
library.add(fas)
const app = createApp(App)
app.use(elementIcons)
app.use(router)
app.use(store)
app.use(ElementPlus,{ locale: zhCn })

app.config.globalProperties.useDict = useDict

app.component('svg-icon', SvgIcon)
app.component('DictTag', DictTag)
app.component('font-awesome-icon', FontAwesomeIcon)
app.component('FileUpload', FileUpload)
app.mount('#app')
