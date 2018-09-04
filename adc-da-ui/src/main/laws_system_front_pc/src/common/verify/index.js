/**
 * @description: 通用正则验证方法
 * @author: chenxiaoxi
 * @date: 2018-09-04 11:29:05
 */
export default {
  // 验证Email
  checkEmail (nullTips, errTips) {
    return (rule, value, callback) => {
      if (!value) {
        return callback(new Error(nullTips || '不能为空'))
      }
      let pattern = new RegExp('^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$')
      if (pattern.test(value) === false) {
        return callback(new Error(errTips || '邮箱格式不正确'))
      } else {
        callback()
      }
    }
  },
  // 验证账号
  checkUsername (nullTips, errTips) {
    return (rule, value, callback) => {
      if (!value) {
        return callback(new Error(nullTips || '不能为空'))
      }
      let pattern = /\w{3,15}$/
      setTimeout(() => {
        if (pattern.test(value) === false) {
          return callback(new Error(errTips || '只能由3-15位的数字、字母组成'))
        } else {
          callback()
        }
      }, 1000)
    }
  }
}
