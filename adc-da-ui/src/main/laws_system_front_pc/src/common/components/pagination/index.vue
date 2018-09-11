<!-- 分页 -->
<template>
  <div class="pagination">
    <Divider />
    <div class="pagination-slot">
      <slot>
        <Page :total="total"
              show-elevator
              show-sizer
              :page-size-opts="[10, 20, 30, 50]"
              @on-change="pageChange"
              @on-page-size-change="pageSizeChange"/>
      </slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'pagination',
  methods: {
    // 切换页码
    pageChange (page) {
      this.$emit('pageChange', page)
    },
    pageSizeChange (pageSize) {
      this.$emit('pageSizeChange', pageSize)
    }
  },
  props: {
    total: {
      type: Number
    }
  }
}
</script>

<style lang="less">
  .pagination {
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
    .ivu-divider-horizontal {
      margin: 0;
    }
    .pagination-slot {
      width: 100%;
      height: 56px;
      line-height: 56px;
      padding: 0 15px;
      box-sizing: border-box;
    }
  }
</style>
