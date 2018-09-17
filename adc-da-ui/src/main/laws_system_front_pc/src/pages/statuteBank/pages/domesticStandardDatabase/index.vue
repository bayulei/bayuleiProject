<!-- 国内标准库 -->
<template>
 <div id="domesticStandardDatabase">
   <div class="tree">
     <Tree :data="tree" :render="renderContent"></Tree>
   </div>
     <!--<table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false">-->
       <!--<div slot="left">-->
         <!--<label-input v-model="sarStandardsSearch.country" placeholder="根据国家/地区查找" clearable label="国家/地区"  />-->
         <!--<label-input v-model="sarStandardsSearch.standNumber" placeholder="根据标准号查找" clearable label="标准号" class="my-input" />-->
         <!--<label-input v-model="sarStandardsSearch.standName" placeholder="根据标准名称查找" clearable label="标准名称" class="my-input" />-->
         <!--<label-input v-model="sarStandardsSearch.standState" placeholder="根据标准状态查找" clearable label="标准状态" class="my-input" />-->
         <!--<Button type="primary" icon="ios-search" :loading="searching" @click="searchData"></Button>-->
         <!--<Dropdown trigger="click" style="margin-left: 20px" @on-click="clickDropMenu">-->
           <!--<Button type="primary" icon="ios-arrow-down">设置</Button>-->
           <!--<DropdownMenu slot="list">-->
             <!--<DropdownItem name="newMenu">新建</DropdownItem>-->
             <!--<DropdownItem name="editMenu">编辑</DropdownItem>-->
             <!--<DropdownItem name="deleteMenu">删除</DropdownItem>-->
           <!--</DropdownMenu>-->
         <!--</Dropdown>-->
         <!--<Button type="primary" icon="ios-add" :loading="searching" @click="addModal">新增标准</Button>-->
         <!--<Button type="primary" icon="ios-add" :loading="searching" @click="addImportModal">导入标准</Button>-->
         <!--<Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>-->
         <!--<Button type="primary" @click="isAdvancedSearch = true">配置标准</Button>-->
       <!--</div>-->
     <!--</table-tools-bar>-->
   <div class="tree-right">
     <!-- 顶部工具栏 -->
     <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false">
       <div slot="left">
         <label-input v-model="sarStandardsSearch.country" placeholder="根据国家/地区查找" clearable label="国家/地区"  />
         <label-input v-model="sarStandardsSearch.standNumber" placeholder="根据标准号查找" clearable label="标准号" />
         <Button type="primary" icon="ios-search" :loading="searching" @click="getDomesticStandardTable"></Button>
         <Button type="primary" @click="clearAllSearch">清空查询</Button>
       </div>
       <div slot="right">
         <Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>
       </div>
     </table-tools-bar>
     <div class="content">
       <div class="action-bar">
         <Checkbox :value="checkAll" size="large" @on-change="handleSelectAll" :indeterminate="indeterminate">全选</Checkbox>
         <Button type="info" size="small">下载</Button>
         <Button type="primary" size="small" @click="addModal">新增</Button>
         <Button type="error" size="small" @click="clickDropMenu('deleteMenu')">删除</Button>
       </div>
       <div class="content-detail" v-if="stahndinfoList.length > 0">
         <div class="card " v-for="(item, index) in stahndinfoList" :key="index" :class="{ 'selected': item.checked }" @click="handleCardClick(item)">
             <Row>
               <Col span="5">
                 <Checkbox v-model="item.checked" size="large"></Checkbox>
                 标准号: {{ item.standNumber }}
               </Col>
               <Col span="4" push="1">
                 <b>《{{ item.standName }}》</b>
               </Col>
               <Col span="4" push="2">{{ item.standState }}</Col>
               <Col span="4" push="2">{{ item.standNature }}</Col>
               <Col span="3" push="4">
                 <Icon type="md-star" size="26" style="margin-right:5px"></Icon>
                 <Icon type="ios-redo" size="26"></Icon>
               </Col>
             </Row>
             <Row>
               <Col span="4">新车型实施时间: {{ item.putTime }}</Col>  
               <Col span="4" push="2">在产车实施时间: {{ item.issueTime }}</Col>
               <Col span="4" push="3">适用车型: -</Col>
               <Col span="6" push="6">
                 <Button @click = "goProcess(item)">流程</Button>
                 <Button @click = "selectStandardPro(item,'show')">查看</Button>
                 <Button @click = "selectStandardPro(item,'edit')">编辑</Button>
                 <Button @click = "selectSarStandItems(item.id)">查看表单</Button>
               </Col>
             </Row>
           </div>
       </div>
       <has-no-data pClass="content-detail" v-else></has-no-data>
       <loading :loading="loading">数据获取中</loading>
     </div>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
     <!-- 新增、编辑模态窗 -->
     <full-modal v-model="modalshowflag" v-if="modalshowflag" ref="modalshow" >
       <!--    新增样式     -->
       <div class="standards-info-form">
         <Form ref="sarStandardsInfoForm" :model="sarStandardsInfoEO" :rules="sarStandardsInfoRules" class="label-input-form">
           <Row>
             <Col span="8">
               <FormItem label="国家/地区" prop="country" class="standards-info-item">
                 <!--<Input v-model="sarStandardsInfoEO.country" disabled="disabled"></Input>-->
                 <Select v-model="sarStandardsInfoEO.country" disabled>
                   <Option v-for="opt in countryOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="标准类别" prop="standSort" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.standSort" :disabled="formdisableflag">
                   <Option v-for="opt in standSortOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="适用车型" prop="applyArctic" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.applyArctic" multiple :disabled="formdisableflag" >
                   <Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="标准编号" prop="standNumber" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.standNumber" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="标准年份" prop="standYear" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.standYear" :disabled="formdisableflag" ></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="标准名称" prop="standName" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.standName" :disabled="formdisableflag" ></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="标准英文名称" prop="standEnName" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.standEnName" :disabled="formdisableflag" ></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="标准状态" prop="standState" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.standState" :disabled="formdisableflag" >
                   <Option v-for="opt in standStateOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="标准性质" prop="standNature" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.standNature" :disabled="formdisableflag">
                   <Option v-for="opt in standNatureOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="代替标准号" prop="replaceStandNum" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.replaceStandNum" @on-blur="testReplaceStandNum" placeholder="输入多个标准号，以逗号隔开" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="被代替标准号" prop="replacedStandNum" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.replacedStandNum" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="采用国际标准号" prop="interStandNum" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.interStandNum" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="采标程度" prop="adoptExtent" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.adoptExtent" :disabled="formdisableflag">
                   <Option v-for="opt in adoptExtentOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="能源种类" prop="emergyKind" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.emergyKind" multiple :disabled="formdisableflag">
                   <Option v-for="opt in emergyKindOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="适用认证" prop="applyAuth" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.applyAuth" multiple :disabled="formdisableflag">
                   <Option v-for="opt in applyAuthOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="发布日期" prop="issueTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.issueTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="实施日期" prop="putTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.putTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="新定型车实施日期" prop="newcarPutTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.newcarPutTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="在产车实施日期" prop="productPutTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.productPutTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="新生产车实施日期" prop="newproductPutTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.newproductPutTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="起草单位" prop="draftingUnit" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.draftingUnit" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="起草人" prop="draftUser" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.draftUser" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="标准文本" prop="standFile" class="standards-info-item">
                 <!--<Input v-model="sarStandardsInfoEO.standFile"></Input>-->
                 <input type="file" name="standFiles" />
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="标准修改单" prop="standModifyFile" class="standards-info-item">
                 <!--<Input v-model="sarStandardsInfoEO.standModifyFile"></Input>-->
                 <input type="file" name="standModifyFiles" :disabled="formdisableflag"/>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="草案" prop="draftFile" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.draftFile" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="征求意见稿" prop="opinionFile" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.opinionFile" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="送审稿" prop="sentScreenFile" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.sentScreenFile" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="报批稿" prop="approvalFile" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.approvalFile" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="关联文件" prop="relevanceFile" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.relevanceFile" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="关键词" prop="tags" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.tags" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="内容摘要" prop="synopsis" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.synopsis" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="责任部门" prop="responsibleUnit" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.responsibleUnit" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
               <FormItem label="所属类别" prop="category" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.category" multiple :disabled="formdisableflag">
                   <Option v-for="item in categoryOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="8">
               <FormItem label="备注" prop="remark" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.remark" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="8">
             </Col>
             <Col span="8">
             </Col>
           </Row>
         </Form>
         <Button v-if="!formdisableflag" type="primary" @click="saveOrUpdateStands" >保存修改</Button>
       </div>
     </full-modal>
     <!-- 导入模态窗 -->
     <Modal v-model="importModalshowflag" title="导入文件" >
       <Upload action="/api/lawss/sarStandardsInfo/importStandardsInfo?standType='INLAND'" ref="importfile" name="file" :format="['xlsx']" :on-format-error="handleFormatError" :on-success="importFileSuccess">
         <Button icon="ios-cloud-upload-outline">选择文件</Button>
       </Upload>
     </Modal>
     <!-- 新增二级菜单模态窗 -->
     <Modal v-model="menuModalFlag" title="新增目录" @on-ok="newMenu" @on-cancel="closeModal" ref="menuRefModal">
       <Form  :model="sarMenu" :rules="sarMenuRules" class="label-input-form">
         <FormItem label="名称" prop="menuName" class="standards-info-item">
           <Input v-model="sarMenu.menuName"></Input>
         </FormItem>
         <FormItem label="排序号" prop="displaySeq" class="standards-info-item">
           <Input v-model="sarMenu.displaySeq"   placeholder="只允许输入数字" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></Input>
         </FormItem>
       </Form>
     </Modal>
     <!--  查看标准条目列表   -->
     <full-modal v-model="modalStandItemflag" v-if="modalStandItemflag" ref="" >
       <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false" class="label-input-form">
         <div slot="left">
         </div>
         <div slot="right">
           <Button type="primary" icon="ios-add" :loading="searching" @click="addItemModal">新增</Button>
           <Button type="primary" icon="ios-add" :loading="searching" @click="addImportModal">导入</Button>
           <Button type="primary" @click="isAdvancedSearch = true">删除</Button>
           <Button type="primary" @click="configurationStandard">保存</Button>
           <Button type="primary" @click="exportStandard">导出</Button>
         </div>
       </table-tools-bar>
       <Table border ref="" :columns="standItemTableColumn" :data="standItemList"></Table>
     </full-modal>
     <!--  新增标准条目  -->
     <full-modal v-model="modalItemaddShowflag" v-if="modalItemaddShowflag" ref="modalshow" >
       <div class="standardsItem-info-form">
         <Form ref="" :model="standItemEO"  class="label-input-form">
           <FormItem label="条目号" prop="" class="standards-info-item">
             <Input v-model="standItemEO.itemsNum" :disabled="formdisableflag"></Input>
           </FormItem>
           <FormItem label="条目名称" prop="" class="standards-info-item">
             <Input v-model="standItemEO.itemsName" :disabled="formdisableflag" ></Input>
           </FormItem>
           <FormItem label="涉及零部件" prop="" class="standards-info-item">
             <Input v-model="standItemEO.parts" :disabled="formdisableflag" ></Input>
           </FormItem>
           <FormItem label="特殊生效日期" prop="" class="standards-info-item">
             <DatePicker v-model="standItemEO.tackTime" :disabled="formdisableflag"></DatePicker>
           </FormItem>
           <FormItem label="适用车型" prop="applyArctic" class="standards-info-item">
             <Select v-model="standItemEO.applyArctic" multiple :disabled="formdisableflag" >
               <Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
             </Select>
           </FormItem>
           <FormItem label="能源种类" prop="energyKind" class="standards-info-item">
             <Select v-model="standItemEO.energyKind" multiple :disabled="formdisableflag">
               <Option v-for="opt in emergyKindOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
             </Select>
           </FormItem>
           <FormItem label="责任部门" prop="responsibleUnit" class="standards-info-item">
             <Input v-model="standItemEO.responsibleUnit" :disabled="formdisableflag"></Input>
           </FormItem>
           <FormItem label="备注" prop="" class="standards-info-item">
             <Input v-model="standItemEO.remarks" :disabled="formdisableflag"></Input>
           </FormItem>
         </Form>
         <Button v-if="!formdisableflag" type="primary" @click="saveOrUpdateStandItem" >保存修改</Button>
       </div>
     </full-modal>
   </div>
 </div>
</template>

<script>
import draggable from 'vuedraggable'
export default {
  name: 'DomesticStandardLibrary',
  data () {
    return {
      isAdvancedSearch: false, // 高级检索窗口是否打开
      searching: false,
      checkAll: false, // 是否全选
      indeterminate: false, // 是否半选
      selectedList: [],
      loading: false,
      total: 0,
      tableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '标准号',
          key: 'standNumber'
        },
        {
          title: '标准名称',
          key: 'standName'
        },
        {
          title: '标准性质',
          key: 'standNature'
        },
        {
          title: '标准状态',
          key: 'standState'
        },
        {
          title: '发布日期',
          key: 'issueTime'
        },
        {
          title: '实施日期',
          key: 'putTime'
        },
        {
          title: '操作',
          key: 'action',
          width: 200,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.show(params.index)
                  }
                }
              }, '查看'),
              h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.modalshowflag = true
                    this.sarStandardsInfoEO = params.row
                    this.modalshowtitle = '修改标准'
                    this.addOrUPdateFlag = 2
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.sarStandardsInfoEO.id = params.row.id
                    this.deleteStand()
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      stahndinfoList: [],
      standitemTotal: 0,
      standItemTableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '条目号',
          key: 'itemsNum'
        },
        {
          title: '条目内容',
          key: 'itemsName'
        },
        {
          title: '涉及零部件',
          key: 'parts'
        },
        {
          title: '特殊生效时间',
          key: 'tackTime'
        },
        {
          title: '适用车型',
          key: 'applyArcticShow'
        },
        {
          title: '能源类型',
          key: 'emergyKindShow'
        },
        {
          title: '责任部门',
          key: 'responsibleUnit'
        },
        {
          title: '备注',
          key: 'remarks'
        },
        {
          title: '操作',
          key: 'action',
          width: 200,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.show(params.index)
                  }
                }
              }, '查看'),
              h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.modalItemaddShowflag = true
                    this.standItemEO = params.row
                    this.itemAddOrUPdateFlag = 2
                    let a = []
                    if (this.standItemEO.applyArctic != null && !(this.standItemEO.applyArctic instanceof Array)) {
                      a = this.standItemEO.applyArctic.split(',')
                      this.standItemEO.applyArctic = a // 适用车型
                    }
                    if (this.standItemEO.energyKind != null && !(this.standItemEO.energyKind instanceof Array)) {
                      a = this.standItemEO.energyKind.split(',')
                      this.standItemEO.energyKind = a // 能源种类
                    }
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.standItemEO.id = params.row.id
                    this.deleteStandItem()
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      standItemList: [],
      modalshowflag: false,
      importModalshowflag: false,
      menuModalFlag: false,
      formdisableflag: false,
      modalStandItemflag: false, // 标准条目显示
      modalItemaddShowflag: false, // 新增标准条目显示
      modalshowtitle: '新增标准',
      addOrUPdateFlag: 1, // 新增：1， 修改：2
      itemAddOrUPdateFlag: 1, // 新增：1， 修改：2
      sarStandardsInfoEO: {
        id: '',
        standType: 'INLAND', // 标准分类
        country: 'CN',
        standSort: '',
        applyArctic: '',
        standNumber: '',
        standYear: '',
        standName: '',
        standEnName: '',
        standState: '',
        standNature: '',
        replaceStandNum: '',
        replacedStandNum: '',
        interStandNum: '',
        adoptExtent: '',
        emergyKind: '',
        applyAuth: '',
        issueTime: '',
        putTime: '',
        newcarPutTime: '',
        productPutTime: '',
        newproductPutTime: '',
        draftingUnit: '',
        draftUser: '',
        standFile: '',
        standModifyFile: '',
        draftFile: '',
        opinionFile: '',
        sentScreenFile: '',
        approvalFile: '',
        relevanceFile: '',
        tags: '',
        synopsis: '',
        responsibleUnit: '',
        category: '',
        remark: ''
      }, // 新增过程中用到的对象
      standItemEO: {
        id: '',
        standId: '',
        itemsNum: '',
        itemsName: '',
        parts: '',
        tackTime: '',
        applyArctic: '', // 试用车型
        energyKind: '', // 能源种类
        responsibleUnit: '',
        remarks: ''
      }, // 标准条目新增过程中用到对象
      sarStandardsSearch: {
        page: 1,
        pageSize: 10,
        standType: 'INLAND',
        menuId: '',
        country: '',
        standNumber: '',
        standName: '',
        standState: '',
        standNature: '',
        issueTime: '',
        applyArctic: '',
        replaceStandNum: '',
        replacedStandNum: ''
      }, // 分页查询过程中用到的对象
      standItemSearch: {
        standId: ''
      },
      sarStandardsInfoRules: {
        standSort: [
          { required: true, message: '标准类别不能为空', trigger: 'blur' }
        ],
        applyArctic: [
          { required: true, message: '适用车型不能为空', trigger: 'blur' }
        ],
        standNumber: [
          { required: true, message: '标准编号不能为空', trigger: 'blur' }
        ],
        standYear: [
          { required: true, message: '标准年份不能为空', trigger: 'blur' }
        ],
        standName: [
          { required: true, message: '标准名称不能为空', trigger: 'blur' }
        ],
        standEnName: [
        ],
        standState: [
          { required: true, message: '标准状态不能为空', trigger: 'blur' }
        ],
        standNature: [
          { required: true, message: '标准性质不能为空', trigger: 'blur' }
        ],
        replaceStandNum: [],
        replacedStandNum: [],
        interStandNum: [],
        adoptExtent: [],
        emergyKind: [
          { required: true, message: '能源种类不能为空', trigger: 'blur' }],
        applyAuth: [],
        issueTime: [
          { required: true, message: '发布日期不能为空', trigger: 'blur' }
        ],
        putTime: [
          { required: true, message: '实施日期不能为空', trigger: 'blur' }
        ],
        newcarPutTime: [],
        productPutTime: [],
        newproductPutTime: [],
        draftingUnit: [],
        draftUser: [],
        standFile: [],
        standModifyFile: [],
        draftFile: [],
        opinionFile: [],
        sentScreenFile: [],
        approvalFile: [],
        relevanceFile: [],
        tags: [],
        synopsis: [],
        responsibleUnit: [],
        category: [],
        remark: []
      },
      sarMenuRules: {
        menuName: [
          { required: true, message: '二级菜单不能为空', trigger: 'blur' }
        ],
        displaySeq: [
          { required: true, message: '排序序号不能为空', trigger: 'blur' }
        ]
      },
      countryOptions: [],
      standSortOptions: [], // 标准类别下拉框
      applyArcticOptions: [], // 适用车型下拉框
      standStateOptions: [], // 标准状态下拉框
      standNatureOptions: [], // 标准性质下拉框
      adoptExtentOptions: [], // 采标程度下拉框
      emergyKindOptions: [], // 能源种类下拉框
      applyAuthOptions: [], // 适用认证下拉框
      categoryOptions: [], // 所属类别下拉框
      issueTimeOptions: [{ label: '本月', value: '1' }, { label: '近三个月', value: '2' }, { label: '近一年', value: '3' }, { label: '近三年', value: '4' }, { label: '三年以上', value: '5' }], // 高级搜索条件中的发布日期
      // 二级菜单对象
      sarMenu: {
        id: '',
        parentId: '',
        menuName: '',
        sorDivide: 'INLAND_STAND',
        displaySeq: '',
        parentIds: ''
      },
      // 树形结构
      tree: [{
        title: 'parent 1',
        expand: true,
        render: (h, { root, node, data }) => {
          return h('span', {
            style: {
              display: 'inline-block',
              width: '100%'
            }
          }, [
            h('span', [
              h('Icon', {
                props: {
                  type: 'ios-folder-outline'
                },
                style: {
                  marginRight: '8px'
                }
              }),
              h('span', data.title)
            ]),
            h('span', {
              style: {
                display: 'inline-block',
                float: 'right',
                marginRight: '32px'
              }
            }, [
              h('Button', {
                props: Object.assign({}, this.buttonProps, {
                  icon: 'ios-add',
                  type: 'primary'
                }),
                style: {
                  width: '64px'
                },
                on: {
                  click: () => { this.append(data) }
                }
              })
            ])
          ])
        },
        children: [
          {
            title: 'child 1-1',
            expand: true,
            children: [
              {
                title: 'leaf 1-1-1',
                expand: true
              },
              {
                title: 'leaf 1-1-2',
                expand: true
              }
            ]
          },
          {
            title: 'child 1-2',
            expand: true,
            children: [
              {
                title: 'leaf 1-2-1',
                expand: true
              },
              {
                title: 'leaf 1-2-1',
                expand: true
              }
            ]
          }
        ]
      }
      ],
      buttonProps: {
        type: 'default',
        size: 'small'
      }
    }
  },
  methods: {
    // 分页查询国内标准
    getDomesticStandardTable () {
      this.$http.get('lawss/sarStandardsInfo/getSarStandardsInfoPage', this.sarStandardsSearch, {
        _this: this, loading: 'loading'
      }, res => {
        for (let i = 0; i < res.data.list.length; i++) {
          res.data.list[i]['collectIcontype'] = 'ios-star-outline'
          res.data.list[i]['collectIconcolor'] = '#5c6b77'
          res.data.list[i].checked = false
        }
        this.stahndinfoList = res.data.list
        this.total = res.data.count
      }, e => {
      })
    },
    // 分页点击后方法
    pageChange (page) {
      this.sarStandardsSearch.page = page
      this.getDomesticStandardTable()
    },
    // 分页每页显示数改变后方法
    pageSizeChange (pageSize) {
      this.sarStandardsSearch.pageSize = pageSize
      this.getDomesticStandardTable()
      // 此处需要调用接口，修改个人配置
    },
    // 点击新增按钮弹出新增模态框
    addModal () {
      this.modalshowflag = true
      this.formdisableflag = false
      this.modalshowtitle = '新增标准'
      this.addOrUPdateFlag = 1
      this.sarStandardsInfoEO = {}
      this.sarStandardsInfoEO.standType = 'INLAND' // 标准分类
      this.sarStandardsInfoEO.country = 'CN'
    },
    // 保存或修改标准
    saveOrUpdateStands () {
      // 时间格式修改
      this.sarStandardsInfoEO.issueTime = this.$dateFormat(this.sarStandardsInfoEO.issueTime, 'yyyy-MM-dd')
      this.sarStandardsInfoEO.putTime = this.$dateFormat(this.sarStandardsInfoEO.putTime, 'yyyy-MM-dd')
      this.sarStandardsInfoEO.newcarPutTime = this.$dateFormat(this.sarStandardsInfoEO.newcarPutTime, 'yyyy-MM-dd')
      this.sarStandardsInfoEO.productPutTime = this.$dateFormat(this.sarStandardsInfoEO.productPutTime, 'yyyy-MM-dd')
      this.sarStandardsInfoEO.newproductPutTime = this.$dateFormat(this.sarStandardsInfoEO.newproductPutTime, 'yyyy-MM-dd')
      // 新增
      if (this.addOrUPdateFlag === 1) {
        this.$http.post('lawss/sarStandardsInfo/addarStandardsInfo', this.sarStandardsInfoEO, {
          _this: this
        }, res => {
          this.getDomesticStandardTable()
        }, e => {
        })
      } else {
        // 修改
        this.$http.post('lawss/sarStandardsInfo/updateSarStandardsInfo', this.sarStandardsInfoEO, {
          _this: this
        }, res => {
          this.getDomesticStandardTable()
        }, e => {
        })
      }
    },
    // 删除标准
    deleteStand () {
      this.$http.post('lawss/sarStandardsInfo/deleteSarStandards', {id: this.sarStandardsInfoEO.id}, {
        _this: this
      }, res => {
        this.getDomesticStandardTable()
      }, e => {
      })
    },
    // 需求中操作栏中操作函数
    // 查看标准属性
    selectStandardPro (item, state) {
      this.sarStandardsInfoEO = item
      this.modalshowflag = true
      let a = []
      if (this.sarStandardsInfoEO.applyArctic != null && !(this.sarStandardsInfoEO.applyArctic instanceof Array)) {
        a = item.applyArctic.split(',')
        this.sarStandardsInfoEO.applyArctic = a // 适用车型
      }
      if (this.sarStandardsInfoEO.emergyKind != null && !(this.sarStandardsInfoEO.emergyKind instanceof Array)) {
        a = item.emergyKind.split(',')
        this.sarStandardsInfoEO.emergyKind = a // 能源种类
      }
      if (this.sarStandardsInfoEO.applyAuth != null && !(this.sarStandardsInfoEO.applyAuth instanceof Array)) {
        a = item.applyAuth.split(',')
        this.sarStandardsInfoEO.applyAuth = a // 适用认证
      }
      if (this.sarStandardsInfoEO.category != null && !(this.sarStandardsInfoEO.category instanceof Array)) {
        a = item.category.split(',')
        this.sarStandardsInfoEO.category = a // 所属类别
      }
      if (state === 'show') {
        this.formdisableflag = true
      } else {
        this.addOrUPdateFlag = 2
        this.formdisableflag = false
      }
    },
    // 收藏标准
    collectStandard (i) {
      console.log(i)
      i.collectIconcolor = '#CD950C'
      i.collectIcontype = 'ios-star'
      /* this.$http.post('', {id: this.sarStandardsInfoEO.id}, {
        _this: this
      }, res => {
      }, e => {
      }) */
    },
    // 分享标准
    shareStandard (item) {
      this.$http.post('', {id: item.id}, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 跳转流程节点
    goProcess (item) {
      this.$http.post('', {id: item.id}, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 查看标准条目
    selectSarStandItems (standid) {
      this.modalStandItemflag = true
      this.standItemSearch.standId = standid
      this.$http.get('lawss/sarStandItems/getSarStandItemsList', {standId: standid}, {
        _this: this, loading: 'loading'
      }, res => {
        this.standItemList = res.data
        // this.standitemTotal = res.data.count // 分页需要
      }, e => {
      })
    },
    // 关闭新增模态模态框
    closeModal () {
      this.$refs.modalshow.toggleClose()
      this.$refs.menuRefModal.toggleClose()
    },
    // 点击导入标准
    addImportModal () {
      this.importModalshowflag = true
      this.$refs.importfile.clearFiles()
    },
    // 导入标准文件格式错误执行
    handleFormatError (file) {
      this.$Notice.warning({
        title: 'The file format is incorrect',
        desc: 'File format of ' + file.name + ' is incorrect, please select jpg or png.'
      })
    },
    // 导入标准数据成功后执行
    importFileSuccess (response, file) {
      this.getDomesticStandardTable()
    },
    // 二级菜单新建，编辑，删除
    clickDropMenu (name) {
      if (name === 'newMenu') {
        this.menuModalFlag = true
      } else if (name === 'editMenu') {
      } else {
        // deleteMenu 删除二级菜单，先判断是否选中，选中项目，然后调用删除方法
      }
    },
    newMenu () {
      this.$http.post('lawss/sarMenu/addSarMenu', this.sarMenu, {
        _this: this, loading: 'loading'
      }, res => {
      }, e => {
      })
    },
    // 输入代替标准号后，和数据库做验证
    testReplaceStandNum () {
      this.sarStandardsInfoEO.replaceStandNum = this.sarStandardsInfoEO.replaceStandNum.replace(/，/ig, ',')
      this.$http.post('lawss/sarStandardsInfo/selectReplaceStandNum', {'replaceStandNum': this.sarStandardsInfoEO.replaceStandNum, 'standType': this.sarStandardsInfoEO.standType}, {
        _this: this
      }, res => {
        console.log(res)
      }, e => {
      })
    },
    // 现在配置标准要求查询处于游离状态的数据
    configurationStandard () {
      this.sarStandardsSearch.menuId = 'nomenu'
      this.$http.post('lawss/sarStandardsInfo/selectStandardsInfoByMenu', this.sarStandardsSearch, {
        _this: this
      }, res => {
        console.log(res)
      }, e => {
      })
    },
    // 清空搜索框
    clearAllSearch () {
      this.sarStandardsSearch.country = ''
      this.sarStandardsSearch.standNumber = ''
      this.sarStandardsSearch.standName = ''
      this.sarStandardsSearch.standState = ''
      this.sarStandardsSearch.standNature = ''
      this.sarStandardsSearch.issueTime = ''
      this.sarStandardsSearch.applyArctic = ''
      this.sarStandardsSearch.replaceStandNum = ''
      this.sarStandardsSearch.replacedStandNum = ''
    },
    // 导出选中的标准  此处因为复选框没有设置好，所以先设置导出所有数据
    exportStandard () {
      this.$http.get('lawss/sarStandardsInfo/exportStandardsInfoExcel', this.sarStandardsSearch, {
        _this: this
      }, res => {
        console.log(res)
      }, e => {
      })
    },
    addItemModal () {
      this.modalItemaddShowflag = true
      this.itemAddOrUPdateFlag = 1
    },
    // 标准条目新增
    saveOrUpdateStandItem () {
      this.standItemEO.tackTime = this.$dateFormat(this.standItemEO.tackTime, 'yyyy-MM-dd')
      // 新增
      if (this.itemAddOrUPdateFlag === 1) {
        this.standItemEO.standId = this.standItemSearch.standId
        this.$http.post('lawss/sarStandItems/addSarStandItemsList', this.standItemEO, {
          _this: this
        }, res => {
          this.selectSarStandItems(this.standItemSearch.standId)
        }, e => {
        })
      } else {
        // 修改
        this.$http.post('lawss/sarStandItems/updateStandItem', this.standItemEO, {
          _this: this
        }, res => {
          this.selectSarStandItems(this.standItemSearch.standId)
        }, e => {
        })
      }
      this.modalItemaddShowflag = false
    },
    // 标准条目删除
    deleteStandItem () {
      let ids = []
      ids.push(this.standItemEO.id)
      this.$http.post('lawss/sarStandItems/deleteStandItem', {id: ids}, {
        _this: this
      }, res => {
        alert(this.standItemSearch.standId)
        this.selectSarStandItems(this.standItemSearch.standId)
      }, e => {
      })
    },
    /**
     * @description: 全选
     * @author: chenxiaoxi
     * @date: 2018-09-13 20:40:31
     */
    handleSelectAll (checked) {
      // 全部选中
      if (checked) {
        // 1.遍历数据,把每一项的checkbox置为选中状态
        this.selectedList = []
        for (let i = 0; i < this.stahndinfoList.length; i++) {
          this.$set(this.stahndinfoList[i], 'checked', true)
          // 2.把每一项的id都放入selectedList数组中
          this.selectedList.push(this.stahndinfoList[i].id)
        }
        // 全部取消选中
      } else {
        // 1.遍历数据,把每一项的checkbox置为取消选中状态
        for (let i = 0; i < this.stahndinfoList.length; i++) {
          this.$set(this.stahndinfoList[i], 'checked', false)
          // 2.清空selectedList数组
          this.selectedList = []
        }
      }
    },
    /**
     * @description: card点击
     * @author: chenxiaoxi
     * @date: 2018-09-15 10:47:57
     */
    handleCardClick (item) {
      item.checked = !item.checked
    },
    renderContent (h, { root, node, data }) {
      return h('span', {
        style: {
          display: 'inline-block',
          width: '100%'
        }
      }, [
        h('span', [
          h('Icon', {
            props: {
              type: 'ios-paper-outline'
            },
            style: {
              marginRight: '8px'
            }
          }),
          h('span', data.title)
        ]),
        h('span', {
          style: {
            display: 'inline-block',
            float: 'right',
            marginRight: '32px'
          }
        }, [
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-add'
            }),
            style: {
              marginRight: '8px'
            },
            on: {
              click: () => { this.append(data) }
            }
          }),
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-remove'
            }),
            on: {
              click: () => { this.remove(root, node, data) }
            }
          })
        ])
      ])
    },
    append (data) {
      const children = data.children || []
      children.push({
        title: 'appended node',
        expand: true
      })
      this.$set(data, 'children', children)
    },
    remove (root, node, data) {
      const parentKey = root.find(el => el === node).parent
      const parent = root.find(el => el.nodeKey === parentKey).node
      const index = parent.children.indexOf(data)
      parent.children.splice(index, 1)
    }
  },
  components: {
    draggable
  },
  props: {},
  watch: {
    stahndinfoList: {
      deep: true,
      handler (newVal, oldVal) {
        this.selectedList = []
        for (let i = 0; i < newVal.length; i++) {
          if (newVal[i].checked) {
            this.selectedList.push(newVal[i].id)
          }
        }
        if (this.selectedList.length === newVal.length && newVal.length !== 0) {
          this.checkAll = true
          this.indeterminate = false
        } else if (this.selectedList.length === 0) {
          this.checkAll = false
        } else {
          this.checkAll = false
          this.indeterminate = false
        }
      }
    },
    // 已选择的列表
    selectedList (newVal, oldVal) {
      if (oldVal.length === this.stahndinfoList.length && newVal.length !== 0) {
        this.checkAll = false
        this.indeterminate = true
      } else if (newVal.length === 0) {
        this.checkAll = false
        this.indeterminate = false
      }
    }
  },
  mounted () {
    this.getDomesticStandardTable()
    // 查询各下拉框数据
    this.$http.get('sys/dictype/getDicTypeListCode', '', {
      _this: this
    }, res => {
      this.countryOptions = res.data.COUNTRY
      this.standSortOptions = res.data.STANDCLASSIFY
      this.applyArcticOptions = res.data.PRODUCTTYPE // 根据需求文档，产品类别对应标准属性中的“适用车型”
      this.standStateOptions = res.data.STANDSTATE
      this.standNatureOptions = res.data.SARPROPERTY // 标准性质
      this.adoptExtentOptions = res.data.DEGREESTANDARD
      this.emergyKindOptions = res.data.ENERGYTYPES
      this.applyAuthOptions = res.data.PROVETYPE // 适用认证下拉框
      this.categoryOptions = res.data.CATEGORY
    }, e => {
    })
  }
}
</script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  @import '../../../../assets/zTree/css/metroStyle/metroStyle.css';
   #domesticStandardDatabase{
      padding: 0;
      display: flex;
      display: -ms-flex;
      .standards-info-form{
        min-height: 400px;
        overflow : auto;
        .save{
          width: 4.24rem;
          height: 0.72rem;
          line-height: 0.72rem;
          margin: 0.5rem 0 0 0.2rem;
        }
      }
      .content-detail{
        .ivu-row{
          height: 50%;
          display: flex;
          display: -ms-flex;
          align-items: center;
          .ivu-col{
            &:last-child{
              display: flex;
              display: -ms-flex;
              justify-content: flex-end;
              align-items: center;
              .ivu-btn{
                padding: 0 13px 1px;
              }
            }
            .card-edit{
              color: @baseColor;
              display: inline-block;
              height: 100%;
              margin-right: 15px;
            }
          }
        }
      }
    }
</style>
