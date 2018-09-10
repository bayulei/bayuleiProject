<template>
  <div class="test">
    <label-input :label="123"></label-input>
    <label-select v-model="userId" :options="options" label="管理员"></label-select>
    <span>{{ userId }}</span>
  </div>
</template>
<script>
export default {
  data () {
    return {
      userId: '1002',
      options: [{
        value: '1001',
        label: '张小明'
      }, {
        value: '1002',
        label: '王铁柱'
      }]
    }
  },
  methods: {}
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
