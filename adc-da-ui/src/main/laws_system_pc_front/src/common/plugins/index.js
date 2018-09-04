/**
 * @description: 自定义插件
 * @author: xx
 * @date: 2018-08-29 10:17:59
 */

import dateFormat from '../plugins/date'

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
}

export default {
  install
}
