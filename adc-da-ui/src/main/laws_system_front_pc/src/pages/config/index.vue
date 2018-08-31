<!-- 配置管理 -->
<template>
  <div class="config">
    <com-header></com-header>
    <div class="main">
      <side-bar :navList="navList"></side-bar>
      <div class="sub-container">
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
      }]
    }
  },
  components: {
    ComHeader,
    SideBar
  }
}
</script>

<style lang="less" scoped>
  .config{
    width: 100%;
    height: 100%;
    .main{
      width: 100%;
      height: calc(~'100% - 1.6rem');
      display: flex;
    }
    .sub-container{
      top: 1.6rem;
      flex: 1;
      height: calc(~'100% - 0.4rem');
      margin: 0 0.4rem 0.4rem 0.4rem;
    }
  }
</style>
