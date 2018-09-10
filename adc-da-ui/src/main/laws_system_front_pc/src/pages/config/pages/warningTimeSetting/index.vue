<!-- 预警时间设置 -->
<template>
  <div class="warning-time-setting">
    <Select v-model="warningModel" style="width:200px">
      <Option v-for="item in warningList" :value="item.value" :key="item.value">{{ item.label }}</Option>
    </Select>
  </div>
</template>

<script>
export default {
  name: 'warning-time-setting',
  data () {
    return {
      warningList: [
        {
          value: 'Three months',
          label: '3个月'
        },
        {
          value: 'six months',
          label: '6个月'
        },
        {
          value: 'one year',
          label: '一年'
        },
        {
          value: 'two year',
          label: '两年'
        }
      ],
      warningModel: ''
    }
  }
}
</script>

<style lang="less" scoped>
  .warning-time-setting{
    display: flex;
    background: #FFF;
  }
</style>
