<!-- 侧边栏 -->
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
    <div class="side-bar-position border-right" @click="toggleSideBar">
      <div class="router-map"></div>
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
  methods: {
    toggleSideBar () {
      this.$emit('change', this.sideClose)
    },
    /**
     * @description: 当前位置
     * @author: chenxiaoxi
     * @date: 2018-09-18 15:05:38
     */
    setRouterMap (router) {
      let routerMap = []
      for (let i = 0; i < router.matched.length; i++) {
        routerMap.push(router.matched[i].meta.title)
      }
      routerMap = routerMap.join(',')
      let mapString = ''
      let routerParent = ''
      let routerSplit = ''
      let routerCurrnet = ''
      for (let i = 0; i < routerMap.length; i++) {
        let splitIndex = routerMap.indexOf(',')
        if (i < splitIndex) {
          routerParent += routerMap[i] + '<br />'
        } else if (i === splitIndex) {
          routerSplit += '<span class="iconfont" style="font-size:12px;color:#CCC;display: inline-block;width: calc(100% - 5px);text-align: center">&#xe61a;</span><br />'
        } else if (i === routerMap.length) {
          routerCurrnet += routerMap[i]
        } else {
          routerCurrnet += routerMap[i] + '<br />'
        }
      }
      routerParent = '<span class="router-map-parent">' + routerParent + '</span>'
      routerCurrnet = '<span class="router-map-current">' + routerCurrnet + '</span>'
      mapString = routerParent + routerSplit + routerCurrnet
      $('.side-bar-position div').html(mapString)
    }
  },
  model: {
    prop: 'sideClose',
    event: 'change'
  },
  props: {
    sideClose: Boolean,
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
  },
  watch: {
    '$route': {
      deep: true,
      handler (val) {
        this.setRouterMap(val)
      }
    }
  },
  mounted () {
    this.setRouterMap(this.$route)
  }
}
</script>

<style lang="less" scoped>
  @import '~styles/style';
  @import '~styles/mixins';
  .side-bar{
    height: calc(~'100% - 7.8% - 10px');
    float: left;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    position: absolute;
    left: 0;
    transition: all .3s linear;
    &:hover{
      .side-bar-left{
        box-shadow: -1px 17px 20px 0px #CCC;
      }
    }
    .side-bar-left{
      width: 230px;
      height: 100%;
      border-top: 1px solid #EDEDED;
      border-right: 1px solid #EDEDED;
      background: @sideBarColor;
      transition: box-shadow .3s linear;
      float: left;
      transition: all .3s linear;
      overflow: hidden;
      .content{
        height: 100%;
        margin: 0 auto;
        .user-info{
          width: 180px;
          margin: 0 auto;
          height: 140px;
          padding: 40px 0;
          box-sizing: border-box;
          .user-avator{
            width: 60px;
            height: 60px;
            background: #DDD;
            box-sizing: border-box;
            display: inline-block;
            img{
              width: 100%;
              height: 100%;
            }
          }
          .user-info-box{
            padding: 0 25px;
            box-sizing: border-box;
            height: 100%;
            display: inline-block;
          }
        }
        .nav-list{
          padding-left: 15px;
          width: calc(~'100% - 40px');
          ul{
            li{
              width: 100%;
              height: 40px;
              line-height: 40px;
              cursor: pointer;
              &.active{
                padding-left: 14px;
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
      width: 40px;
      height: 200px;
      color: #333;
      font-size: 12.5px;
      border-top: 1px solid #EDEDED;
      border-bottom: 1px solid #EDEDED;
      background: #FFF;
      float: left;
      cursor: pointer;
      display: flex;
      display: -ms-flex;
      justify-content: center;
      align-items: center;
      position: relative;
      left: -1px;
      .router-map{
        width: 100%;
        color: #AAA;
      }
    }
  }
</style>
