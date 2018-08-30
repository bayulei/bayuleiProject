/**
 * @description: 路由配置
 * @author: xx
 * @date: 2018-08-29 10:09:33
 */

import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/pages/login'
import Home from '@/pages/home'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

Vue.use(VueRouter)

const routes = [{
  path: '*',
  redirect: '/'
}, {
  path: '/signin',
  name: 'Login',
  component: Login
}, {
  path: '/',
  component: Home,
  children: [
    {
      path: '/',
      name: 'Home',
      component: resolve => require(['pages/home/pages/Home'], resolve),
      meta: {
        requireAuth: true,
        meta: '首页'
      }
    },
    // **************************   个人中心   ******************************* //
    {
      path: '/personal',
      name: 'Personal',
      redirect: '/info',
      component: resolve => require(['pages/personal'], resolve),
      children: [
        {
          path: '/info',
          name: 'personalInfo',
          component: resolve => require(['pages/personal/pages/personal-info'], resolve),
          meta: {
            requireAuth: true,
            meta: '个人信息'
          }
        },
        {
          path: '/plate',
          name: 'IndividualPlate',
          component: resolve => require(['pages/personal/pages/individual-plate'], resolve),
          meta: {
            requireAuth: true,
            meta: '个人板块管理'
          }
        },
        {
          path: '/dynamics',
          name: 'MyDynamics',
          component: resolve => require(['pages/personal/pages/my-dynamics'], resolve),
          meta: {
            requireAuth: true,
            meta: '我的动态'
          }
        },
        {
          path: '/collection',
          name: 'MyCollection',
          component: resolve => require(['pages/personal/pages/my-collection'], resolve),
          meta: {
            requireAuth: true,
            meta: '我的收藏'
          }
        },
        {
          path: '/push',
          name: 'MyPush',
          component: resolve => require(['pages/personal/pages/my-push'], resolve),
          meta: {
            requireAuth: true,
            meta: '我的推送'
          }
        },
        {
          path: '/browsing',
          name: 'MyBrowsing',
          component: resolve => require(['pages/personal/pages/my-browsing'], resolve),
          meta: {
            requireAuth: true,
            meta: '我的浏览'
          }
        }]
    }]
}]

const router = new VueRouter({
  routes,
  mode: 'history', // default: hash ,history
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
