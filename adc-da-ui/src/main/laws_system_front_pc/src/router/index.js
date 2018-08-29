/**
 * @description: 路由配置
 * @author: xx
 * @date: 2018-08-29 10:09:33
 */

import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/pages/home'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

Vue.use(VueRouter)

const routes = [{
  path: '*',
  redirect: '/'
}, {
  path: '/',
  name: 'Home',
  component: Home
}]

const router = new VueRouter({
  routes,
  mode: 'hash', // default: hash ,history
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return {x: 0, y: 0}
    }
  }
})

// 全局路由配置
router.beforeEach((to, from, next) => {
  next()
})
// 路由完成之后的操作
router.afterEach(route => {
  NProgress.done()
})

export default router
