<!-- 登录页 -->
<template>
  <div class="login">
    <div class="logo">
      <img :src="logo" alt="logo">
    </div>
    <div class="login-content">
      <div class="login-form" @keyup.enter="signin">
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
      <div class="comp">
        <img :src="logoSm" alt="logo_sm">
      </div>
      <div class="system-text">
        <div class="system"></div>
        <div class="line"></div>
        <span>打造法规标准信息化应用平台</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  name: 'login',
  data () {
    return {
      logo: require('assets/images/login/login-logo-top.png'), // logo
      bgImage: require('assets/images/login/login-bg.png'), // 登录页背景
      loginBtn: require('assets/images/login/login-btn.png'), // 登录按钮,
      logoSm: require('assets/images/home/logo_sm.png'),
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
      if (this.username === '') {
        this.$Message.warning('用户名不能为空')
      } else if (this.password === '') {
        this.$Message.warning('密码不能为空')
      } else {
        if (this.username === 'admin' && this.password === '123456') {
          this.setToken('isLogin')
          this.$router.push('/')
        } else {
          this.$Message.warning('用户名:admin 密码:123456')
        }
      }
    },
    /**
     * @description: vuex拓展方法
     * @author: chenxiaoxi
     * @date: 2018-09-13 10:25:43
     */
    ...mapMutations(['setToken'])
  },
  mounted () {
    $('.system').stop().animate({ left: 0, opacity: 1 }, 2000)
    $('.login-footer span').stop().animate({ right: 0, opacity: 1 }, 2000)
  }
}
</script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  .login{
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    background-size: 100%;
    background-repeat: no-repeat;
    position: relative;
    background: url("../../assets/images/login/login-bg.png");
    .logo{
      width: 7.5rem;
      padding: 0.36rem 0 0 0.64rem;
      img{
        width: 100%;
      }
    }
    .login-content{
      width: 14.32rem;
      height: 2.8rem;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -65%);
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
            font-size: 0.28rem;
            color: #313131;
            border-right: 1px solid #ECEDED;
            padding: 0 0.3rem  0 0.1rem;
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
      width: 16rem ;
      height: 2.2rem ;
      position: absolute;
      left: 50%;
      transform: translate(-50%);
      bottom: 2.2rem;
      animation: slideInText 500 linear forwards;
      color: #FFF;
      box-sizing: border-box;
      .comp{
        width: 5.5rem;
        height: 0.8rem;
        margin: 0 auto;
        img{
          width: 100%;
        }
        .flex-center;
      }
      .system-text{
        width: 100%;
        font-size: 0.55rem;
        display: flex;
        justify-content: center;
        margin-top: 0.5rem;
        .system{
          flex: 0 0 3.54rem;
          height: 0.8rem;
          float: left;
          background: url("../../assets/images/login/login-system.png") center center no-repeat;
          background-size: 80%;
          position: relative;
          left: -0.4rem;
          opacity: .5;
        }
        .line{
          width: 0;
          height: 0.8rem;
          border-right: 2px solid #FFF;
          margin-right: 0.2rem;
        }
        span{
          position: relative;
          right: -0.4rem;
          opacity: .5;
        }
      }
    }
  }
</style>
