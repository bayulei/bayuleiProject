<!-- 待办流程 -->
<template>
  <div id="uncompletedProcess">
    <components :is="currentComponent"></components>
  </div>
</template>

<script>
import BuyApprovalProcess from './components/BuyApprovalProcess'

export default {
  name: 'uncompletedProcess',
  data () {
    return {
      currentComponent: 'BuyApprovalProcess'
    }
  },
  methods: {},
  components: {
    BuyApprovalProcess
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
