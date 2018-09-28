<!-- 标准团队 -->
<template>
  <div id="standardTeam">
    <com-header></com-header>
    <div class="standard-team-content">
      <div class="standard-team-content-detail">
        <Upload
          :show-upload-list="false"
          type="drag"
          action="//jsonplaceholder.typicode.com/posts/">
          <div style="padding: 20px 0">
            <Icon type="ios-image-outline" size="52" style="color: #3399ff"></Icon>
            <p>图片上传</p>
          </div>
        </Upload>
      </div>
    </div>
    <div class="standard-team-footer">
      <Button type="error">删除</Button>
      <Button type="primary">保存</Button>
    </div>
  </div>
</template>

<script>
import ComHeader from 'pages/components/Header'
export default {
  name: 'standardTeam',
  data () {
    return {}
  },
  methods: {},
  components: {
    ComHeader
  },
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  #standardTeam{
    .standard-team-content{
      height: calc(~'100% - 120px');
      background: #e8e8e8;
      padding: 10px;
      .standard-team-content-detail{
        width: 100%;
        height: calc(~'100% - 20px');
        .ivu-upload{
          width: 100%;
          height: 100%;
          .flex();
          align-items: center;
          justify-content: center;
        }
      }
    }
    .standard-team-footer{
      width: 100%;
      position: absolute;
      bottom: 0;
      left: 0;
      border-top: 1px solid #e8e8e8;
      height: 60px;
      text-align: right;
      background: #fff;
      padding: 0 10px;
      .flex();
      justify-content: flex-end;
      align-items: center;
      .ivu-btn{
        margin-right: 5px;
      }
    }
  }
</style>
