/**
 * @description: 自定义插件
 * @author: xx
 * @date: 2018-08-29 10:17:59
 */

import dateFormat from './date'
import loading from './loading'
import confirm from './confirm'

const install = function (Vue) {
  if (install.installed) return
  install.installed = true
  Object.defineProperties(Vue.prototype, {
    $dateFormat: {
      get () {
        return dateFormat
      }
    }
  })
  Object.defineProperties(Vue.prototype, {
    $loading: {
      get () {
        return loading
      }
    }
  })
  Object.defineProperties(Vue.prototype, {
    $confirm: {
      get () {
        return confirm
      }
    }
  })
}

export default {
  install
}
