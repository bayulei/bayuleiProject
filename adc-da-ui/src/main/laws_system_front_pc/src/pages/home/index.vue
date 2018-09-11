<template>
  <div class="home">
    <div class="home-header">
      <div class="logo"></div>
      <ul class="home-nav">
        <router-link tag="li" v-for="topNav in topNavList" :key="topNav.name" :to="topNav.path">{{ topNav.title }}</router-link>
      </ul>
      <div class="header-right"></div>
    </div>
    <!-- 搜索框 -->
    <div class="search-box">
      <div class="search-option">主题</div>
      <div class="search-input">
        <Input type="text" v-model="keywords" autofocus id="search-input" :placeholder="searchRecommend" clearable></Input>
      </div>
      <div class="search-btn">
        <Icon type="ios-search" :size="34" color="rgba(0,0,0,.8)" />
      </div>
      <div class="search-text">{{ searchText }}</div>
    </div>
    <!-- 导航 -->
    <div class="nav">
      <div class="time-box">{{ currentTime }}</div>
      <div class="nav-content">
        <div class="nav-content-left">
          <div class="nav-group nav-top">
            <div class="nav-top-left">
              <div class="nav-item router-item">分析工具</div>
              <div class="nav-item router-item">数据分析</div>
            </div>
            <router-link tag="div"  class="nav-top-right router-item" to="/statuteBank">本地标准法规库</router-link>
            <!--<router-link tag="div" class="nav-item router-item" to="/config">-->
          </div>
          <div class="nav-group nav-top">
            <div class="nav-top-item router-item">资源中心</div>
            <div class="nav-top-item router-item">国内动态</div>
          </div>
          <div class="nav-group nav-bottom">
            <div class="nav-bottom-left router-item">待办任务</div>
            <div class="nav-bottom-right">
              <div class="nav-bottom-right-item">
                <div class="nav-item router-item">标准团队</div>
                <div class="nav-item router-item">云端资源库</div>
              </div>
              <div class="nav-bottom-right-item">
                <div class="nav-item router-item">流程中心</div>
                <router-link tag="div" class="nav-item router-item" to="/config">配置管理</router-link>
              </div>
            </div>
          </div>
        </div>
        <div class="nav-content-right">
          <div class="nav-top">
            <div class="nav-item router-item">联系我们</div>
            <router-link tag="div" class="nav-item router-item" to="/personal">欢迎您，张三</router-link>
          </div>
          <div class="nav-middle router-item">友情链接</div>
          <div class="nav-bottom">
            <div class="nav-item router-item">同时在线人数</div>
            <div class="nav-item router-item">累计登录总人数</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'home',
  data () {
    return {
      searchText: '标准高级检索',
      searchRecommend: '中文文献、外文文献 (elsevier、Springer、wiley......)',
      keywords: '', // 搜索关键词
      currentTime: '',
      topNavList: [
        {
          title: '首页',
          path: '/',
          name: 'home'
        },
        {
          title: '云端资源库',
          path: '/yunduanziyuanku',
          name: 'yunduanziyuanku'
        },
        {
          title: '本地资源库',
          path: '/bendiziyuanku',
          name: 'bendiziyuanku'
        },
        {
          title: '数据报表',
          path: '/shujubaobiao',
          name: 'shujubaobiao'
        },
        {
          title: '分析工具',
          path: '/fenxigongju',
          name: 'fenxigongju'
        },
        {
          title: '配置管理',
          path: '/config',
          name: 'Config'
        }
      ]
    }
  },
  methods: {
    /**
     * @description: 获取当前时间
     * @author: chenxiaoxi
     * @date: 2018-09-02 10:59:50
     */
    getTime () {
      setInterval(() => {
        this.currentTime = this.$dateFormat(new Date(), 'yyyy-MM-dd hh:mm:ss') + ' 星期' + '日一二三四五六'.charAt(new Date().getDay())
      }, 1000)
    }
  },
  mounted () {
    this.getTime()
  }
}
</script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  .home{
    width: 100%;
    height: 100%;
    background: url("~assets/images/home/bg.png") no-repeat;
    background-position: top center;
    background-attachment: fixed;
    background-size: cover;
    position: relative;
    .home-header{
      height: 7.5%;
      background: #DDD;
      display: flex;
      align-items: center;
      .logo{
        width: 22.7%;
        height: 58.75%;
        background: url(/static/img/logo.26121a7.png) no-repeat;
        background-position: left center;
        background-size: 85%;
        cursor: pointer;
      }
      .home-nav{
        li{
          float: left;
        }
      }
    }
    .search-box{
      width: 45.8%;
      height: 5.55%;
      margin: 8% auto 0 auto;
      display: flex;
      &>div{
        height: 100%;
      }
      .search-option{
        flex: 0 0 13.63%;
        color: #515151;
        font-size: .32rem;
        background: rgba(255,255,255,.8);
        border: 1px solid #B3C3C7;
        .flex-center;
      }
      .search-input{
        flex: 0 0 55%;
        background: rgba(255,255,255,.8);
        border: 1px solid #B3C3C7;
        border-left: transparent;
        .ivu-input-wrapper{
          height: 100%;
          display: flex;
          align-items: center;
          .ivu-input{
            height: 100%;
            background: transparent;
            border: none !important;
            box-shadow: none;
            padding: 0 .4rem;
            font-size: .32rem;
          }
        }
      }
      .search-btn{
        flex: 0 0 13.63%;
        background: @bgColor;
        cursor: pointer;
        &:hover{
          opacity: .8;
        }
        .flex-center;
      }
      .search-text{
        .flex-center;
        color: #FEFEFE;
        font-size: .32rem;
        flex: 0 0 calc(~'100% - 82.26%');
        position: relative;
        top: 5px;
        left: 5px;
      }
    }
    .nav{
      width: 54.2%;
      height: 61%;
      margin: 3.5% auto 0 auto;
      .time-box{
        display: flex;
        justify-content: flex-end;
        color: #FEFEFE;
        margin-right: 5px;
        height: 30px;
        line-height: 30px;
      }
      .nav-content{
        height: calc(~'100% - 30px');
        display: flex;
        color: #FFF;
        .nav-content-left{
          flex: 0 0 64%;
          margin-right: 4%;
          height: 100%;
          .nav-group{
            width: 100%;
            height: 25%;
          }
          .nav-top{
            height: 23.5%;
            margin-bottom: 1.5%;
            display: flex;
            .nav-top-item{
              flex: 1;
              margin-right: 1.5%;
              &:nth-child(1){
                background: @boxColor3;
              }
              &:nth-child(2){
                background: @boxColor1;
                margin-right: 0;
              }
            }
            &:nth-child(1){
              display: flex;
              .nav-top-left{
                flex: 1;
                margin-right: 1.5%;
                display: flex;
                .nav-item{
                  flex: 1;
                  margin-right: 1.5%;
                  &:nth-child(1){
                    background: @boxColor2;
                  }
                  &:nth-child(2){
                    background: @bgColor;
                    margin-right: 0;
                  }
                }
              }
              .nav-top-right{
                flex: 1;
                background: @boxColor3;
              }
            }
          }
          .nav-bottom{
            width: 100%;
            height: 50%;
            display: flex;
            .nav-bottom-left{
              flex: 1;
              height: 100%;
              background: #23A3DC;
              margin-right: 1.5%;
            }
            .nav-bottom-right{
              flex: 1;
              height: 100%;
              .nav-bottom-right-item{
                height: 49.25%;
                margin-bottom: 1.5%;
                display: flex;
                &:last-child{
                  margin-bottom: 0;
                  .nav-item{
                    &:nth-child(1){
                      background: @boxColor2;
                    }
                    &:nth-child(2){
                      background: @boxColor4;
                    }
                  }
                }
                &:nth-child(1){
                  .nav-item{
                    &:nth-child(1){
                      background: @boxColor3;
                    }
                    &:nth-child(2){
                      background: @boxColor5;
                    }
                  }
                }
                .nav-item{
                  flex: 1;
                  margin-right: 1.5%;
                  &:last-child{
                    margin-right: 0;
                  }
                }
              }
            }
          }
        }
        .nav-content-right{
          flex: 0 0 32%;
          height: 100%;
          .nav-top{
            width: 100%;
            height: 23.5%;
            margin-bottom: 2.5%;
            display: flex;
            .nav-item{
              flex: 0 0 49%;
              &:nth-child(1){
                background: @boxColor5;
                margin-right: 2%;
              }
              &:nth-child(2){
                background: #CCC;
              }
            }
          }
          .nav-middle{
            width: 100%;
            height: 50%;
            background: @boxColor2;
          }
          .nav-bottom{
            width: 100%;
            height: 23.5%;
            margin-top: 2.5%;
            display: flex;
            .nav-item{
              flex: 0 0 49%;
              &:nth-child(1){
                background: @bgColor;
                margin-right: 2%
              }
              &:nth-child(2){
                background: @boxColor3;
              }
            }
          }
        }
      }
    }
    .router-item{
      cursor: pointer;
      &:hover{
        .flex-center;
      }
    }
  }
</style>
