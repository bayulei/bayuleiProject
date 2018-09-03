/**
 * @description: 路由配置
 * @author: xx
 * @date: 2018-08-29 10:09:33
 */

import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/pages/login'
import Home from '@/pages/home'
import Test from '@/pages/home/test'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    redirect: '/'
  },
  {
    path: '/signin',
    name: 'Login',
    component: Login
  },
  {
    path: '/test',
    name: 'Test',
    component: Test,
    meta: {
      requireAuth: true,
      title: '功能测试页'
    }
  },
  {
    path: '/',
    component: Home,
    meta: {
      requireAuth: true,
      title: '首页'
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
        name: 'PersonalInfo',
        component: resolve => require(['pages/personal/pages/personal-info'], resolve),
        meta: {
          requireAuth: true,
          title: '个人信息'
        }
      },
      {
        path: '/plate',
        name: 'IndividualPlate',
        component: resolve => require(['pages/personal/pages/individual-plate'], resolve),
        meta: {
          requireAuth: true,
          title: '个人板块管理'
        }
      },
      {
        path: '/dynamics',
        name: 'MyDynamics',
        component: resolve => require(['pages/personal/pages/my-dynamics'], resolve),
        meta: {
          requireAuth: true,
          title: '我的动态'
        }
      },
      {
        path: '/collection',
        name: 'MyCollection',
        component: resolve => require(['pages/personal/pages/my-collection'], resolve),
        meta: {
          requireAuth: true,
          title: '我的收藏'
        }
      },
      {
        path: '/push',
        name: 'MyPush',
        component: resolve => require(['pages/personal/pages/my-push'], resolve),
        meta: {
          requireAuth: true,
          title: '我的推送'
        }
      },
      {
        path: '/browsing',
        name: 'MyBrowsing',
        component: resolve => require(['pages/personal/pages/my-browsing'], resolve),
        meta: {
          requireAuth: true,
          title: '我的浏览'
        }
      }]
  },
  // **************************   配置管理   ******************************* //
  {
    path: '/config',
    name: 'Config',
    redirect: '/regulationsManage',
    component: resolve => require(['pages/config'], resolve),
    children: [
      {
        path: '/regulationsManage',
        name: 'RegulationsManage',
        component: resolve => require(['pages/config/pages/regulationsManage'], resolve),
        meta: {
          requireAuth: true,
          title: '标准法规属性管理'
        }
      },
      {
        path: '/informationCenterConfig',
        name: 'informationCenterConfig',
        component: resolve => require(['pages/config/pages/informationCenterConfig'], resolve),
        meta: {
          requireAuth: true,
          title: '资料中心模块配置'
        }
      },
      {
        path: '/mechanismManage',
        name: 'MechanismManage',
        component: resolve => require(['pages/config/pages/mechanismManage'], resolve),
        meta: {
          requireAuth: true,
          title: '机构管理'
        }
      },
      {
        path: '/roleManage',
        name: 'RoleManage',
        component: resolve => require(['pages/config/pages/roleManage'], resolve),
        meta: {
          requireAuth: true,
          title: '角色管理'
        }
      },
      {
        path: '/userManage',
        name: 'UserManage',
        component: resolve => require(['pages/config/pages/userManage'], resolve),
        meta: {
          requireAuth: true,
          title: '用户管理'
        }
      },
      {
        path: '/dynamicInformationManage',
        name: 'DynamicInformationManage',
        component: resolve => require(['pages/config/pages/dynamicInformationManage'], resolve),
        meta: {
          requireAuth: true,
          title: '动态信息管理'
        }
      },
      {
        path: '/warningTimeSetting',
        name: 'WarningTimeSetting',
        component: resolve => require(['pages/config/pages/warningTimeSetting'], resolve),
        meta: {
          requireAuth: true,
          title: '预警时间设置'
        }
      }
    ]
  }
]

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
