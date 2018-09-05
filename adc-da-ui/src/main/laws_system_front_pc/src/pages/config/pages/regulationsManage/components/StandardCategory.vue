<!-- 标准类别 -->
<template>
  <div class="standard-category">
    <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false">
      <div slot="left">
        <Input v-model="keywords1" placeholder="根据用户名查找" clearable class="my-input" />
        <Input v-model="keywords2" placeholder="根据描述查找" clearable class="my-input" />
        <Button type="primary" icon="ios-search" :loading="searching" @click="searchData"></Button>
      </div>
      <div slot="right">
        <Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="tableColumn" :data="data"></Table>
    </div>
    <pagination :total="total"></pagination>
  </div>
</template>
<script>
import TableToolsBar from 'pages/components/TableToolsBar'
import Pagination from 'pages/components/Pagination'
export default {
  name: 'standard-category',
  data () {
    return {
      keywords1: '',
      keywords2: '',
      searching: false,
      isAdvancedSearch: false, // 是否为高级搜索
      total: 1000,
      loading: false,
      tableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: 'Name',
          key: 'name'
        },
        {
          title: 'Age',
          key: 'age'
        },
        {
          title: 'Address',
          key: 'address'
        }
      ],
      data: [],
      userData: [
        {
          name: 'John Brown',
          age: 18,
          address: 'New York No. 1 Lake Park',
          date: '2016-10-03'
        },
        {
          name: 'Jim Green',
          age: 24,
          address: 'London No. 1 Lake Park',
          date: '2016-10-01'
        },
        {
          name: 'Joe Black',
          age: 30,
          address: 'Sydney No. 1 Lake Park',
          date: '2016-10-02'
        },
        {
          name: 'Jon Snow',
          age: 26,
          address: 'Ottawa No. 2 Lake Park',
          date: '2016-10-04'
        }
      ]
    }
  },
  methods: {
    searchData () {
      this.searching = true
      this.loading = true
      setTimeout(() => {
        this.loading = false
        this.searching = false
      }, 1000)
    }
  },
  components: {
    TableToolsBar,
    Pagination
  },
  mounted () {
    this.$loading.show(this, '正在获取数据...')
    setTimeout(() => {
      this.data = this.userData
      this.$loading.remove(this)
    }, 1000)
  }
}
</script>

<style lang="less">
  @import '~styles/mixins';
  .standard-category{
  }
</style>
