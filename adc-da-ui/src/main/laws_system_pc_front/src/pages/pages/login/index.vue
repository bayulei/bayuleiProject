<!-- 登录页 -->
<template>
  <div class="login" :style="{ backgroundImage: 'url(' + bgImage + ')' }">
    <div class="logo">
      <img :src="logo" alt="logo">
    </div>
    <div class="login-content">
      <div class="login-form">
        <div class="username">
          <div class="label">用户名</div>
          <div class="login-input">
            <Input v-model="username" :maxlength="20" clearable></Input>
          </div>
          <Icon type="md-help-circle" :size="20" color="#9BA5AD" title="还没注册?" />
        </div>
        <div class="password">
          <div class="label">密&nbsp;&nbsp;&nbsp;码</div>
          <div class="login-input">
            <Input type="password" v-model="password" :maxlength="30" clearable></Input>
          </div>
          <Icon type="md-lock" :size="20" color="#9BA5AD" title="忘记密码?" />
        </div>
      </div>
      <div class="login-btn" @click="signin">
        <img src="../../assets/images/login/login-btn.png" alt="">
      </div>
    </div>
    <div class="login-footer">
      <div class="system"></div>
      <div class="system-text">打造法规标准信息化应用平台</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      logo: require('assets/images/login/login-logo-top.png'), // logo
      bgImage: require('assets/images/login/login-bg.png'), // 登录页背景
      loginBtn: require('assets/images/login/login-btn.png'), // 登录按钮,
      username: '',
      password: ''
    }
  },
  methods: {
    /**
     * @description: 登录
     * @author: xx
     * @date: 2018-08-30 19:18:30
     */
    signin () {
      this.$router.push('/')
    }
  },
  mounted () {
    $('.login-footer').animate({ bottom: '2rem' }, 2000)
    $('.system-text').animate({ opacity: 1 }, 5000)
  }
}
</script>

<style lang="less">
  @import '~styles/style';
  .login{
    width: 100%;
    height: 100%;
    padding: 0.36rem 0 0 0.64rem;
    box-sizing: border-box;
    background-size: 100%;
    background-repeat: no-repeat;
    position: relative;
    .logo{
      width: 8.6rem * @zoom;
      img{
        width: 100%;
      }
    }
    .login-content{
      width: 16rem * @zoom;
      height: 3rem * @zoom;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      background: #FBFCFD;
      box-sizing: border-box;
      .login-form{
        float: left;
        width: calc(~'100% - 1.5rem');
        height: 100%;
        padding: 0.2rem 0.45rem;
        &>div{
          height: 50%;
          display: flex;
          align-items: center;
          border-bottom: 1px solid #C0BFC0;
          &:last-child{
            border-bottom: transparent;
          }
          .label{
            font-size: 0.36rem * @zoom;
            color: #313131;
            border-right: 1px solid #ECEDED;
            padding: 0 0.3rem * @zoom 0 0.1rem;
          }
          .ivu-icon{
            cursor: pointer;
          }
          .login-input{
            padding: 0 0.4rem;
            height: 1rem;
            line-height: 1rem;
            flex: 1;
            .ivu-input{
              background-color: transparent;
              border: none;
              padding: 0;
              &:focus{
                box-shadow: none;
              }
            }
          }
        }
      }
      .login-btn{
        width: 1.5rem;
        height: 100%;
        background: #333;
        float: left;
        opacity: .9;
        &:hover{
          cursor: pointer;
          opacity: 1;
        }
        img{
          width: 100%;
          height: 100%;
        }
      }
    }
    .login-footer{
      width: 16rem * @zoom;
      height: 2.2rem * @zoom;
      position: absolute;
      left: 50%;
      transform: translate(-50%);
      bottom: 1rem;
      animation: slideInText 500 linear forwards;
      color: #FFF;
      box-sizing: border-box;
      .system{
        width: 100%;
        height: 1.5rem * @zoom;
        background: url("../../assets/images/login/login-system.png") center center no-repeat;
        background-size: 25%;
      }
      .system-text{
        width: 100%;
        height: 0.72rem * @zoom;
        font-size: 0.5rem;
        text-align: center;
        opacity: 0.1;
      }
    }
  }
</style>
