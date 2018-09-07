<!-- 云端适应性分析 -->
<template>
  <div id="vehicleApprovalApplyForm">
    <Form :model="formItem" :label-width="80">
      <FormItem label="流程编号">
        <Input v-model="formItem.processNumber" placeholder="请输入流程编号"></Input>
      </FormItem>
      <FormItem label="流程类型">
        <Select v-model="formItem.processType">
          <Option value="beijing">整车认可试验计划下达及验证流程</Option>
          <Option value="shanghai">标准购买审批流程</Option>
          <Option value="shenzhen">企业技术标准制修订年度计划审批发布流程</Option>
        </Select>
      </FormItem>
      <FormItem label="流程描述">
        <Input v-model="formItem.processDescription" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter something..."></Input>
      </FormItem>
      <FormItem label="项目名称">
        <Input v-model="formItem.projectName" placeholder="请输入项目名称"></Input>
      </FormItem>
      <FormItem label="附件">
        <Upload
          multiple
          action="//jsonplaceholder.typicode.com/posts/">
          <Button icon="ios-cloud-upload-outline">浏览</Button>
        </Upload>
      </FormItem>
      <FormItem label="选择人员" class="orginize-tree">
        <Tree :data="data4" show-checkbox multiple></Tree>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="saveInfo()">保存</Button>
        <Button style="margin-left: 8px"  @click="submitInfo()">提交</Button>
      </FormItem>
    </Form>
  </div>
</template>

<script>
export default {
  name: 'vehicleApprovalApplyForm',
  data () {
    return {
      formItem: {
        projectName: '',
        processNumber: '',
        processType: '',
        processDescription: ''
      },
      data4: [
        {
          title: 'parent 1',
          expand: true,
          selected: true,
          children: [
            {
              title: 'parent 1-1',
              expand: true,
              children: [
                {
                  title: 'leaf 1-1-1',
                  disabled: true
                },
                {
                  title: 'leaf 1-1-2'
                }
              ]
            },
            {
              title: 'parent 1-2',
              expand: true,
              children: [
                {
                  title: 'leaf 1-2-1',
                  checked: true
                },
                {
                  title: 'leaf 1-2-1'
                }
              ]
            }
          ]
        }
      ]
    }
  },
  methods: {
    saveInfo () {
      this.$http.post('WorkFlow/VehicleApproval/submitOrSaveStandardApprovalProcess', this.formItem, {
        _this: this
      }, res => {
        if (res.ok) {
          alert('删除成功')
          this.searchLawsInfo()
        }
      }, e => {

      })
    }
  },
  components: {},
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  #vehicleApprovalApplyForm{
    .orginize-tree{
      height: 6rem;
      overflow: auto;
    }
  }
</style>
