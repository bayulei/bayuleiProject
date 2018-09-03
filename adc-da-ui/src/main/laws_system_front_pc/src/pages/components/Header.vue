<template>
  <div class="com-header">
    <router-link tag="div" class="logo" title="回到首页" to="/" />
    <div class="user-info">
      <div class="user-avator">
        <img :src="userAvator" alt="user-avator">
      </div>
      <div class="info-box">欢迎您，{{ username }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'home-header',
  data () {
    return {
      username: '张三',
      userAvator: require('assets/images/user-avator.png')
    }
  }
}
</script>

<style lang="less" scoped>
  @import '~styles/style';
  .com-header{
    width: 100%;
    position: relative;
    height: 1.6rem;
    background: @bgColor;
    display: flex;
    align-items: center;
    padding-left: 0.4rem;
    .logo{
      width: 22.7%;
      height: 58.75%;
      background: url("~assets/images/home/logo.png") no-repeat;
      background-position: left center;
      background-size: 85%;
      cursor: pointer;
    }
    .user-info{
      position: absolute;
      top: 0;
      right: 0;
      width: 4.56rem;
      height: 100%;
      display: flex;
      align-items: center;
      .user-avator{
        width: 0.8rem;
        height: 0.8rem;
        background: #DDD;
        box-sizing: border-box;
        img{
          width: 100%;
        }
      }
      .info-box{
        padding-left: 0.45rem;
        box-sizing: border-box;
        position: relative;
        color: #FFF;
        font-size: .32rem;
        dd{
          font-size: 0.35rem;
        }
      }
    }
  }
</style>
