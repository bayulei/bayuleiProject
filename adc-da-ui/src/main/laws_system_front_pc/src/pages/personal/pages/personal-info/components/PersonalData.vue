<!-- 个人资料 -->
<template>
  <div class="personal-data">
    <div class="user-avator">
      <div class="img-warpper">
        <img :src="avator" alt="user-avator">
      </div>
      <input type="file" accept="image/*" v-show="false" ref="avatorUploadBtn" id="avatorUploadBtn" @change="uploadAvator">
      <input type="button" class="btn-avator primary-btn" value="修改头像" @click="chooseAvator">
      <div class="tips"><span class="require">*</span> 图片大小不能超过2M</div>
    </div>
    <div class="user-info-form">
      <Form ref="userInfo" :model="userInfo" :rules="userInfoRules" :label-width="80" class="label-input-form">
        <FormItem label="用户名" prop="account" class="user-info-item">
          <Input v-model="userInfo.account" disabled></Input>
        </FormItem>
        <FormItem label="姓 名" prop="uName" class="user-info-item">
          <Input v-model="userInfo.uName" disabled></Input>
        </FormItem>
        <FormItem label="任职部门" prop="duty" class="user-info-item">
          <Input v-model="userInfo.duty" disabled></Input>
        </FormItem>
        <FormItem label="电 话" prop="officePhone" class="user-info-item">
          <Input v-model="userInfo.officePhone"></Input>
        </FormItem>
        <FormItem label="个人邮箱" prop="email" class="user-info-item">
          <Input v-model="userInfo.email"></Input>
        </FormItem>
        <FormItem label="手机号码" prop="mobilePhone" class="user-info-item">
          <Input v-model="userInfo.mobilePhone"></Input>
        </FormItem>
        <FormItem label="传 真" prop="faxAddress" class="user-info-item">
          <Input v-model="userInfo.faxAddress"></Input>
        </FormItem>
        <FormItem label="个性签名" prop="extInfo" class="user-info-item">
          <Input v-model="userInfo.extInfo"></Input>
        </FormItem>
      </Form>

      <input type="button" value="保存修改" class="save primary-btn" @click="savePersonal">
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
        id: '', // 数据ID
        account: '', // 用户名
        uName: '', // 姓名
        duty: '', // 任职部门
        officePhone: '', // 电话
        email: '', // 邮箱
        mobilePhone: '', // 手机
        faxAddress: '', // 传真
        extInfo: '', // 个性签名
        userPic: ''// 用户头像
      },
      userInfoRules: {
        officePhone: [
          { required: true, message: '电话不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '个人邮箱不能为空', trigger: 'blur' }
        ],
        mobilePhone: [
          { required: true, message: '手机号码不能为空', trigger: 'blur' }
        ],
        faxAddress: [
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
      this.$http.post('/att/attFile/upload', {
        file: avator },
      {_this: this}, res => {
        console.log(res)
        let attVo = res.data
        this.avator = '/uploadPath' + attVo.filePath + attVo.fileName
        this.userInfo.userPic = attVo.id
      })
    },
    // 根据文件ID获取文件信息并将回显
    getUserPicInfo (attId) {
      this.$http.get('/att/attFile/' + attId, {}, {_this: this}, res => {
        console.log(res)
        let attVo = res.data
        this.avator = '/uploadPath' + attVo.filePath + attVo.fileName
      })
    },
    // 保存用户信息
    saveUserInfo () {
      this.$http.putData('person/userInfo',
        {
          id: this.userInfo.id,
          officePhone: this.userInfo.officePhone,
          email: this.userInfo.email,
          mobilePhone: this.userInfo.mobilePhone,
          faxAddress: this.userInfo.faxAddress,
          signature: this.userInfo.signature,
          userPic: this.userInfo.userPic
        }, {_this: this}, res => {
          console.log(res)
        })
    },
    searchPersonal () {
      this.$http.get('person/userInfo/getByUserInfoCode', {
        userId: 'QJX2Z8E678'
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.userInfo = res.data
        if (res.data.userPic !== null) {
          this.getUserPicInfo(res.data.userPic)
        }
      }, e => {})
    },
    savePersonal () {
      this.$http.post('', this.userInfo.userPicid, {
        _this: this
      }, res => {
        this.searchPersonal()
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
      margin: -5.6rem 0 0 7.75rem;
      .save{
        width: 4.24rem;
        height: 0.72rem;
        line-height: 0.72rem;
        margin: 0.5rem 0 0 0.2rem;
      }
      .user-info-item{
        margin-bottom: 12px;
        vertical-align: top;
        zoom: 1;
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
