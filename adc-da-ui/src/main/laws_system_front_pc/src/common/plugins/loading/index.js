/**
 * @description: Loading
 * @author: chenxiaoxi
 * @date: 2018-09-04 09:09:38
 */
export default {
  show: (_this, tips) => {
    var loadingTips = (tips === undefined || tips === '') ? 'loading' : tips
    _this.$Spin.show({
      render: (h) => {
        return h('Spin', [
          h('Icon', {
            'class': 'spin-loading',
            props: {
              size: 20,
              custom: 'i-icon icon-icon-loading',
              fix: true
            }
          }),
          h('div', {
            'class': 'loading-tips'
          }, loadingTips)
        ])
      }
    })
  },
  remove (_this) {
    _this.$Spin.hide()
  }
}
