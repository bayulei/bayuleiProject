// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'styles/reset.css'
import iView from 'iview'
import 'assets/my-theme/index.less'
import 'styles/common.less'
// import 'styles/iconfont.css'
import $ from 'jquery'
// vuex
import store from './store'
// 自定义插件引入
import plugins from '@/common/plugins'
Vue.config.productionTip = false
Vue.use(iView)
Vue.prototype.$ = $
Vue.use(plugins)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
