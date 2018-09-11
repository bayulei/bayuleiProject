<template>
  <div id="transact">
    <Form ref="formInline" :model="transactForm">
      <FormItem label="流程编号">
        <Input type="text" placeholder="请输入流程编号" v-model="transactForm.processNumber"></Input>
      </FormItem>
      <FormItem label="流程类型">
        <Select v-model="transactForm.processType">
          <Option value="10">标准购买审批流程</Option>
        </Select>
      </FormItem>
      <FormItem label="流程描述">
         <Input name="processDescription" v-model="transactForm.processDescription" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入流程描述"></Input>
      </FormItem>
      <FormItem label="标准检索">
        <Input type="text" name="" placeholder="XXXXXXXX"></Input>
      </FormItem>
      <FormItem label="检索结果">
        <Input type="text" name="" placeholder="无"></Input>
      </FormItem>
      <FormItem label="标准费用">
        <Select v-model="transactForm.money" placeholder="请选择">
          <Option :value="1000">大于等于500元</Option>
          <Option :value="200">小于500元</Option>
        </Select>
      </FormItem>
      <FormItem label="审批意见">
        <Input v-model="transactForm.comment" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入审批意见"></Input>
      </FormItem>
      <FormItem>
        <Button style="margin-left: 8px">委托</Button>
        <Button style="margin-left: 8px" @click="rejected">退回</Button>
        <Button type="primary">提交</Button>
      </FormItem>
    </Form>
  </div>
</template>

<script>
export default {
  name: 'transact',
  data () {
    return {
      currentComponent: 'BuyApprovalProcess',
      ruleInline: {},
      transactForm: {
        processNumber: '',
        processType: '',
        processDescription: '',
        money: '',
        comment: ''
      }
    }
  },
  methods: {
    rejected () {
      this.$http.post('WorkFlow/BuyApproval/reject', {
        processInstanceId: '5001',
        nowUserId: '0'
      }, {
        _this: this
      }, res => {
        //
      })
    },
    // 获取流程变量数据，展现到表单中
    getVariables () {
      this.$http.$get('WorkFlow/BuyApproval/getVariables', {
        processInstanceId: '5001'
      }, {
        _this: this
      }, res => {
        this.transactForm.processNumber = res.data.processNumber
        this.transactForm.processType = res.data.processType
        this.transactForm.processDescription = res.data.processDescription
        this.transactForm.money = res.data.money
      })
    }
  },
  components: {},
  props: {},
  computed: {},
  watch: {},
  mounted () {
    this.getVariables()
  }
}
</script>

<style lang="less">
  #transact{}
</style>
