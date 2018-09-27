<!-- 首页 -->
<template>
  <div id="home">
    <com-header></com-header>
    <section class="home-section">
      <!-- 搜索框 -->
      <div class="search-box">
        <div class="search-option">
          <Select v-model="selectKey">
            <Option value="standNumber">标准编号</Option>
            <Option value="standName">标准名称</Option>
            <Option value="standState">标准状态</Option>
            <Option value="standFile">标准正文内容</Option>
            <Option value="applyArctic">适用车型</Option>
            <Option value="issueTime">发布日期</Option>
            <Option value="putTime">实施日期</Option>
            <Option value="draftUser">起草人</Option>
            <Option value="draftingUnit">起草部门</Option>
            <Option value="replaceStandNum">代替标准</Option>
            <Option value="replacedStandNum">被代替标准</Option>
            <Option value="lawsName">文件名称</Option>
            <Option value="2">动态标题</Option>
          </Select>
        </div>
        <div class="search-input">
          <Input type="text" v-model="selectValue" autofocus id="search-input" :placeholder="searchRecommend" clearable></Input>
        </div>
        <div class="search-btn">
          <Icon type="ios-search" :size="34" color="rgba(0,0,0,.8)" @click.native="searchCenteric"/>
        </div>
        <div class="search-text" >{{ searchText }}</div>
      </div>
      <nav class="section-nav">
        <div class="nav-content">
          <div class="nav-content-left">
            <!-- 国内外标准法规库 -->
            <router-link tag="div" class="nav-left-top nav-item" id="standardRegulationLibrary" to="/statuteBank">
              <dl>
                <dt class="nav-icon iconfont">&#xe656;</dt>
                <dd>国内外标准法规库</dd>
              </dl>
            </router-link>
            <!-- 企业标准库 -->
            <div class="nav-left-top nav-item" id="enterpriseStandardLaw">
              <dl>
                <dt class="nav-icon iconfont">&#xe6ee;</dt>
                <dd>企业标准库</dd>
              </dl>
            </div>
            <!-- 流程中心 -->
            <router-link tag="div" class="nav-left-top nav-item" id="processCenter" to="/processCenter">
              <dl>
                <dt class="nav-icon iconfont">&#xe634;</dt>
                <dd>流程中心</dd>
              </dl>
            </router-link>
            <!-- 标准团队 -->
            <div class="nav-left-top nav-item" id="standardTeam">
              <dl>
                <dt class="nav-icon iconfont">&#xe600;</dt>
                <dd>标准团队</dd>
              </dl>
            </div>
            <!-- 待办任务 -->
            <div class="nav-left-bottom" id="pendingTask">
              <h5>待办任务</h5>
              <div class="box-line"></div>
              <ul>
                <li v-for="needTodo in todoList" :key="needTodo.id">{{ needTodo.content }}</li>
              </ul>
            </div>
          </div>
          <div class="nav-content-right">
            <!-- 动态信息 -->
            <div class="nav-right-top" id="dynamicEarlyWarning">
              <h5>动态信息</h5>
              <span class="more">更多</span>
              <div class="box-line"></div>
              <ul>
                <li v-for="item in domesticDynamics" :key="item.id">
                  <i v-if="item.id === '1001'">new</i>
                  <a>{{ item.content }}</a>
                  <span>{{ item.time }}</span>
                </li>
              </ul>
            </div>
            <!-- 资料中心 -->
            <div class="nav-right-bottom" id="informationCenter">
              <h5>资料中心信息</h5>
              <span class="more">更多</span>
              <div class="box-line"></div>
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
      </nav>
    </section>
    <footer class="home-footer">
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
    </footer>
  </div>
</template>

<script>
import ComHeader from 'pages/components/Header'
export default {
  name: 'index',
  data () {
    return {
      selectKey: 'standNumber', // 搜索类型
      selectValue: '',
      searchText: '标准高级检索',
      searchRecommend: '请输入关键词',
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
      ],
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
      ],
      currentInline: 45, // 同时在线人数
      totalInline: 300, // 累计登录总人数
      contactNumber: '022-23652365', // 联系电话
      contactAddress: '天津市西青区西青道999号', // 联系地址
      logoSm: require('assets/images/home/logo_sm.png')
    }
  },
  methods: {
    searchCenteric () {
      this.$router.push({
        name: 'SearchCenter',
        params: {
          selectKey: this.selectKey,
          selectValue: this.selectValue
        }
      })
    }
  },
  components: {
    ComHeader
  },
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  #home{
    .home-section{
      height: 77.2%;
      background: @homeSectionBgColor;
      padding-top: 1.6%;
      .search-box{
        width: 62.95%;
        height: 7.9%;
        margin: 0 auto;
        display: flex;
        margin-bottom: 1.65%;
        &>div{
          height: 100%;
        }
        .search-option{
          flex: 0 0 13.63%;
          color: #515151;
          font-size: 16px;
          background: rgba(255,255,255,.8);
          border: 1px solid #B3C3C7;
          .flex-center;
          .ivu-select{
            height: 100%;
            background: transparent;
            border: none;
            .ivu-select-selection{
              height: 100%;
              border: none;
              border-radius: 0;
              box-shadow: none;
              & > div{
                height: 100%;
                .flex();
                align-items: center;
                justify-content: center;
              }
            }
          }
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
              padding: 0 20px;
              font-size: 16px;
            }
          }
        }
        .search-btn{
          flex: 0 0 13.63%;
          background: @homeHeaderBgColor;
          cursor: pointer;
          &:hover{
            opacity: .8;
          }
          .flex-center;
          .ivu-icon{
            color: #FFF !important;
          }
        }
        .search-text{
          .flex-center;
          color: #2A639B;
          font-weight: bold;
          font-size: 16px;
          flex: 0 0 calc(~'100% - 82.26%');
          position: relative;
          top: 5px;
        }
      }
      .section-nav{
          width: 90%;
          height: 87%;
          margin: 0 auto;
          max-width: 1600px;
          .nav-content{
            width: 100%;
            height: 100%;
            color: #FFF;
            position: relative;
            .nav-content-left {
              width: 36.14%;
              height: 100%;
              position: absolute;
              top: 0;
              left: 0;
              .nav-left-top{
                &.nav-item{
                  width: 48.9%;
                  height: 23%;
                  position: absolute;
                  .flex();
                  justify-content: center;
                  align-items: center;
                  @media screen and (max-width: 1366px) {
                    .nav-icon{font-size: 40px !important;}
                  }
                  .nav-icon{
                    font-size: 50px;
                    text-align: center;
                  }
                  dd{font-size:14px;}
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
                    font-size: 16px;
                    text-align: center;
                  }
                }
              }
              .nav-left-bottom{
                width: 100%;
                height: 49%;
                position: absolute;
                bottom: 0;
                left: 0;
                color: #666;
                h5{
                  font-size: 18px;
                  line-height: 30px;
                  -webkit-user-select: none;
                  -moz-user-select: none;
                  -ms-user-select: none;
                  user-select: none;
                  font-weight: 600;
                }
                ul{
                  margin-top: 5px;
                  height: 85%;
                  overflow-y: auto;
                  li{
                    padding: 5px;
                    .ellipsis;
                    font-size: 14px;
                  }
                }
              }
            }
            .nav-content-right{
              width: 60.46%;
              height: 100%;
              position: absolute;
              top: 0;
              right: 0;
              .nav-right-top{
                position: relative;
                width: 100%;
                height: 47.9%;
                position: absolute;
                top: 0;
                left: 0;
                h5{
                  font-size: 18px;
                  line-height: 30px;
                  -webkit-user-select: none;
                  -moz-user-select: none;
                  -ms-user-select: none;
                  user-select: none;
                  font-weight: 600;
                }
                ul{
                  height: 85%;
                  overflow-y: auto;
                  li{
                    padding: 0 5px;
                    margin-bottom: 5px;
                    position: relative;
                    cursor: pointer;
                    display: flex;
                    display: -ms-flex;
                    align-items: center;
                    font-size: 14px;
                    &:last-child{
                      margin-bottom: 0;
                    }
                    a{
                      display: inline-block;
                      max-width: 80%;
                      color: #333;
                      .ellipsis;
                    }
                    &:hover{
                      a{
                        color: #12508E;
                      }
                      span{
                        color: #12508E;
                      }
                    }
                    i{
                      font-style: italic;
                      color: #E4393C;
                      font-weight: bold;
                      margin-right: 5px;
                    }
                    span{
                      position: absolute;
                      top: 0;
                      right: 7.5px;
                    }
                  }
                }
              }
              .nav-right-bottom{
                width: 100%;
                height: 49%;
                position: absolute;
                bottom: 0;
                left: 0;
                h5{
                  font-size: 18px;
                  line-height: 30px;
                  -webkit-user-select: none;
                  -moz-user-select: none;
                  -ms-user-select: none;
                  user-select: none;
                  font-weight: 600;
                }
                ul{
                  width: 100%;
                  height: 85%;
                  overflow-y: auto;
                  li{
                    padding: 0 0.1rem;
                    margin-bottom: 0.15rem;
                    position: relative;
                    cursor: pointer;
                    display: flex;
                    display: -ms-flex;
                    align-items: center;
                    font-size: 14px;
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
                        background: #AAA;
                      }
                    }
                    .time{
                      position: absolute;
                      top: 0;
                      right: 0.1rem;
                    }
                    a{
                      padding-left: 0.1rem;
                      color: #333;
                      display: inline-block;
                      max-width: 80%;
                      .ellipsis;
                    }
                    &:hover{
                      a{
                        color: #12508E;
                      }
                      .time{
                        color: #12508E;
                      }
                    }
                  }
                }
              }
              .more{
                cursor: pointer;
                color: #12508E;
                position: absolute;
                top: 0;
                right: 0;
                z-index: 100;
                padding: 5px 10px;
                &:hover{
                  color: #FFF;
                  background: #12508E;
                  transition: all .2s linear;
                }
              }
            }
            #standardRegulationLibrary{
              background: @homeHeaderBgColor;
              color: #FFF;
            }
            #enterpriseStandardLaw{
              background: @boxBgColor;
              color: #FFF;
            }
            #processCenter{
              background: @boxBgColor;
              color: #FFF;
            }
            #standardTeam{
              background: @homeHeaderBgColor;
              color: #FFF;
            }
            #pendingTask{color: #333;}
            #dynamicEarlyWarning{color: #333;}
            #informationCenter{color: #333;}
          }
          .nav-item{
            cursor: pointer;
          }
        }
    }
    .home-footer{
      background: @homeSectionBgColor;
      height: calc(~'100% - 7.8% - 77.2%');
      .flex();
      flex-wrap: wrap;
      .friendshipLink{
        width: 68%;
        margin: 0 auto;
        display: flex;
        display: -ms-flex;
        position: relative;
        top: 10px;
        span{
          color: #333;
          display: inline-flex;
          display: -ms-inline-flexbox;
          align-items: center;
          flex: 0 0 2rem;
          text-align: center;
          font-size: 0.28rem;
          font-weight: bold;
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
              color: #333;
              font-size: 14px;
              &:hover{
                color: @baseColor;
                text-decoration: underline;
              }
            }
          }
        }
      }
      .footer{
        width: 90%;
        margin: 0.3rem auto 0 auto;
        color: #333;
        font-size: 14px;
        font-weight: bold;
        span{
          position: relative;
          display: inline-block;
          &.footer-border-right{
            padding: 0 0.1rem;
            &::before{
              content: '';
              display: block;
              width: 1px;
              height: 75%;
              position: absolute;
              top: 12.5%;
              right: 0;
              background: #333;
            }
          }
        }
        .ivu-col{
          text-align: center;
        }
        a{
          color: @baseColor;
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
