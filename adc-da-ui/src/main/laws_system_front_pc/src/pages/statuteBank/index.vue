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
        title: '国内标准库',
        path: '/domesticStandardDatabase'
      }, {
        title: '国内法规库',
        path: '/domesticRegulationsDatabase'
      }, {
        title: '国外标准库',
        path: '/foreignStandardDatabase'
      }, {
        title: '国外法规库',
        path: '/foreignRegulationsDatabase'
      }, {
        title: '企业标准库',
        path: '/enterpriseStandardDatabase'
      }, {
        title: '准入标准法规清单',
        path: '/accessStandardsAndRegulations'
      }, {
        title: '试验项目库',
        path: '/testItemDatabase'
      }, {
        title: '本地产品/项目库',
        path: '/localProductDatabase'
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
