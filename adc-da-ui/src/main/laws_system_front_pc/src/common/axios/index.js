/**
 * @description: axios二次封装
 * @author: chenxiaoxi
 * @date: 2018-09-04 11:29:34
 */
const axios = require('axios')
axios.defaults.headers['Authorization'] = sessionStorage.token || ''

/**
 * @description: FormData
 * @author: xx
 * @date: 2018-08-14 16:57:48
 */
let formData = (data) => {
  let _formData = new FormData()
  for (let i in data) {
    _formData.append(i, data[i])
  }
  return _formData
}
axios.defaults.timeout = 5000
axios.defaults.headers['Content-type'] = 'application/json'
module.exports = {
  /**
   * @description: post方法
   * @config: {
   *    _this : this (vue原型)
   *    loading: data中定义的 loading('字符串格式 例如data定义的myloading config:{ _this: this, loading: 'myloading' }')
   * }
   * @author: xx
   * @date: 2018-08-14 16:59:56
   */
  post (url, param, config, thenFun, exeFun) {
    var _formData = formData(param)
    // 参数包含this
    let _this = config._this || false
    // 参数包含loading
    let loading = config.loading || false
    if (_this) {
      _this[loading] = true
    }
    axios.post('/api/' + url, _formData).then(res => {
      if (_this && loading) { _this[loading] = false }
      if (res.data.ok !== undefined) {
        let type = res.data.ok ? 'success' : 'warning'
        if (_this) { _this.$Message[type](res.data.message) }
        if (res.data.ok) {
          thenFun.call(this, res.data)
        }
      }
    }).catch(err => {
      if (_this && loading) {
        _this[loading] = false
        _this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
      }
      exeFun.call(this, err)
    })
  },

  /**
   * @description: post方法(json格式提交)
   * @config: {
   *    _this : this (vue原型)
   *    loading: data中定义的 loading('字符串格式 例如data定义的myloading config:{ _this: this, loading: 'myloading' }')
   * }
   * @author: xx
   * @date: 2018-08-14 16:59:56
   */
  postData (url, param, config, thenFun, exeFun) {
    // 参数包含this
    let _this = config._this || false
    // 参数包含loading
    let loading = config.loading || false
    if (_this) {
      _this[loading] = true
    }
    axios.post('/api/' + url, param).then(res => {
      if (_this && loading) { _this[loading] = false }
      if (res.data.ok !== undefined) {
        let type = res.data.ok ? 'success' : 'warning'
        if (_this) { _this.$Message[type](res.data.message) }
        if (res.data.ok) {
          thenFun.call(this, res.data)
        }
      }
    }).catch(err => {
      if (_this && loading) {
        _this[loading] = false
        _this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
      }
      exeFun.call(this, err)
    })
  },

  /**
   * @description: $get方法(使用post方法获取数据)
   * @config: {
   *    _this : this (vue原型)
   *    loading: data中定义的 loading('字符串格式 例如data定义的myloading config:{ _this: this, loading: 'myloading' }')
   * }
   * @author: xx
   * @date: 2018-08-14 16:59:56
   */
  $get (url, param, config, thenFun, exeFun) {
    var _formData = formData(param)
    // 参数包含this
    let _this = config._this || false
    // 参数包含loading
    let loading = config.loading || false
    if (_this) {
      _this[loading] = true
    }
    axios.post('/api/' + url, _formData).then(res => {
      if (_this && loading) { _this[loading] = false }
      thenFun.call(this, res.data)
    }).catch(err => {
      if (_this && loading) {
        _this[loading] = false
        _this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
      }
      exeFun.call(this, err)
    })
  },

  /**
   * @description: get方法
   * @config: {
   *    _this : this (vue原型)
   *    loading: data中定义的 loading('字符串格式 例如data定义的myloading config:{ _this: this, loading: 'myloading' }')
   * }
   * @author: xx
   * @date: 2018-08-14 16:59:56
   */
  get (url, param, config, thenFun, exeFun) {
    // 参数包含this
    let _this = config._this || false
    // 参数包含loading
    let loading = config.loading || false
    if (_this) {
      _this[loading] = true
    }
    axios.get('/api/' + url, { params: param }).then(res => {
      if (_this && loading) { _this[loading] = false }
      // 返回data对象
      if (res.ok !== undefined) {
        if (res.ok) {
          thenFun.call(this, res.data)
        }
      } else {
        // 返回data数组
        if (res.data.data.length > 0) {
          thenFun.call(this, res.data)
        }
      }
      thenFun.call(this, res.data)
    }).catch(err => {
      if (_this && loading) {
        _this[loading] = false
        _this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
      }
      exeFun.call(this, err)
    })
  },

  /**
   * @description: put方法
   * @author: chenxiaoxi
   * @date: 2018-09-06 13:28:37
   */
  put (url, param, config, thenFun, exeFun) {
    var _formData = formData(param)
    // 参数包含this
    let _this = config._this || false
    // 参数包含loading
    let loading = config.loading || false
    if (_this) {
      _this[loading] = true
    }
    axios.put('/api/' + url, _formData).then(res => {
      if (_this && loading) { _this[loading] = false }
      if (res.data.ok !== undefined) {
        let type = res.data.ok ? 'success' : 'warning'
        if (_this) { _this.$Message[type](res.data.message) }
        if (res.data.ok) {
          thenFun.call(this, res.data)
        }
      }
    }).catch(err => {
      if (_this && loading) {
        _this[loading] = false
        _this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
      }
      exeFun.call(this, err)
    })
  },

  /**
   * @description: delete方法
   * @author: chenxiaoxi
   * @date: 2018-09-14 09:55:30
   */
  delete (url, param, config, thenFun, exeFun) {
    var _formData = formData(param)
    // 参数包含this
    let _this = config._this || false
    // 参数包含loading
    let loading = config.loading || false
    if (_this) {
      _this[loading] = true
    }
    axios.delete('/api/' + url, _formData).then(res => {
      if (_this && loading) { _this[loading] = false }
      if (res.data.ok !== undefined) {
        let type = res.data.ok ? 'success' : 'warning'
        if (_this) { _this.$Message[type](res.data.message) }
        if (res.data.ok) {
          thenFun.call(this, res.data)
        }
      }
    }).catch(err => {
      if (_this && loading) {
        _this[loading] = false
        _this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
      }
      exeFun.call(this, err)
    })
  }
}
