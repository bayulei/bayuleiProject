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
  },
  validateStandardItem (rule, value, callback) {
    if (!value) {
      return callback(new Error('条目号不能为空'))
    }
    let testval = /^[a-zA-Z0-9.\u4e00-\u9fa5]{1,100}$/
    if (!value.match(testval)) {
      return callback(new Error('1到100个任意字母、数字、汉字、小数点组合'))
    } else {
      callback()
    }
  },
  validateStandardNum (rule, value, callback) {
    let testval = /^\d+(\.\d+)*$/
    let pattern1 = /\w{1,100}$/
    let pattern2 = /\w{0,100}$/ // 验证代替标准号，和被代替标准号，因为非必填项，所以长度可能是0
    if (rule.field === 'standNumber') {
      if (!value) {
        return callback(new Error('标准编号不能为空'))
      } else if (!value.match(testval)) {
        return callback(new Error('1到100字符数字，支持“数字.数字”'))
      } else if (!value.match(pattern1) && rule.field === 'standNumber') {
        return callback(new Error('1到100字符数字，支持“数字.数字”'))
      } else {
        callback()
      }
    } else {
      if (value) {
        if (!value.match(testval)) {
          return callback(new Error('100以内字符，支持“数字.数字”'))
        } else if (!value.match(pattern2) && rule.field !== 'standNumber') {
          return callback(new Error('100以内字符，支持“数字.数字”'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
  }
}
