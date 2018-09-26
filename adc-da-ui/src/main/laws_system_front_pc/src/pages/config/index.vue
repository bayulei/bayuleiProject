<!-- 配置管理 -->
<template>
  <div class="config">
    <com-header :isCollapsed="sideClose" @collapsedSider="toggleSideBar"></com-header>
    <div class="main">
      <side-bar :navList="navList" v-model="sideClose" @change="toggleSideBar" :class="{ 'side-close': sideClose }"></side-bar>
      <div class="sub-container" :class="{ 'side-close': sideClose }">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import ComHeader from '@/pages/components/Header'
import SideBar from '@/pages/components/SideBar'
export default {
  name: 'config',
  data () {
    return {
      navList: [{
        title: '标准法规属性管理',
        path: '/regulationsManage'
      }, {
        title: '资料中心模块配置',
        path: '/informationCenterConfig'
      }, {
        title: '机构管理',
        path: '/mechanismManage'
      }, {
        title: '角色管理',
        path: '/roleManage'
      }, {
        title: '用户管理',
        path: '/userManage'
      }, {
        title: '动态信息管理',
        path: '/dynamicInformationManage'
      }, {
        title: '预警时间设置',
        path: '/warningTimeSetting'
      }],
      sideClose: false // 边栏收展状态
    }
  },
  methods: {
    /**
     * @description: 边栏收缩
     * @author: chenxiaoxi
     * @date: 2018-09-18 11:11:46
     */
    toggleSideBar () {
      this.sideClose = !this.sideClose
    }
  },
  components: {
    ComHeader,
    SideBar
  }
}
</script>

<style lang="less" scoped>
  .config{}
</style>
