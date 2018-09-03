/**
 * @description: 全局状态
 * @author: xx
 * @date: 2018-08-29 14:31:49
 */
let defaultTheme = 'theme-one'
try {
  if (localStorage.theme) {
    defaultTheme = localStorage.theme
  }
} catch (e) {}
export default {
  theme: defaultTheme // 主题
}
