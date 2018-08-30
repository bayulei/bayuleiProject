<!-- 个人中心 -->
<template>
  <div class="personal">
    <side-bar></side-bar>
    <div class="sub-container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import SideBar from './components/SideBar'
export default {
  name: 'personal',
  data () {
    return {}
  },
  components: {
    SideBar
  }
}
</script>

<style lang="less" scoped>
  .personal{
    width: 100%;
    height: 100%;
    display: flex;
    .sub-container{
      flex: 1;
      margin:0 0.4rem;
      height: inherit;
    }
  }
</style>
