<!-- 动态信息新增编辑 -->
<template>
  <div id="dynamicInfo">
    <Form ref="msgDynamicInfoVO" :model="msgDynamicInfoVO" :rules="msgDynamicInfoVOFormRules" label-position="right" class="label-input-form">
      <input v-model="msgDynamicInfoVO.id" v-show="false">
      <Row>
        <Col span="8" >
          <FormItem label="动态标题" prop="title" class="laws-info-item">
            <Input  v-model="msgDynamicInfoVO.title" />
          </FormItem>
        </Col>
        <Col span="8" >
          <FormItem label="发布时间" prop="pubTime" class="laws-info-item">
            <DatePicker v-model="msgDynamicInfoVO.pubTime" type="datetime" format="yyyy-MM-dd HH:mm" placeholder="请选择" style="width: 200px"></DatePicker>
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col span="8" >
          <FormItem label="消息模块" prop="msgType" class="laws-info-item">
            <Select v-model="msgDynamicInfoVO.msgType" @on-change="msgTypeChange" style="width:200px">
              <Option v-for="item in msgTypeOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
            <Select v-if="showMsgMode" v-model="msgDynamicInfoVO.msgMode" style="width:200px">
              <Option v-for="item in msgModeOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          </FormItem>
        </Col>
        <Col span="8" >
          <FormItem label="发布机构" prop="pubOrg" class="laws-info-item">
            <Input  v-model="msgDynamicInfoVO.pubOrg" />
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col>
          <FormItem label="动态内容" prop="content" class="laws-info-item">
            <Input  v-model="msgDynamicInfoVO.content" />
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col>
          <FormItem label="相关附件" prop="attInfo" class="laws-info-item">
            <Upload multiple type="drag" ref="attInfo" :action="uploadFileListPath" name="files" :on-remove="delFileInfo" :on-success="uploadBackSuccess" :on-error="uploadBackError" >
              <div style="padding: 20px 0">
                <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
                <p>点击或拖拽文件到此处</p>
              </div>
            </Upload>
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col>
          <FormItem label="相关链接" prop="linkUri" class="laws-info-item">
            <Input  v-model="msgDynamicInfoVO.linkUri" />
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col>
          <FormItem label="新闻图片" prop="picFile" class="laws-info-item">
            <Upload type="drag" ref="picFile" :action="uploadFilePath">
              <div style="padding: 20px 0">
                <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
                <p>点击或拖拽文件到此处</p>
              </div>
            </Upload>
          </FormItem>
        </Col>
      </Row>
    </Form>
    <div>
      <Button type="primary" icon="ios-add" title="保存" @click="saveMsgInfo">保存</Button>
      <Button type="primary">取消</Button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'dynamicInfo',
  data () {
    return {
      msgDynamicInfoVO: {
        id: '',
        pubOrg: '',
        pubTime: '',
        linkUri: '',
        content: '',
        title: '',
        msgMode: '',
        msgType: '',
        isPicMsg: '',
        attInfo: [],
        picFile: {}
      },
      msgDynamicInfoVOFormRules: {

      },
      msgTypeOptions: [{
        label: '国内动态',
        value: 'INLAND'
      }, {
        label: '国外动态',
        value: 'FOREIGN'
      }, {
        label: '资料中心',
        value: 'RESOURCE'
      }],
      msgModeOptions: [],
      showMsgMode: false,
      uploadFilePath: this.simpleUploadPath,
      uploadFileListPath: this.multipleUploadPath,
      // 附件信息集合
      fileInfoList: []
    }
  },
  methods: {
    msgTypeChange (value) {
      if (value === 'RESOURCE') {
        this.showMsgMode = true
      } else {
        this.showMsgMode = false
        this.msgDynamicInfoVO.msgMode = ''
      }
    },
    // 保存新闻信息
    saveMsgInfo () {

    },
    // 获取资料中心模块信息
    getResourceMode () {
      this.$http.get('lawss/msgModule/getAll', {}, {_this: this},
        res => {
          if (res.ok) {
            console.log(res)
            let msgModdeObj = []
            let msgModeList = res.data
            for (let index = 0; index < msgModeList.length; index++) {
              let msgMode = {}
              msgMode.label = msgModeList[index].moduleName
              msgMode.value = msgModeList[index].id
              msgModdeObj.push(msgMode)
            }
            this.msgModeOptions = msgModdeObj
          }
        })
    },
    // this.fileInfoList = res.data
    uploadBackSuccess (res, file, fileList) {
      console.log(res)
      console.log('获取文件列表信息')
      console.log(fileList)
      if (res.ok) {
        this.fileInfoList.push(res.data)
      }
    },
    uploadBackError (errorInfo, file, fileList) {
      this.executeError('上传失败! 失败原因:' + errorInfo.message)
    },
    delFileInfo (file) {
      let fileInfo = file.response.data[0]
      let index = this.fileInfoList.findIndex((value, index, arr) => {
        if (value[0].id === fileInfo.id) {
          return index
        }
      })
      this.fileInfoList.splice(index, 1)
    },
    // 成功弹框
    executeSuccess (message) {
      this.$Message.success(message)
    },
    // 失败弹框
    executeError (message) {
      this.$Message.error(message)
    }
  },
  components: {},
  props: {},
  computed: {},
  watch: {},
  mounted () {
    // this.getResourceMode()
  }
}
</script>

<style lang="less">
  #dynamicInfo{
    padding: 10px;
  }
</style>
