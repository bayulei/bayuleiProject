/**
 * @description: 全局状态
 * @author: xx
 * @date: 2018-08-29 14:31:49
 */
let defaultTheme = 'theme-one'
let defaultToken = ''
try {
  if (localStorage.theme) { defaultTheme = localStorage.theme }
  if (sessionStorage.token) { defaultToken = sessionStorage.token }
} catch (e) {}
export default {
  theme: defaultTheme, // 主题
  token: defaultToken // 登录标识
}
