<template>
  <div id="transact">
    <Form ref="formInline" :model="formItem">
      <FormItem label="流程编号">
        <Input type="text" name="processNumber" placeholder="请输入流程编号">
        </Input>
      </FormItem>
      <FormItem label="流程类型">
        <Select name="processType">
          <Option value="10">标准购买审批流程</Option>
        </Select>
      </FormItem>
      <FormItem label="流程描述">
         <Input name="processDescription" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter something..."></Input>
      </FormItem>
      <FormItem label="标准费用">
        <Select name="money">
          <Option value="">请选择</Option>
          <Option value="1000">大于等于500元</Option>
          <Option value="200">小于500元</Option>
        </Select>
      </FormItem>
      <FormItem>
        <Button type="primary">提交</Button>
        <Button style="margin-left: 8px">驳回</Button>
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
      ruleInline: {}
    }
  },
  methods: {

  },
  components: {},
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  #transact{}
</style>
