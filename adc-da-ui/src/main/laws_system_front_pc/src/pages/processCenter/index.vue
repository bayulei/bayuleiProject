<!-- 流程中心 -->
<template>
  <div class="processCenter">
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
  name: 'index',
  data () {
    return {
      navList: [{
        title: '新建流程',
        path: '/createProcess'
      }, {
        title: '我的流程',
        path: '/myProcess'
      }, {
        title: '待办流程',
        path: '/uncompletedProcess'
      }, {
        title: '已办流程',
        path: '/complateProcess'
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
  .personal{}
</style>
