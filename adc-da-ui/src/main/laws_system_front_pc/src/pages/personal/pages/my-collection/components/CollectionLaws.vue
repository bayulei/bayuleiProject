<!-- 已收藏的法规 -->
<template>
 <div id="CollectionLaws">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="search.collectionLaws" placeholder="请输入动态信息" label="标准搜索"></label-input>
       <Button type="info" @click="LawsSelect">查询</Button>
     </div>
     <div slot="right"></div>
   </table-tools-bar>
 </div>
</template>

<script>
export default {
  name: 'CollectionLaws',
  data () {
    return {
      search: {
        collectionLaws: ''
      }
    }
  },
  methods: {
    // 搜索
    LawsSelect () {
    }
  },
  mounted: {
  }
}
</script>

<style lang="less">
   #CollectionLaws{}
</style>
