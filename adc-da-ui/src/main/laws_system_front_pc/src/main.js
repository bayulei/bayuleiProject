/**
 * @description: 项目入口文件
 * @author: xx
 * @date: 2018-09-01 15:08:08
 */

import Vue from 'vue'
import App from './App'
import router from './router'
// iview
import iView from 'iview'
// 样式重置
import 'styles/reset.css'
// 全局样式文件
import 'styles/common.less'
// iconfont字体库
import 'styles/iconfont.css'
// jQuery
import $ from 'jquery'
// vuex
import store from './store'
// 自定义插件
import plugins from '@/common/plugins'
// 自定义全局组件
import components from '@/common/components'
// axios
import axios from 'axios'
import $axios from '@/common/axios'
// es6兼容
import 'babel-polyfill'
// 表单验证
import verify from '@/common/verify'
// 引入公共配置
import globalConfig from '../config/global.config'
Vue.config.productionTip = false
Vue.use(iView)
Vue.prototype.$ = $
Vue.use(plugins)
Vue.use(components)
Vue.prototype.verify = verify
// 原生axios
Vue.prototype.axios = axios
// 封装后的axios
Vue.prototype.$http = $axios
// 后台Url路径
Vue.prototype.globalUrl = globalConfig.serverUrl
// 后台接口路径
Vue.prototype.globalInterfaceUrl = globalConfig.interfaceUrl
// 单文件上传
Vue.prototype.simpleUploadPath = globalConfig.simpleUploadPath
// 多文件上传
Vue.prototype.multipleUploadPath = globalConfig.multipleUploadPath
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
