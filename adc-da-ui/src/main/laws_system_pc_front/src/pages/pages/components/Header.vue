<template>
  <div class="com-header">
    <div class="logo-wrapper">
      <img :src="logo" alt="logo" class="logo-img">
    </div>
    <div class="user-info">
      <div class="user-avator">
        <img :src="userAvator" alt="user-avator">
      </div>
      <div class="info-box">
        <dl>
          <dt>2018/08/28 12:00:00</dt>
          <dd>欢迎您,管理员</dd>
        </dl>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'home-header',
  data () {
    return {
      logo: require('assets/images/home/logo.png'),
      userAvator: require('assets/images/user-avator.png')
    }
  }
}
</script>

<style lang="less" scoped>
  @import '~styles/style';
  .com-header{
    position: relative;
    .logo-wrapper{
      width: 6rem;
      .logo-img{
        width: 100%;
      }
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
        dd{
          font-size: 0.35rem;
        }
      }
    }
  }
</style>
