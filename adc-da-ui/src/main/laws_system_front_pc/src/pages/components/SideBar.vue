<!-- 个人中心侧边栏 -->
<template>
  <div class="side-bar">
    <div class="side-bar-left">
      <div class="content">
        <div class="user-info">
          <div class="user-avator">
            <img :src="userAvator" alt="user-avator">
          </div>
          <div class="user-info-box">
            <dl>
              <dt>{{ username }}</dt>
              <dd class="font-color">{{ role }}</dd>
            </dl>
          </div>
        </div>
        <div class="nav-list">
          <ul>
            <router-link tag="li" v-for="(nav, index) in navList"
              :key="index"
              :class="{ 'active': active === nav.path }"
              :to="nav.path"><span>{{ nav.title }}</span></router-link>
          </ul>
        </div>
      </div>
    </div>
    <div class="side-bar-position border-right">
      <!--首页 个人中心-->
    </div>
  </div>
</template>

<script>
export default {
  name: 'side-bar',
  data () {
    return {
      username: '管理员',
      role: '超级管理员',
      userAvator: require('assets/images/user-avator.png')
    }
  },
  props: {
    navList: {
      type: Array,
      required: true
    }
  },
  computed: {
    // 当前路由
    active () {
      return this.$route.path
    }
  }
}
</script>

<style lang="less" scoped>
  @import '~styles/style';
  .side-bar{
    width: 6rem;
    height: 100%;
    position: relative;
    float: left;
    &:hover{
      .side-bar-left{
        box-shadow: -1px 17px 20px 0px #CCC;
      }
    }
    .side-bar-left{
      width: 5.2rem;
      height: 100%;
      border-top: 1px solid #EDEDED;
      border-right: 1px solid #EDEDED;
      background: @sideBarColor;
      transition: box-shadow .3s linear;
      .content{
        width: 3.8rem;
        margin: 0 auto;
        .user-info{
          height: 2.8rem;
          padding: 0.8rem 0;
          box-sizing: border-box;
          .user-avator{
            width: 1.2rem;
            height: 1.2rem;
            background: #DDD;
            box-sizing: border-box;
            float: left;
            img{
              width: 100%;
            }
          }
          .user-info-box{
            float: left;
            padding: 0 0.5rem;
            box-sizing: border-box;
            height: 100%;
          }
        }
        .nav-list{
          ul{
            li{
              width: 100%;
              height: 0.8rem;
              line-height: 0.8rem;
              cursor: pointer;
              &.active{
                padding-left: 0.28rem;
                transition: all linear .2s;
                color: @baseColor;
                background: #F0F0F0;
                box-shadow: 2px 2px 5px #DDD;
                span{
                  text-shadow: 2px 2px 5px #DDD;
                  &::before{
                    display: none;
                  }
                }
              }
              &:hover{
                span{
                  &::before{
                    left: 0;
                    width: 100%;
                    transform: translate(0);
                  }
                }
              }
              span{
                position: relative;
                &::before{
                  content: '';
                  position: absolute;
                  width: 0;
                  left: 50%;
                  top: 125%;
                  transform: translate(-50%);
                  height: 2px;
                  background: @baseColor;
                  transition: all .2s ease-in-out;
                }
              }
            }
          }
        }
      }
    }
    .side-bar-position{
      width: 0.8rem;
      height: 4rem;
      writing-mode:lr-tb;
      color: #333;
      font-size: .4rem;
      margin: 0 auto;
      border-top: 1px solid #EDEDED;
      border-bottom: 1px solid #EDEDED;
      position: absolute;
      top: 0;
      right: 1px;
      background: #FFF;
    }
  }
</style>
