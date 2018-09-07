import PanelHeader from './panel/PanelHeader'
import PanelContent from './panel/PanelContent'
import Loading from './loading'
import LabelInput from './labelInput'

const install = function (Vue) {
  Vue.component('PanelHeader', PanelHeader)
  Vue.component('PanelContent', PanelContent)
  Vue.component('loading', Loading)
  Vue.component('labelInput', LabelInput)
}

export default {
  install
}
