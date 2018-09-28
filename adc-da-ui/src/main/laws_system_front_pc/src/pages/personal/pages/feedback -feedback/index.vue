<template>
 <div id="feedback-feedback">
   <UEditor :config=config ref="ueditor"></UEditor>
   <Button type="primary" style="margin: 0.5rem 9rem" @click="saveFeedback">提交</Button>
 </div>
</template>

<script>
export default {
  name: 'feedback-feedback',
  data () {
    return {
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
      }
    }
  },
  methods: {
    saveFeedback () {
      console.log(this.$refs.ueditor.getUEContent())
      let feedBackInfo = this.$refs.ueditor.getUEContent()
      this.$http.post('sys/feedbackInfo/save', {
        feedBackInfo: feedBackInfo
      }, {
        _this: this
      }, res => {
      }, e => {
      })
    }
  },
  components: {},
  mounted () {}
}
</script>

<style lang="less">
   #feedback-feedback{}
</style>
