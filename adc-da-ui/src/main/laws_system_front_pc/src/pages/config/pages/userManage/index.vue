<!-- 用户管理 -->
<template>
  <div class="user-manage">
    <table-tools-bar>
      <Row slot="left">
        <Col span="6" style="padding-right:10px">
          <Select v-model="model11" filterable>
            <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
        <Col span="6" style="padding-right:10px">
          <Select v-model="model11" filterable>
            <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
        <Col span="6" style="padding-right:10px">
          <Select v-model="model11" filterable>
            <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
        <Col span="6" style="padding-right:10px">
          <Select v-model="model11" filterable>
            <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
      </Row>
      <div class="btnGroup" slot="right">
        <Button type="info" style="margin-right: 0.7rem">查询</Button>
        <Button type="primary">清空</Button>
      </div>
    </table-tools-bar>
    <div class="content">
        <div class="btnGroup">
          <Button type="info">Info</Button>
          <Button type="success">Success</Button>
          <Button type="warning">Warning</Button>
          <Button type="error">Error</Button>
        </div>
    </div>
  </div>
</template>

<script>
import tableToolsBar from 'pages/components/TableToolsBar'
export default {
  name: 'user-manage',
  data () {
    return {
      cityList: [
        {
          value: 'New York',
          label: 'New York'
        },
        {
          value: 'London',
          label: 'London'
        },
        {
          value: 'Sydney',
          label: 'Sydney'
        },
        {
          value: 'Ottawa',
          label: 'Ottawa'
        },
        {
          value: 'Paris',
          label: 'Paris'
        },
        {
          value: 'Canberra',
          label: 'Canberra'
        }
      ],
      model11: '',
      model12: []
    }
  },
  components: {
    tableToolsBar
  }
}
</script>

<style lang="less" scoped>
  .user-manage{
    display: flex;
    background: #FFF;
    padding: 15px;
    .header{
      width: 100%;
      height:3.5rem ;
      border-bottom: 1px solid #DDD;
      padding: 0.5rem;
    }
    .btnGroup{
      float: right;
      margin: 0.5rem;
    }
    .btnGroup Button{
      width: 2.5rem;
    }
    .content{
      width: 100%;
      overflow: hidden;
    }
  }
</style>
