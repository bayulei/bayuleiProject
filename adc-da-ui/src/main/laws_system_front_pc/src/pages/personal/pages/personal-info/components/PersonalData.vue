<!-- 个人资料 -->
<template>
  <div class="personal-data">
    <div class="user-avator">
      <div class="img-warpper">
        <img :src="avator" alt="user-avator">
      </div>
      <input type="file" v-show="false" ref="avatorUploadBtn" id="avatorUploadBtn" @change="uploadAvator">
      <input type="button" class="btn-avator primary-btn" value="修改头像" @click="chooseAvator">
      <div class="tips"><span class="require">*</span> 图片大小不能超过2M</div>
    </div>
    <div class="user-info-form">
      <Form ref="userInfo" :model="userInfo" :rules="userInfoRules" :label-width="80" class="label-input-form">
        <FormItem label="用户名" prop="username" class="user-info-item">
          <Input v-model="userInfo.username"></Input>
        </FormItem>
        <FormItem label="姓 名" prop="cname" class="user-info-item">
          <Input v-model="userInfo.cname"></Input>
        </FormItem>
        <FormItem label="任职部门" prop="department" class="user-info-item">
          <Input v-model="userInfo.department"></Input>
        </FormItem>
        <FormItem label="电 话" prop="phone" class="user-info-item">
          <Input v-model="userInfo.phone"></Input>
        </FormItem>
        <FormItem label="个人邮箱" prop="email" class="user-info-item">
          <Input v-model="userInfo.email"></Input>
        </FormItem>
        <FormItem label="手机号码" prop="mobile" class="user-info-item">
          <Input v-model="userInfo.mobile"></Input>
        </FormItem>
        <FormItem label="传 真" prop="fax" class="user-info-item">
          <Input v-model="userInfo.fax"></Input>
        </FormItem>
        <FormItem label="个性签名" prop="autograph" class="user-info-item">
          <Input v-model="userInfo.autograph"></Input>
        </FormItem>
      </Form>

      <input type="button" value="保存修改" class="save primary-btn">
    </div>
  </div>
</template>

<script>
export default {
  name: 'personal-data',
  data () {
    return {
      avator: require('assets/images/user-big-avator.png'),
      userInfo: {
        username: '', // 用户名
        cname: '', // 姓名
        department: '', // 任职部门
        phone: '', // 电话
        email: '', // 邮箱
        mobile: '', // 手机
        fax: '', // 传真
        autograph: '' // 个性签名
      },
      userInfoRules: {
        phone: [
          { required: true, message: '电话不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '个人邮箱不能为空', trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '手机号码不能为空', trigger: 'blur' }
        ],
        fax: [
          { required: true, message: '传真不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 选取图片
    chooseAvator () {
      document.getElementById('avatorUploadBtn').click()
    },
    /**
     * @description: 头像上传
     * @author: xx
     * @date: 2018-08-30 16:06:06
     */
    uploadAvator () {
      let avator = this.$refs.avatorUploadBtn.files[0]
      console.log(avator)
    },
    searchPersonal () {
      this.$http.get('person/userInfo', {}, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.userInfo.username = res.data.count
      }, e => {})
    }
  },
  mounted () {
    this.searchPersonal()
  }
}
</script>

<style lang="less">
  .personal-data{
    width: 100%;
    height: 100%;
    .user-avator{
      width: 4.32rem;
      height: 6.26rem;
      box-sizing: border-box;
      margin: 1.2rem 0 0 2rem;
      float: left;
      .img-warpper{
        width: 100%;
        height: 4.24rem;
      }
      .btn-avator{
        width: 100%;
        height: 0.72rem;
        line-height: 0.72rem;
        margin-top: 0.4rem;
      }
    }
    .user-info-form{
      width: 16.28rem;
      float: left;
      min-height: 400px;
      margin: 1.2rem 0 0 1.75rem;
      .save{
        width: 4.24rem;
        height: 0.72rem;
        line-height: 0.72rem;
        margin: 0.5rem 0 0 0.2rem;
      }
      .user-info-item{
        .ivu-input{
          width: 250px;
        }
        &:last-child{
          .ivu-input{
            width: 400px;
          }
        }
      }
    }
  }
</style>
