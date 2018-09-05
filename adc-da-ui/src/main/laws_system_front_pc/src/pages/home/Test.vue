<template>
  <div class="test">
    <Tree :data="data4" show-checkbox @on-select-change="checkNode"></Tree>
    <Input :v-model="nodeTitle" clearable class="my-input" />
    <Button type="primary" @click="save">保存</Button>
  </div>
</template>
<script>
export default {
  data () {
    return {
      data4: [
        {
          title: 'parent 1',
          expand: true,
          selected: true,
          children: [
            {
              title: 'parent 1-1',
              expand: true,
              children: [
                {
                  title: 'leaf 1-1-1',
                  disabled: true
                },
                {
                  title: 'leaf 1-1-2'
                }
              ]
            },
            {
              title: 'parent 1-2',
              expand: true,
              children: [
                {
                  title: 'leaf 1-2-1',
                  checked: true
                },
                {
                  title: 'leaf 1-2-1'
                }
              ]
            }
          ]
        }
      ],
      nodeTitle: '',
      nodeKey: ''
    }
  },
  methods: {
    checkNode (treeNode) {
      this.nodeKey = treeNode[0].nodeKey || ''
    },
    save () {
      console.log(this.nodeKey)
    },
    fetchData () {
      this.$http.get('lawss/sarLawsInfo/page', {
        //
      }, {
        _this: this
      }, res => {
        console.log('success')
      }, err => {
        console.log('error')
      })
      // this.axios.get('http://192.168.1.191:8888/api/lawss/sarLawsInfo/page', {}).then().catch()
    }
  },
  mounted () {
    this.fetchData()
  }
}
</script>
<style>
  .my-input{
    width: 150px;
    margin-left: 20px;
  }
</style>
