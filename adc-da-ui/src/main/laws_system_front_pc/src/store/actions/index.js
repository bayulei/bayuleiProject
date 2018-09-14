/**
 * @description: actions
 * @author: chenxiaoxi
 * @date: 2018-09-13 11:30:47
 */
export default {
  // 设置token
  setToken (content, token) {
    content.commit('setToken', token)
  },
  // 退出登录
  logout ({ commit }) {
    return new Promise((resolve, reject) => {
      commit('logout')
      resolve()
    })
  }
}
