/**
 * @description: mutations
 * @author: xx
 * @date: 2018-08-29 14:36:43
 */
export default {
  // 主题
  setTheme (state, theme) {
    state.theme = theme
    try {
      localStorage.theme = theme
    } catch (e) {}
  }
}
