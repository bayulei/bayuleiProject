<!-- 个人中心 -->
<template>
  <div class="personal">
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
  name: 'personal',
  data () {
    return {
      navList: [{
        title: '个人信息',
        path: '/info'
      }, {
        title: '个人板块管理',
        path: '/plate'
      }, {
        title: '我的动态',
        path: '/dynamics'
      }, {
        title: '我的收藏',
        path: '/collection'
      }, {
        title: '我的推送',
        path: '/push'
      }, {
        title: '我的浏览',
        path: '/browsing'
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
  .personal{
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
