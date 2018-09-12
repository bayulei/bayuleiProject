<!-- 我的收藏 -->
<template>
  <div class="my-collection">
    <panel-header :tabs="tabsList" @activated="showComponent" :active="active"></panel-header>
    <panel-content>
      <components :is="active"></components>
    </panel-content>
  </div>
</template>

<script>
import CollectionDynamics from './components/CollectionDynamics'
import CollectionStandard from './components/CollectionStandard'
export default {
  name: 'my-collection',
  data () {
    return {
      active: 'CollectionStandard',
      tabsList: [{
        title: '已收藏的标准',
        name: 'CollectionStandard'
      }, {
        title: '已收藏的动态',
        name: 'CollectionDynamics'}]
    }
  },
  methods: {
    showComponent (name) {
      this.active = name
    }
  },
  components: {
    CollectionDynamics,
    CollectionStandard
  }
}
</script>

<style lang="less" scoped>
  .my-collection{

  }
</style>
