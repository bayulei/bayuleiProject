<!-- 法规标准属性管理 -->
<template>
  <div class="regulations-manage">
    <panel-header :tabs="tabsList" @activated="showComponent" :active="active"></panel-header>
    <panel-content>
      <transition enter-active-class="" leave-active-class="">
        <components :is="active"></components>
      </transition>
    </panel-content>
  </div>
</template>

<script>
import StandardCategory from './components/StandardCategory'
import StandardClassification from './components/StandardClassification'
import ApplicableModel from './components/ApplicableModel'
import ApplicableAuthentication from './components/ApplicableAuthentication'
import DriveType from './components/DriveType'
import Category from './components/Category'
export default {
  name: 'regulations-manage',
  data () {
    return {
      active: 'StandardCategory',
      tabsList: [{
        title: '标准类别',
        name: 'StandardCategory'
      }, {
        title: '标准分类',
        name: 'StandardClassification'
      }, {
        title: '适用车型',
        name: 'ApplicableModel'
      }, {
        title: '适用认证',
        name: 'ApplicableAuthentication'
      }, {
        title: '驱动类型',
        name: 'DriveType'
      }, {
        title: '所属类别',
        name: 'Category'
      }]
    }
  },
  methods: {
    /**
     * @description: 切换组件side-bar-left
     * @author: xx
     * @date: 2018-08-30 14:00:36
     */
    showComponent (name) {
      this.active = name
    }
  },
  components: {
    StandardCategory,
    StandardClassification,
    ApplicableModel,
    ApplicableAuthentication,
    DriveType,
    Category
  }
}
</script>

<style lang="less" scoped>
  @import 'animate.css';
  .regulations-manage{
    width: 100%;
    height: 100%;
  }
</style>
