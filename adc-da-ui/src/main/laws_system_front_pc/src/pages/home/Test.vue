<template>
  <div class="test">
    <input type="button" value="请求" @click="postData">
  </div>
</template>
<script>
export default {
  data () {
    return {
      processType: 1,
      projectName: '这个测试肯定成功',
      flag: 1
    }
  },
  methods: {
    postData () {
      let vehicleApprovalEO = {
        processType: this.processType,
        projectName: this.projectName,
        flag: 1
      }
      this.$http.post('WorkFlow/VehicleApproval/submitOrSaveStandardApprovalProcess', vehicleApprovalEO, {
        _this: this
      }, res => {}, e => {})
    }
  }
}
</script>
<style lang="less">
  .test{
    padding: 20px;
    #labelInput{
      margin-bottom: 10px;
    }
  }
</style>
