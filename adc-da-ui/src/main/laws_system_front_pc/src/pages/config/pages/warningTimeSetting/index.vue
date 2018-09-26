<!-- 预警时间设置 -->
<template>
  <div class="warning-time-setting">
        <div class="warningSelect">
            <label-select v-model="warningForm.warning" :options="warningList" placeholder="请设置预警时间" label="预警时间设置" ></label-select>
        <div class="btn-group">
          <Button type="primary" @click="handleSubmit()">保存</Button>
          <Button style="margin-left: 8px" @click="handleReset ()">返回</Button>
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
          value: 'THREEMONTH',
          label: '3个月'
        },
        {
          value: 'SIXMONTH',
          label: '6个月'
        },
        {
          value: 'ONEYEAR',
          label: '一年'
        },
        {
          value: 'TWOYEAR',
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
    handleSubmit () {
      this.$http.putData('sys/warnTime/updateSource',
        {warnType: this.warningForm.warning},
        {_this: this}, res => {

        })
    },
    handleReset () {
      this.warningForm.warning = ''
    },
    getWarnTimeInfo () {
      this.$http.get('sys/warnTime/', {}, {_this: this}, res => {
        console.log(res)
        this.warningForm.warning = res.data[0].warnType
      })
    }
  },
  mounted () {
    this.getWarnTimeInfo()
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
