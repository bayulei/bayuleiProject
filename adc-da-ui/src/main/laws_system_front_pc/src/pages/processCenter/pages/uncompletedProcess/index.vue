<!-- 待办流程 -->
<template>
  <div id="uncompletedProcess">
      <panel-header :tabs="tabsList" @activated="showComponent" :active="active"></panel-header>
      <panel-content>
        <transition enter-active-class="" leave-active-class="">
          <components :is="active"></components>
        </transition>
      </panel-content>
  </div>

</template>

<script>
import BuyApprovalProcess from './components/BuyApprovalProcess'
import vehicleApprovalApplyForm from './components/vehicleApprovalApplyForm'
export default {
  name: 'uncompletedProcess',
  data () {
    return {
      active: 'BuyApprovalProcess',
      tabsList: [{
        title: '购买流程表单',
        name: 'BuyApprovalProcess'
      }, {
        title: '车型流程表单',
        name: 'vehicleApprovalApplyForm'
      }]
    }
  },
  methods: {
    showComponent (name) {
      this.active = name
    }
  },
  components: {
    BuyApprovalProcess,
    vehicleApprovalApplyForm
  },
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  #uncompletedProcess{}
</style>
