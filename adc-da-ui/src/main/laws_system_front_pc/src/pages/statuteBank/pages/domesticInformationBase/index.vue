<!-- 国内信息库 -->
<template>
  <div class="domestic-information-base">
    <panel-header :tabs="tabsList" @activated="showComponent" :active="active"></panel-header>
    <panel-content>
      <transition enter-active-class="" leave-active-class="">
        <components :is="active"></components>
      </transition>
    </panel-content>
  </div>
</template>
<script>
import DomesticStandardLibrary from './components/DomesticStandardLibrary'
import DomesticRegulationsDatabase from './components/DomesticRegulationsDatabase'
import TestItemLibrary from './components/TestItemLibrary'
import TestReportLibrary from './components/TestReportLibrary'
import ProductLibrary from './components/ProductLibrary'
export default {
  name: 'domestic-information-base',
  data () {
    return {
      active: 'DomesticStandardLibrary',
      tabsList: [{
        title: '国内标准库',
        name: 'DomesticStandardLibrary'
      }, {
        title: '国内法规库',
        name: 'DomesticRegulationsDatabase'
      }, {
        title: '试验项目库',
        name: 'TestItemLibrary'
      }, {
        title: '试验报告库',
        name: 'TestReportLibrary'
      }, {
        title: '产品库',
        name: 'ProductLibrary'
      }]
    }
  },
  methods: {
    showComponent (name) {
      this.active = name
    }
  },
  components: {
    DomesticStandardLibrary,
    DomesticRegulationsDatabase,
    TestItemLibrary,
    TestReportLibrary,
    ProductLibrary
  },
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  @import 'animate.css';
  .domestic-information-base{
    width: 100%;
    height: 100%;
  }
</style>
