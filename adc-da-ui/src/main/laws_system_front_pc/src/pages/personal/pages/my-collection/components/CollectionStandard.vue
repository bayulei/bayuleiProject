<!-- 已收藏的标准 -->
<template>
 <div id="CollectionStandard">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="search.standardName" placeholder="请输入动态信息" label="动态搜索"></label-input>
       <Button type="info" @click="standardSelect">查询</Button>
     </div>
     <div slot="right"></div>
   </table-tools-bar>
 </div>
</template>

<script>
export default {
  name: 'collectionStandard',
  data () {
    return {
      search: {
        standardName: ''
      }
    }
  },
  methods: {
    // 检索
    standardSelect () {}
  },
  components: {},
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
   #CollectionStandard{}
</style>
