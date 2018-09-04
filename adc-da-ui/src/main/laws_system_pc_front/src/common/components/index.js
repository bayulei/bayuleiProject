import PanelHeader from './panel/PanelHeader'
import PanelContent from './panel/PanelContent'

const install = function (Vue) {
  Vue.component('PanelHeader', PanelHeader)
  Vue.component('PanelContent', PanelContent)
}

export default {
  install
}
