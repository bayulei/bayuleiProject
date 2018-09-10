import PanelHeader from './panel/PanelHeader'
import PanelContent from './panel/PanelContent'
import Loading from './loading'
import LabelInput from './labelInput'
import LabelSelect from './labelSelect'

const install = function (Vue) {
  Vue.component('PanelHeader', PanelHeader)
  Vue.component('PanelContent', PanelContent)
  Vue.component('loading', Loading)
  Vue.component('labelInput', LabelInput)
  Vue.component('labelSelect', LabelSelect)
}

export default {
  install
}
