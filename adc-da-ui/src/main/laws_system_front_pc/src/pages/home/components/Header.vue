<template>
  <div class="com-header">
    <div class="logo-wrapper">
      <Row>
        <Col span="24">
          <img :src="logo" alt="logo" class="logo-img">
        </Col>
      </Row>
    </div>
    <div class="user-info">
      <div class="user-avator"></div>
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
      logo: require('assets/logo.png')
    }
  }
}
</script>

<style lang="less" scoped>
  @import '~styles/style';
  .com-header{
    position: relative;
    .logo-wrapper{
      width: 31.28rem;
      .logo-img{
        width: 100%;
      }
    }
    .user-info{
      position: absolute;
      top: 0;
      right: 0;
      width: 16.35rem;
      height: 100%;
      display: flex;
      align-items: center;
      .user-avator{
        width: 2.85rem;
        height: 2.85rem;
        background: #DDD;
        box-sizing: border-box;
      }
      .info-box{
        padding-left: 1.5rem;
        box-sizing: border-box;
        position: relative;
        dd{
          font-size: 1.2rem;
        }
      }
    }
  }
</style>
