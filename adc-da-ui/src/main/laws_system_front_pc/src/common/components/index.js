/**
 * @description: 自定义全局组件
 * @author: chenxiaoxi
 * @date: 2018-09-10 14:41:16
 */

import PanelHeader from './panel/PanelHeader'
import PanelContent from './panel/PanelContent'
import Loading from './loading'
import LabelInput from './labelInput'
import LabelSelect from './labelSelect'
import Pagination from './pagination'
import TableToolsBar from './tableToolsBar'
import FullModal from './fullModal'
import HasNoData from './hasNoData'

const install = function (Vue) {
  Vue.component('PanelHeader', PanelHeader)
  Vue.component('PanelContent', PanelContent)
  Vue.component('loading', Loading)
  Vue.component('labelInput', LabelInput)
  Vue.component('labelSelect', LabelSelect)
  Vue.component('pagination', Pagination)
  Vue.component('tableToolsBar', TableToolsBar)
  Vue.component('fullModal', FullModal)
  Vue.component('hasNoData', HasNoData)
}

export default {
  install
}
