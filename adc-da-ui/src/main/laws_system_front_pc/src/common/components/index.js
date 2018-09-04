import PanelHeader from './panel/PanelHeader'
import PanelContent from './panel/PanelContent'
import Loading from './loading'

const install = function (Vue) {
  Vue.component('PanelHeader', PanelHeader)
  Vue.component('PanelContent', PanelContent)
  Vue.component('loading', Loading)
}

export default {
  install
}
