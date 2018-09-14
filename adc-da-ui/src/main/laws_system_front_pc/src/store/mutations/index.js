/**
 * @description: mutations
 * @author: xx
 * @date: 2018-08-29 14:36:43
 */
export default {
  // state退出重置
  logout (state) {
    for (let i in state) {
      state[i] = ''
      sessionStorage.clear()
    }
  },
  // 主题
  setTheme (state, theme) {
    state.theme = theme
    try {
      localStorage.theme = theme
    } catch (e) {}
  },
  // 设置token
  setToken (state, token) {
    state.token = token
    try {
      sessionStorage.token = token
    } catch (e) {}
  }
}
