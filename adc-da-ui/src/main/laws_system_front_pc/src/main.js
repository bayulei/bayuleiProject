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
Vue.config.productionTip = false
Vue.use(iView)
Vue.prototype.$ = $
Vue.use(plugins)
Vue.use(components)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
