<!-- 法规库 -->
<template>
 <div class="statute-bank">
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
        title: '国内信息库',
        path: '/domesticInformationBase'
      }, {
        title: '国外信息库',
        path: '/foreignInformationBase'
      }, {
        title: '云端适应性分析',
        path: '/cloudAdaptationAnalysis'
      }]
    }
  },
  methods: {},
  components: {
    ComHeader,
    SideBar
  },
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  .statute-bank{}
</style>
