<!-- 动态信息新增编辑 -->
<template>
  <div id="dynamicInfo">
    <div style="overflow:auto;height: 100%;" >
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
            <DatePicker v-model="msgDynamicInfoVO.pubTime"  type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="请选择" style="width: 200px"></DatePicker>
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
            <UEditor :config=config ref="ueditor"></UEditor>
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col>
          <FormItem label="相关附件" prop="attInfo" class="laws-info-item">
            <Upload multiple type="drag" ref="attInfo" :action="uploadFileListPath" :default-file-list="fileInfoList" name="files" :on-remove="delFileInfo" :on-success="uploadBackSuccess" :on-error="uploadBackError" >
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
          <FormItem label="相关链接" prop="linkUri" class="laws-info-item"  >
            <Input  v-model="msgDynamicInfoVO.linkUri" />
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col>
          <FormItem label="新闻图片" prop="picFile" class="laws-info-item">
            <Upload type="drag" ref="picFile" accept="image/*" :action="uploadFilePath" :default-file-list="picFIleList" name="file" :before-upload="clickPicFile" :on-remove="delPicFileInfo" :on-success="uploadPicBackSuccess" :on-error="uploadBackError" >
              <div :v-if="showPicUpload" style="padding: 20px 0">
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
      <Button type="primary" @click="gotoMsgInfoPage" >取消</Button>
    </div>
    </div>
    <loading :loading="loading">数据获取中</loading>
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
        pubTime: new Date(),
        linkUri: '',
        content: '',
        title: '',
        msgMode: '',
        msgType: '',
        isPicMsg: '',
        msgFileEOList: [],
        PicFileEO: {}
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
      fileInfoList: [],
      PicFile: {},
      picFIleList: [],
      showPicUpload: true,
      // UE编辑器配置
      config: {
        // 可以在此处定义工具栏的内容
        toolbars: [
          ['fullscreen', 'undo', 'redo', 'bold', 'indent', 'italic', 'underline', 'strikethrough', 'blockquote', 'simpleupload',
            '|', 'selectall', 'formatmatch', 'horizontal', 'removeformat', 'time', 'date', 'inserttitle',
            '|', 'cleardoc', 'fontfamily', 'fontsize', 'paragraph', 'link', 'searchreplace', 'justifyleft', 'justifyright', 'justifycenter', 'justifyjustify', 'forecolor', 'backcolor', // 背景色
            '|']
        ],
        autoHeightEnabled: false,
        autoFloatEnabled: true,
        initialContent: '请输入内容', // 初始化编辑器的内容,也可以通过textarea/script给值，看官网例子
        autoClearinitialContent: true, // 是否自动清除编辑器初始内容，注意：如果focus属性设置为true,这个也为真，那么编辑器一上来就会触发导致初始化的内容看不到了
        initialFrameWidth: null,
        initialFrameHeight: 450,
        BaseUrl: '',
        UEDITOR_HOME_URL: 'static/ueditor/'
      },
      loading: false
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
    loadMsgInfo () {
      let msgId = this.$route.params.id
      console.log(this.$route.params.id)
      if (msgId !== undefined || msgId !== '') {
        this.$http.get('lawss/msgDynamicInfo/' + msgId, {}, {_this: this}, res => {
          this.msgDynamicInfoVO = res.data
          // 加载文件信息
          console.log(res.data.content)
          this.$refs.ueditor.setContent(res.data.content)
          // 加载 附件信息
          this.fileInfoList = res.data.msgFileEOList !== null ? res.data.msgFileEOList : []
          if (res.data.picFileEO !== null) {
            this.PicFile = res.data.picFileEO
            this.picFIleList.push(res.data.picFileEO)
          }
        }, e => { console.log('失败啦') })
      }
    },
    // 保存新闻信息
    saveMsgInfo () {
      // 判断是否有存在附件
      if (this.msgDynamicInfoVO.id !== '') {
        console.log(this.fileInfoList)
        if (this.fileInfoList !== undefined && this.fileInfoList.length > 0) {
          let FileEOList = []
          for (let index = 0; index < this.fileInfoList.length; index++) {
            let fileInfo = this.fileInfoList[index]
            let file = {}
            file.attId = fileInfo.attId
            file.fileSuffix = fileInfo.fileSuffix
            file.fileName = fileInfo.fileName
            FileEOList.push(file)
          }
          this.msgDynamicInfoVO.msgFileEOList = FileEOList
        }

        // 判断是否有新闻图片
        if (JSON.stringify(this.PicFile) !== '{}') {
          let PicFile = {}
          PicFile.attId = this.PicFile.id
          PicFile.fileSuffix = this.PicFile.fileSuffix
          PicFile.fileName = this.PicFile.oldFileName
          this.msgDynamicInfoVO.picFileEO = PicFile
          this.msgDynamicInfoVO.isPicMsg = 0
        } else {
          this.msgDynamicInfoVO.isPicMsg = 1
        }

        // 获取正文内容
        let content = this.$refs.ueditor.getUEContent()
        this.msgDynamicInfoVO.content = content
        this.$http.putData('lawss/msgDynamicInfo',
          {
            id: this.msgDynamicInfoVO.id,
            pubOrg: this.msgDynamicInfoVO.pubOrg,
            pubTime: this.$dateFormat(this.msgDynamicInfoVO.pubTime, 'yyyy-MM-dd hh:mm:ss'),
            linkUri: this.msgDynamicInfoVO.linkUri,
            content: this.msgDynamicInfoVO.content,
            title: this.msgDynamicInfoVO.title,
            msgMode: this.msgDynamicInfoVO.msgMode,
            msgType: this.msgDynamicInfoVO.msgType,
            isPicMsg: this.msgDynamicInfoVO.isPicMsg,
            msgFileEOList: this.msgDynamicInfoVO.msgFileEOList,
            picFileEO: this.msgDynamicInfoVO.picFileEO
          },
          {_this: this, loading: this.loading}, res => {
            console.log(res)
            this.$router.push('/dynamicInformationManage')
          })
      } else {
        // 保存信息
        if (this.fileInfoList.length > 0) {
          let FileEOList = []
          for (let index = 0; index < this.fileInfoList.length; index++) {
            let fileInfo = this.fileInfoList[index]
            let file = {}
            file.attId = fileInfo.id
            file.fileSuffix = fileInfo.fileSuffix
            file.fileName = fileInfo.oldFileName
            FileEOList.push(file)
          }
          this.msgDynamicInfoVO.msgFileEOList = FileEOList
        }

        // 判断是否有新闻图片
        if (JSON.stringify(this.PicFile) !== '{}') {
          let PicFile = {}
          PicFile.attId = this.PicFile.id
          PicFile.fileSuffix = this.PicFile.fileSuffix
          PicFile.fileName = this.PicFile.oldFileName
          this.msgDynamicInfoVO.picFileEO = PicFile
          this.msgDynamicInfoVO.isPicMsg = 0
        } else {
          this.msgDynamicInfoVO.isPicMsg = 1
        }

        // 获取正文内容
        let content = this.$refs.ueditor.getUEContent()
        this.msgDynamicInfoVO.content = content

        this.$http.postData('lawss/msgDynamicInfo',
          {
            pubOrg: this.msgDynamicInfoVO.pubOrg,
            pubTime: this.$dateFormat(this.msgDynamicInfoVO.pubTime, 'yyyy-MM-dd hh:mm:ss'),
            linkUri: this.msgDynamicInfoVO.linkUri,
            content: this.msgDynamicInfoVO.content,
            title: this.msgDynamicInfoVO.title,
            msgMode: this.msgDynamicInfoVO.msgMode,
            msgType: this.msgDynamicInfoVO.msgType,
            isPicMsg: this.msgDynamicInfoVO.isPicMsg,
            msgFileEOList: this.msgDynamicInfoVO.msgFileEOList,
            picFileEO: this.msgDynamicInfoVO.picFileEO
          }, {_this: this, loading: this.loading}, res => {
            console.log(res)
            if (res.ok) {
              this.executeSuccess('发布成功!')
              this.$router.push('/dynamicInformationManage')
            } else {
              this.executeError('发布失败:' + res.message)
            }
          })
      }
    },
    // 获取资料中心模块信息
    getResourceMode () {
      this.$http.get('lawss/msgModule/getAll', {}, {_this: this},
        res => {
          if (res.ok) {
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
    // 上传文件成功回调
    // this.fileInfoList = res.data
    uploadBackSuccess (res, file, fileList) {
      if (res.ok) {
        this.fileInfoList.push(res.data[0])
      }
    },
    uploadPicBackSuccess (res, file, fileList) {
      console.log(res)
      if (res.ok) {
        this.showPicUpload = false
        this.PicFile = res.data
      }
    },
    // 上传文件失败提示
    uploadBackError (errorInfo, file, fileList) {
      this.executeError('上传失败! 失败原因:' + errorInfo.message)
    },
    // 删除文件
    delFileInfo (file) {
      let fileInfo = file.response.data[0]
      let index = this.fileInfoList.findIndex((value, index, arr) => {
        if (value[0].id === fileInfo.id) {
          return index
        }
      })
      this.fileInfoList.splice(index, 1)
    },
    delPicFileInfo (file) {
      this.showPicUpload = true
      this.PicFile = {}
    },
    clickPicFile (file) {
      if (JSON.stringify(this.PicFile) !== '{}') {
        this.executeError('新闻图片只能上传一张!')
        return false
      }
    },
    // 取消按钮
    gotoMsgInfoPage () {
      this.$router.push('/dynamicInformationManage')
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
    this.getResourceMode()
    this.loadMsgInfo()
  }
}
</script>

<style lang="less">
  #dynamicInfo{
    padding: 10px;
  }
</style>
