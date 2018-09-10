<!-- 预警时间设置 -->
<template>
  <div class="warning-time-setting">
    <Form ref="warningForm" :model="warningForm" :rules="warningRule" :label-width="100" style="margin: 1rem auto">
      <FormItem label="预警时间设置" prop="warning">
        <Select v-model="warningForm.warning">
          <Option v-for="item in warningList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </FormItem>
      <FormItem>
        <div class="btn-group">
          <Button type="primary" @click="handleSubmit('warningForm')">保存</Button>
          <Button style="margin-left: 8px" @click="handleReset ('warningForm')">返回</Button>
        </div>
      </FormItem>
    </Form>
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
      warningRule: {
        warning: [
          { required: true, message: '请设置预警时间', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$Message.success('Success!')
        } else {
          this.$Message.error('Fail!')
        }
      })
    },
    handleReset (name) {
      this.$refs[name].resetFields()
    }
  }
}
</script>

<style lang="less" scoped>
  .warning-time-setting{
    display: flex;
    background: #FFF;
    .btn-group{
      margin:1rem;
    }
    .btn-group Button{
    }
  }
</style>
