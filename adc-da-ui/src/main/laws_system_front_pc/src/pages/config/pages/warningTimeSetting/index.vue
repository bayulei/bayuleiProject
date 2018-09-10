<!-- 预警时间设置 -->
<template>
  <div class="warning-time-setting">
        <div class="warningSelect">
            <label-select v-model="warningForm.warning" :options="warningList" placeholder="请设置预警时间" label="预警时间设置" ></label-select>
        <div class="btn-group">
          <Button type="primary" @click="handleSubmit('warningForm')">保存</Button>
          <Button style="margin-left: 8px" @click="handleReset ('warningForm')">返回</Button>
        </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'warning-time-setting',
  data () {
    return {
      warningForm: {
        warning: ''
      },
      warningList: [
        {
          value: 'Three Months',
          label: '3个月'
        },
        {
          value: 'Six Months',
          label: '6个月'
        },
        {
          value: 'One Year',
          label: '一年'
        },
        {
          value: 'two Year',
          label: '两年'
        }
      ],
      warningRule: {
        warning: [
          { required: true, message: '请设置预警时间', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleSubmit (name) {
      alert('提交的数据是' + this.warningForm.warning)
    },
    handleReset (name) {
      this.warningForm.warning = ''
    }
  }
}
</script>

<style lang="less" scoped>
  .warning-time-setting{
    display: flex;
    background: #FFF;
    .warningSelect{
      margin: 2rem auto;
    }
    .btn-group{
      margin:1rem;
    }
    .btn-group Button{
      width: 2rem;
      margin-left: 30px;
    }
  }
</style>
