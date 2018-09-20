<!-- 弹窗上传 -->
<template>
  <Modal v-model="show" :title="title" id="upload" @on-close="handleClose" @on-ok="handleOk" @on-cancel="handleCancel">
    <Upload
      :multiple="multiple"
      type="drag"
      :on-success="handleSuccess"
      :on-error="handleError"
      :before-upload="beforeUpload"
      :show-upload-list="showUploadList"
      :action="uploadPath">
      <div style="padding: 20px 0">
        <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
        <p>点击或拖拽上传文件</p>
      </div>
    </Upload>
  </Modal>
</template>

<script>
export default {
  name: 'upload',
  data () {
    return {
      // 上传地址
      uploadPath: this.multiple ? this.multipleUploadPath : this.simpleUploadPath
    }
  },
  methods: {
    // 上传之前回调函数
    beforeUpload () {
      if (!this.autoUpload) {
        this.$emit('beforeUpload')
        return false
      } else {
        return true
      }
    },
    // 上传成功
    handleSuccess (response, file, fileList) {
      this.$emit('on-success', response, file, fileList)
    },
    // 上传失败
    handleError (e, file, fileList) {
      this.$emit('on-error', e, file, fileList)
    },
    // 弹窗关闭
    handleClose () {
      this.$emit('change')
      this.$emit('on-close')
    },
    // 弹窗确定
    handleOk () {
      this.$emit('change')
      this.$emit('on-ok')
    },
    // 弹窗取消
    handleCancel () {
      this.$emit('change')
      this.$emit('on-cancel')
    }
  },
  components: {},
  model: {
    prop: 'show',
    event: 'change'
  },
  props: {
    // 显示条件
    show: {
      type: Boolean,
      default: false
    },
    // 是否支持多文件上传
    multiple: {
      type: Boolean,
      default: false
    },
    // 显示已上传列表
    showUploadList: {
      type: Boolean,
      default: true
    },
    // 自动上传
    autoUpload: {
      type: Boolean,
      default: true
    },
    // 标题
    title: {
      type: String,
      default: '文件上传'
    }
  },
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  #upload{

  }
</style>
