/**
 * @description: 路由配置
 * @author: xx
 * @date: 2018-08-29 10:09:33
 */

import Vue from 'vue'
import iView from 'iview'
import VueRouter from 'vue-router'
import store from '../store'
import Login from '@/pages/login'
import Home from '@/pages/home'
import Test from '@/pages/home/test'
import Test2 from '@/pages/home/test2'
Vue.use(iView)
Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    redirect: '/'
  },
  {
    path: '/sign_in',
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
    path: '/test2',
    name: 'Test2',
    component: Test2,
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
    meta: {
      title: '个人'
    },
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
      },
      {
        path: '/feedback',
        name: 'FeedbackFeedback',
        component: resolve => require(['pages/personal/pages/feedback -feedback'], resolve),
        meta: {
          requireAuth: true,
          title: '意见反馈'
        }
      }]
  },
  // **************************   配置管理   ******************************* //
  {
    path: '/config',
    name: 'Config',
    redirect: '/regulationsManage',
    component: resolve => require(['pages/config'], resolve),
    meta: {
      title: '配置'
    },
    children: [
      {
        path: '/regulationsManage',
        name: 'RegulationsManage',
        component: resolve => require(['pages/config/pages/regulationsManage'], resolve),
        meta: {
          requireAuth: true,
          title: '法规属性'
        }
      },
      {
        path: '/informationCenterConfig',
        name: 'informationCenterConfig',
        component: resolve => require(['pages/config/pages/informationCenterConfig'], resolve),
        meta: {
          requireAuth: true,
          title: '资料中心'
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
          title: '动态信息'
        }
      },
      {
        path: '/dynamicInformationManage/dynamicInfomationPage/:id?',
        name: 'dynamicInfo',
        component: resolve => require(['pages/config/pages/dynamicInformationManage/dynamicInfomationPage'], resolve),
        meta: {
          requireAuth: true,
          title: '动态信息维护'
        }
      },
      {
        path: '/warningTimeSetting',
        name: 'WarningTimeSetting',
        component: resolve => require(['pages/config/pages/warningTimeSetting'], resolve),
        meta: {
          requireAuth: true,
          title: '预警时间'
        }
      }
    ]
  },
  // **************************   法规库   ******************************* //
  {
    path: '/statuteBank',
    name: 'StatuteBank',
    redirect: '/domesticStandardDatabase',
    component: resolve => require(['pages/statuteBank'], resolve),
    meta: {
      title: '法规库'
    },
    children: [
      {
        path: '/domesticStandardDatabase',
        name: 'DomesticStandardDatabase',
        component: resolve => require(['pages/statuteBank/pages/domesticStandardDatabase'], resolve),
        meta: {
          requireAuth: true,
          title: '国内标准库'
        }
      },
      {
        path: '/domesticRegulationsDatabase',
        name: 'DomesticRegulationsDatabase',
        component: resolve => require(['pages/statuteBank/pages/domesticRegulationsDatabase'], resolve),
        meta: {
          requireAuth: true,
          title: '国内法规库'
        }
      },
      {
        path: '/foreignStandardDatabase',
        name: 'ForeignStandardDatabase',
        component: resolve => require(['pages/statuteBank/pages/foreignStandardDatabase'], resolve),
        meta: {
          requireAuth: true,
          title: '国外标准库'
        }
      },
      {
        path: '/foreignRegulationsDatabase',
        name: 'ForeignRegulationsDatabase',
        component: resolve => require(['pages/statuteBank/pages/foreignRegulationsDatabase'], resolve),
        meta: {
          requireAuth: true,
          title: '国外法规库'
        }
      },
      {
        path: '/enterpriseStandardDatabase',
        name: 'EnterpriseStandardDatabase',
        component: resolve => require(['pages/statuteBank/pages/enterpriseStandardDatabase'], resolve),
        meta: {
          requireAuth: true,
          title: '企业标准库'
        }
      },
      {
        path: '/accessStandardsAndRegulations',
        name: 'AccessStandardsAndRegulations',
        component: resolve => require(['pages/statuteBank/pages/accessStandardsAndRegulations'], resolve),
        meta: {
          requireAuth: true,
          title: '法规清单'
        }
      },
      {
        path: '/testItemDatabase',
        name: 'TestItemDatabase',
        component: resolve => require(['pages/statuteBank/pages/testItemDatabase'], resolve),
        meta: {
          requireAuth: true,
          title: '试验项目库'
        }
      },
      {
        path: '/localProductDatabase',
        name: 'LocalProductDatabase',
        component: resolve => require(['pages/statuteBank/pages/localProductDatabase'], resolve),
        meta: {
          requireAuth: true,
          title: '产品项目库'
        }
      }
    ]
  },
  // **************************   流程中心   ******************************* //
  {
    path: '/processCenter',
    name: 'ProcessCenter',
    redirect: '/uncompletedProcess',
    component: resolve => require(['pages/processCenter'], resolve),
    children: [
      {
        path: '/uncompletedProcess',
        name: 'uncompletedProcess',
        component: resolve => require(['pages/processCenter/pages/uncompletedProcess'], resolve),
        meta: {
          requireAuth: true,
          title: '待办流程'
        }
      }
      // {
      //   path: '/complateProcess',
      //   name: 'ComplateProcess',
      //   component: resolve => require(['pages/processCenter/pages/complateProcess'], resolve),
      //   meta: {
      //     requireAuth: true,
      //     title: '已办流程'
      //   }
      // },
      // {
      //   path: '/createProcess',
      //   name: 'CreateProcess',
      //   component: resolve => require(['pages/processCenter/pages/createProcess'], resolve),
      //   meta: {
      //     requireAuth: true,
      //     title: '创建流程'
      //   }
      // },
      // {
      //   path: '/myProcess',
      //   name: 'MyProcess',
      //   component: resolve => require(['pages/processCenter/pages/myProcess'], resolve),
      //   meta: {
      //     requireAuth: true,
      //     title: '我的流程'
      //   }
      // }
    ]
  }
]

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
iView.LoadingBar.config({
  color: '#5596CC',
  failedColor: '#f0ad4e',
  height: 5
})
router.beforeEach((to, from, next) => {
  iView.LoadingBar.start()
  let toName = to.name
  let token = store.state.token
  // 返回值为登录状态
  if ((token === null || token === '') && (toName !== 'Login' && to.meta.requireAuth)) {
    router.push({
      name: 'Login'
    })
  } else {
    if (token !== null && token !== '' && toName === 'Login') {
      router.push({
        path: '/'
      })
    } else {
      next()
    }
  }
})
// 路由完成之后的操作
router.afterEach(route => {
  iView.LoadingBar.finish()
})

export default router
