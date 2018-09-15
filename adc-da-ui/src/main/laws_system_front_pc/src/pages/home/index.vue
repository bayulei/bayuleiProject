<template>
  <div class="home">
    <div class="home-header">
      <!-- logo -->
      <div class="logo"></div>
      <!-- 顶部导航 -->
      <ul class="home-nav">
        <router-link tag="li" v-for="topNav in topNavList" :key="topNav.name" :to="topNav.path">{{ topNav.title }}</router-link>
      </ul>
      <!-- 用户信息 -->
      <div class="header-right">
        <div class="time-box">{{ currentTime }}</div>
        <div class="user-info">
          <img :src="userAvator" alt="avator">
          <Dropdown trigger="click">
            <span>欢迎您，张三 <Icon type="ios-arrow-down"></Icon></span>
            <DropdownMenu slot="list">
              <DropdownItem><router-link tag="span" to="/personal">个人中心</router-link></DropdownItem>
              <DropdownItem>退出</DropdownItem>
            </DropdownMenu>
          </Dropdown>
        </div>
      </div>
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
      <div class="nav-content">
        <div class="nav-content-left">
          <!-- 国内外标准法规库 -->
          <router-link tag="div" class="nav-left-top nav-item" id="standardRegulationLibrary" to="/statuteBank">
            <div class="nav-icon iconfont">&#xe656;</div>
            <h5>国内外标准法规库</h5>
          </router-link>
          <!-- 企业标准库 -->
          <div class="nav-left-top nav-item" id="enterpriseStandardLaw">
            <div class="nav-icon iconfont">&#xe6ee;</div>
            <h5>企业标准库</h5>
          </div>
          <!-- 流程中心 -->
          <router-link tag="div" class="nav-left-top nav-item" id="processCenter" to="/processCenter">
            <div class="nav-icon iconfont">&#xe634;</div>
            <h5>流程中心</h5>
          </router-link>
          <!-- 标准团队 -->
          <div class="nav-left-top nav-item" id="standardTeam">
            <div class="nav-icon iconfont">&#xe600;</div>
            <h5>标准团队</h5>
          </div>
          <!-- 待办任务 -->
          <div class="nav-left-bottom" id="pendingTask">
            <h5>待办任务</h5>
            <ul>
              <li v-for="needTodo in todoList" :key="needTodo.id">{{ needTodo.content }}</li>
            </ul>
          </div>
        </div>
        <div class="nav-content-right">
          <!-- 国内动态、国外动态、实时预警 -->
          <div class="nav-right-top" id="dynamicEarlyWarning">
            <span class="more">更多</span>
            <Tabs size="small" @on-click="(name) => dynamicEarlyWarningTab = name">
              <TabPane label="国内动态" name="1">
                <ul>
                  <li v-for="item in domesticDynamics" :key="item.id">
                    <i v-if="item.id === '1001'">new</i>
                    <a>{{ item.content }}</a>
                    <span>{{ item.time }}</span>
                  </li>
                </ul>
              </TabPane>
              <TabPane label="国外动态" name="2">
                <ul>
                  <li v-for="item in foreignTrends" :key="item.id">
                    <i v-if="item.id === '1001'">new</i>
                    <a>{{ item.content }}</a>
                    <span>{{ item.time }}</span>
                  </li>
                </ul>
              </TabPane>
              <TabPane label="实时预警" name="3">
                <ul>
                  <li v-for="item in earlyWarning" :key="item.id">
                    <i v-if="item.id === '1001'">new</i>
                    <a>{{ item.content }}</a>
                    <span>{{ item.time }}</span>
                  </li>
                </ul>
              </TabPane>
            </Tabs>
          </div>
          <!-- 资料中心 -->
          <div class="nav-right-bottom" id="informationCenter">
            <h5>资料中心信息</h5>
            <span class="more">更多</span>
            <ul>
              <li v-for="item in informationData" :key="item.id">
                <span class="type">{{ item.type === 1 ? '新闻' : (item.type === 2 ? '通知' : (item.type === 3 ? '月报' : '资料')) }}</span>
                <a class="content">{{ item.content }}</a>
                <span class="time">{{ item.time }}</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="home-footer">
      <!-- 友情链接 -->
      <div class="friendshipLink">
        <span>友情链接:</span>
        <ul>
          <li v-for="(link, index) in link" :key="index"><a :href="link.url">{{ link.title }}</a></li>
        </ul>
      </div>
      <!-- 页尾 -->
      <div class="footer">
        <Row>
          <Col span="3" push="1"><span class="footer-border-right">同时在线人数</span> <a>{{ currentInline }}</a></Col>
          <Col span="3" push="1"><span class="footer-border-right total-inline">累计登录人数</span> <a>{{ totalInline }}</a></Col>
          <Col span="4" push="4"><img :src="logoSm" alt="logo"></Col>
          <Col span="4" push="4"><span class="contact-number">联系电话: {{ contactNumber }}</span></Col>
          <Col span="6" push="4"><span class="contact-address">地址: {{ contactAddress }}</span></Col>
        </Row>
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
      ],
      userAvator: require('assets/images/user-avator.png'),
      todoList: [
        {
          id: '1001',
          content: '10点半组织新员工培训10点半组织新员工培训10点半组织新员工培训10点半组织新员工培训10点半组织新员工培训'
        },
        {
          id: '1002',
          content: '12点公司楼下咖啡厅约见客户王经理'
        },
        {
          id: '1003',
          content: '14点税务局提交本月单据'
        },
        {
          id: '1004',
          content: '15点组织召开本周周会'
        },
        {
          id: '1005',
          content: '16点总结周会内容，发邮件给部门经理'
        },
        {
          id: '1006',
          content: '15点组织召开本周周会'
        },
        {
          id: '1007',
          content: '16点总结周会内容，发邮件给部门经理'
        },
        {
          id: '1008',
          content: '15点组织召开本周周会'
        },
        {
          id: '1009',
          content: '16点总结周会内容，发邮件给部门经理'
        }
      ], // 待办事项
      dynamicEarlyWarningTab: '', // 动态预警(tab值)
      domesticDynamics: [
        {
          id: '1001',
          content: '汽车法规国内动态0001汽车法规国内动态0001汽车法规国内动态0001汽车法规国内动态0001汽车法规国内动态0001',
          time: '08/28 12:00'
        },
        {
          id: '1002',
          content: '汽车法规国内动态0002',
          time: '08/28 12:00'
        },
        {
          id: '1003',
          content: '汽车法规国内动态0003',
          time: '08/28 12:00'
        },
        {
          id: '1004',
          content: '汽车法规国内动态0004',
          time: '08/28 12:00'
        }
      ], // 国内动态
      foreignTrends: [
        {
          id: '1001',
          content: '汽车法规国外动态0001',
          time: '08/28 12:00'
        },
        {
          id: '1002',
          content: '汽车法规国外动态0002',
          time: '08/28 12:00'
        },
        {
          id: '1003',
          content: '汽车法规国外动态0003',
          time: '08/28 12:00'
        },
        {
          id: '1004',
          content: '汽车法规国外动态0004',
          time: '08/28 12:00'
        }
      ], // 国外动态
      earlyWarning: [
        {
          id: '1001',
          content: '汽车法规实时预警0001',
          time: '08/28 12:00'
        },
        {
          id: '1002',
          content: '汽车法规实时预警0002',
          time: '08/28 12:00'
        },
        {
          id: '1003',
          content: '汽车法规实时预警0003',
          time: '08/28 12:00'
        },
        {
          id: '1004',
          content: '汽车法规实时预警0004',
          time: '08/28 12:00'
        }
      ], // 实时预警
      informationData: [
        {
          id: '1001',
          type: 1,
          content: '资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001',
          time: '08/28 12:00'
        },
        {
          id: '1002',
          type: 2,
          content: '资料信息中心新闻政策1002',
          time: '08/28 12:00'
        },
        {
          id: '1003',
          type: 3,
          content: '资料信息中心新闻政策1003',
          time: '08/28 12:00'
        },
        {
          id: '1004',
          type: 4,
          content: '资料信息中心新闻政策1004',
          time: '08/28 12:00'
        },
        {
          id: '1005',
          type: 1,
          content: '资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001',
          time: '08/28 12:00'
        },
        {
          id: '1006',
          type: 2,
          content: '资料信息中心新闻政策1002',
          time: '08/28 12:00'
        },
        {
          id: '1007',
          type: 3,
          content: '资料信息中心新闻政策1003',
          time: '08/28 12:00'
        },
        {
          id: '1008',
          type: 4,
          content: '资料信息中心新闻政策1004',
          time: '08/28 12:00'
        }
      ], // 资料中心信息
      link: [
        {
          title: '企业OA系统',
          url: '#'
        },
        {
          title: '邮箱系统',
          url: '#'
        },
        {
          title: '知识工程系统',
          url: '#'
        },
        {
          title: '国标委官网',
          url: '#'
        },
        {
          title: '工信部官网',
          url: '#'
        },
        {
          title: '全标委官网',
          url: '#'
        },
        {
          title: 'ECE官网',
          url: '#'
        }
      ], // 友情链接
      currentInline: 45, // 同时在线人数
      totalInline: 300, // 累计登录总人数
      contactNumber: '022-23652365', // 联系电话
      contactAddress: '天津市西青区西青道999号', // 联系地址
      logoSm: require('assets/images/home/logo_sm.png')
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
        this.currentTime = this.$dateFormat(new Date(), 'yyyy-MM-dd hh:mm:ss')
        // this.currentTime = this.$dateFormat(new Date(), 'yyyy-MM-dd hh:mm:ss') + ' 星期' + '日一二三四五六'.charAt(new Date().getDay())
      }, 1000)
    }
  },
  watch: {},
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
      display: flex;
      align-items: center;
      padding: 0 .54rem;
      .logo{
        width: 22.7%;
        height: 58.75%;
        background: url('~assets/images/home/logo.png') no-repeat;
        background-position: left center;
        background-size: 85%;
      }
      .home-nav{
        flex: 1;
        display: flex;
        padding: 0 0.5rem;
        li{
          float: left;
          color: #FFF;
          font-size: 16px;
          flex: 1;
          text-align: center;
          position: relative;
          transition: transform 0.3s linear;
          -webkit-user-select: none;
          -moz-user-select: none;
          -ms-user-select: none;
          user-select: none;
          &::before{
            content: '';
            width: 0;
            height: 2px;
            background: #FFF;
            position: absolute;
            bottom: -0.15rem;
            left: 50%;
            transform: translate(-50%);
            transition: all .3s linear;
          }
          &:hover{
            cursor: pointer;
            transform: translateY(-5px);
            &::before{
              width: 50%;
              bottom: -0.25rem;
            }
          }
        }
      }
      .header-right{
        width: 11rem;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: flex-end;
        color: #FFF;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        &>div{
          height: 62.5%;
          display: flex;
          align-items: center;
          font-size: 15px;
          &.time-box{
            min-width: 3.92rem;
            justify-content: center;
            margin-right: 0.2rem;
          }
          &.user-info{
            min-width: 3.5rem;
            display: flex;
            img{
              width: 0.8rem;
              height: 0.8rem;
            }
            .ivu-dropdown{
              flex: 1;
              height: 0.8rem;
              line-height: 0.8rem;
              cursor: pointer;
              text-align: center;
              margin-left: 0.2rem;
            }
          }
        }
      }
    }
    .search-box{
      width: 45.8%;
      height: 5.55%;
      margin: 3.5% auto 0 auto;
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
        left: 0.55rem;
      }
    }
    .nav{
      width: 20.72rem;
      height: 55%;
      margin: 3.5% auto 0 auto;
      .nav-content{
        height: 100%;
        color: #FFF;
        position: relative;
        .nav-content-left {
          width: 6.2rem;
          height: 100%;
          position: absolute;
          top: 0;
          left: 0;
          .nav-left-top{
            &.nav-item{
              width: 3rem;
              height: 23%;
              position: absolute;
              padding-top: 0.4rem;
              &:nth-child(1){
                top: 0;
                left: 0;
              }
              &:nth-child(2){
                top: 0;
                right: 0;
              }
              &:nth-child(3){
                left: 0;
                top: 24.9%;
              }
              &:nth-child(4){
                right: 0;
                top: 24.9%;
              }
              h5{
                font-size: 0.28rem;
                text-align: center;
              }
            }
          }
          .nav-left-bottom{
            width: 6.2rem;
            height: 49%;
            position: absolute;
            bottom: 0;
            left: 0;
            color: #666;
            padding: 0.25rem;
            h5{
              font-size: 0.32rem;
              -webkit-user-select: none;
              -moz-user-select: none;
              -ms-user-select: none;
              user-select: none;
            }
            ul{
              margin-top: 0.1rem;
              height: 85%;
              border: 1px solid #DDD;
              overflow-y: auto;
              li{
                padding: .1rem;
                .ellipsis;
              }
            }
          }
          .nav-icon{
            font-size: 0.8rem;
            text-align: center;
          }
        }
        .nav-content-right{
          width: 12.52rem;
          height: 100%;
          position: absolute;
          top: 0;
          right: 0;
          .nav-right-top{
            position: relative;
            width: 12.52rem;
            height: 47.9%;
            position: absolute;
            top: 0;
            left: 0;
            .more{
              cursor: pointer;
              color: @baseColor;
              position: absolute;
              top: 0;
              right: 0;
              z-index: 100;
              padding: 0.16rem 0.32rem;
              &:hover{
                color: #12508E;
              }
            }
            .ivu-tabs{
              height: 100%;
              .ivu-tabs-bar{
                margin-bottom: 0;
              }
              .ivu-tabs-content{
                height: calc(~'100% - 0.66rem');
                ul{
                  width: 100%;
                  height: 100%;
                  padding: 0.15rem;
                  overflow-y: auto;
                  li{
                    padding: 0 0.1rem;
                    margin-bottom: 0.1rem;
                    position: relative;
                    cursor: pointer;
                    display: flex;
                    display: -ms-flex;
                    align-items: center;
                    &:last-child{
                      margin-bottom: 0;
                    }
                    a{
                      display: inline-block;
                      max-width: 70%;
                      .ellipsis;
                    }
                    &:hover{
                      a{
                        color: @baseColor;
                      }
                      span{
                        color: @baseColor;
                      }
                    }
                    i{
                      font-style: italic;
                      color: #E4393C;
                      font-weight: bold;
                      margin-right: 0.1rem;
                    }
                    a{
                      color: #515a6e;
                    }
                    span{
                      position: absolute;
                      top: 0;
                      right: 0.15rem;
                    }
                  }
                }
              }
            }
          }
          .nav-right-bottom{
            width: 12.52rem;
            height: 49%;
            position: absolute;
            bottom: 0;
            left: 0;
            padding: 0 0.2rem;
            .more{
              position: absolute;
              top: 0;
              right: 0;
              padding: 0.16rem 0.32rem;
              z-index: 100;
              cursor: pointer;
              color: #40A6D5;
              &:hover{
                color: #FFF;
              }
            }
            h5{
              font-size: 0.32rem;
              height: 0.8rem;
              line-height: 0.8rem;
              -webkit-user-select: none;
              -moz-user-select: none;
              -ms-user-select: none;
              user-select: none;
            }
            ul{
              width: 100%;
              height: calc(~'100% - 1rem');
              overflow-y: auto;
              li{
                padding: 0 0.1rem;
                margin-bottom: 0.15rem;
                position: relative;
                cursor: pointer;
                display: flex;
                display: -ms-flex;
                align-items: center;
                &:last-child{
                  margin-bottom: 0;
                }
                .type{
                  position: relative;
                  display: inline-block;
                  padding-right: 0.15rem;
                  &::before{
                    content: '';
                    display: inline-block;
                    width: 2px;
                    height: 80%;
                    position: absolute;
                    top: 7.5%;
                    right: 0;
                    background: #FFF;
                  }
                }
                .time{
                  position: absolute;
                  top: 0;
                  right: 0.1rem;
                }
                a{
                  padding-left: 0.1rem;
                  color: #FFF;
                  display: inline-block;
                  max-width: 70%;
                  .ellipsis;
                }
                &:hover{
                  a{
                    color: #40A6D5;
                  }
                  .time{
                    color: #40A6D5;
                  }
                }
              }
            }
          }
        }
        #standardRegulationLibrary{
          background: @boxColor3;
          color: @boxColor2;
          &:hover{
            color: #FFF;
          }
        }
        #enterpriseStandardLaw{
          background: @boxColor2;
          color: #417EC6;
          &:hover{
            color: #12508E;
          }
        }
        #processCenter{
          background: @boxColor2;
          color: #417EC6;
          &:hover{
            color: #12508E;
          }
        }
        #standardTeam{
          background: @boxColor3;
          color: @boxColor2;
          &:hover{
            color: #FFF;
          }
        }
        #pendingTask{background: @boxColor2;}
        #dynamicEarlyWarning{background: @boxColor2;}
        #informationCenter{background: @boxColor3;}
      }
      .nav-item{
        cursor: pointer;
      }
    }
    .home-footer{
      margin-top: 2.5%;
      .friendshipLink{
        width: 19rem;
        margin: 0 auto;
        display: flex;
        display: -ms-flex;
        span{
          color: #FFF;
          display: inline-flex;
          display: -ms-inline-flexbox;
          align-items: center;
          flex: 0 0 2rem;
          text-align: center;
          font-size: 0.28rem;
        }
        ul{
          display: flex;
          display: -ms-flex;
          flex: 1;
          li{
            float: left;
            flex: 1;
            display: flex;
            display: -ms-flex;
            align-items: center;
            a{
              color: #FFF;
            }
          }
        }
      }
      .footer{
        width: 23.5rem;
        margin: 0.3rem auto 0 auto;
        color: #FFF;
        span{
          position: relative;
          display: inline-block;
          &.footer-border-right{
            padding: 0 0.1rem;
            &::before{
              content: '';
              display: block;
              width: 1px;
              height: 80%;
              position: absolute;
              top: 10%;
              right: 0;
              background: #FFF;
            }
          }
        }
        .ivu-col{
          text-align: center;
        }
        a{
          color: #FFF;
          cursor: default;
        }
        img{
          width: 2.44rem;
        }
        .contact-number,
        .contact-address{
          padding: 0 0.2rem;
        }
      }
    }
  }
</style>
