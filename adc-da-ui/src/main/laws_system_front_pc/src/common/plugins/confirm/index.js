/**
 * @description: confirm弹窗
 * @author: chenxiaoxi
 * @date: 2018-09-13 10:52:14
 */
import Vue from 'vue'
let _this = Vue.prototype
export default (con) => {
  let config = con === undefined ? {} : con
  let _title = config.title || '删除确认'
  let _tips = config.tips || '您确认要删除吗'
  let _loading = (config.loading && typeof (config.loading) === 'boolean') || false
  let _confirm = config.confirm || function () {
    _this.$Modal.remove()
    _this.$Message.success('删除成功')
  }
  let _cancel = config.cancel || function () {
    _this.$Modal.remove()
  }
  _this.$Modal.confirm({
    render: (h) => {
      return h('div', [
        h('p', {
          style: {
            'color': '#F60',
            'text-align': 'center'
          },
          slot: 'header'
        }, [
          h('Icon', {
            props: {
              type: 'ios-information-circle'
            }
          }),
          h('span', _title),
          h('Divider', {
            class: {
              'my-divider': true
            }
          })
        ]),
        h('div', {
          style: {
            'text-align': 'center'
          }
        }, _tips),
        h('Divider', {
          class: {
            'my-divider': true
          }
        }),
        h('div', {
          style: {
            'text-align': 'center'
          }
        }, [
          h('Button', {
            props: {
              size: 'large',
              loading: _loading
            },
            on: {
              click: () => {
                _cancel()
              }
            }
          }, '取消'),
          h('Button', {
            props: {
              type: 'error',
              size: 'large',
              loading: this.modal_loading
            },
            on: {
              click: () => {
                _confirm()
              }
            },
            style: {
              'margin-left': '15px'
            }
          }, '确定')
        ])
      ])
    }
  })
}
