<!-- 国内标准库 -->
<template>
 <div id="domesticStandardDatabase">
   <div class="tree">
     <ul id="treeDemo" class="ztree"></ul>
     <Dropdown trigger="click" style="margin-left: 20px" @on-click="clickDropMenu">
       <Button type="primary" icon="ios-arrow-down">设置</Button>
       <DropdownMenu slot="list">
         <DropdownItem name="newMenu">新建</DropdownItem>
         <DropdownItem name="editMenu">编辑</DropdownItem>
         <DropdownItem name="deleteMenu">删除</DropdownItem>
       </DropdownMenu>
     </Dropdown>
   </div>
   <div class="tree-right">
     <!-- 顶部工具栏 -->
     <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false">
       <div slot="left">
         <label-select v-model="sarStandardsSearch.country" :options="countryOptions" label="国家/地区" placeholder="根据国家/地区查找"></label-select>
         <label-input v-model="sarStandardsSearch.standNumber" placeholder="根据标准号查找" clearable label="标准编号" class="my-input" />
         <!--<br><br>-->
         <!--<label-input v-model="sarStandardsSearch.standName" placeholder="根据标准名称查找" clearable label="标准名称" class="my-input" />-->
         <!--<label-select v-model="sarStandardsSearch.standState" :options="standStateOptions" label="标准状态" placeholder="根据标准状态查找"></label-select>-->
         <!--<label-select v-model="sarStandardsSearch.standNature" :options="standNatureOptions"  placeholder="根据标准性质查找" clearable label="标准性质"  />-->
         <!--<label-select v-model="sarStandardsSearch.issueTime" :options="issueTimeOptions" placeholder="根据发布日期查找" clearable label="发布日期" class="my-input" />-->
         <!--<label-select v-model="sarStandardsSearch.applyArctic" :options="applyArcticOptions" placeholder="根据适用车型查找" clearable label="适用车型" class="my-input" />-->
         <!--<label-input v-model="sarStandardsSearch.replaceStandNum" placeholder="根据代替标准查找" clearable label="代替标准" class="my-input" />-->
         <!--<label-input v-model="sarStandardsSearch.replacedStandNum" placeholder="根据代替标准查找" clearable label="被代替标准" class="my-input" />-->
         <Button type="primary" icon="ios-search" :loading="searching" @click="getDomesticStandardTable"></Button>
         <Button type="primary"  @click="clearAllSearch">重置</Button>
       </div>
       <div slot="right">
         <Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>
       </div>
     </table-tools-bar>
     <div class="content">
       <div class="action-bar">
         <Checkbox :value="checkAll" size="large" @on-change="handleSelectAll" :indeterminate="indeterminate">全选</Checkbox>
         <Button type="info" size="small" @click="exportStandard">下载</Button>
         <Button type="primary" size="small" @click="addModal">新增</Button>
         <Button type="error" size="small" @click="clickDropMenu('deleteMenu')">删除</Button>
         <Button type="primary" icon="ios-add" :loading="searching" @click="addImportModal(1)">导入标准</Button>
         <Button type="primary" @click="configurationStandard">配置标准</Button>
       </div>
       <div class="content-detail" v-if="stahndinfoList.length > 0">
         <div class="card domBtn" v-for="(item, index) in stahndinfoList" :key="index" :class="{ 'selected': item.checked }" @mousedown="handleMousedown($event)" @mouseup="handleMouseup(item, $event)">
             <Row>
               <Col span="5">
                 <Checkbox v-model="item.checked" size="large"></Checkbox>
                 标准号: {{ item.standSortShow }} {{ item.standNumber }} - {{ item.standYear }}
               </Col>
               <Col span="4" push="1">
                 <b>《{{ item.standName }}》</b>
               </Col>
               <Col span="4" push="2">标准性质: {{ item.standNatureShow }}</Col>
               <Col span="3" push="6">
                 <Icon type="md-star" size="26" style="margin-right:5px"></Icon>
                 <Icon type="ios-redo" size="26"></Icon>
               </Col>
             </Row>
             <Row>
               <Col span="4">新车型发布日期: {{ item.putTime }}</Col>
               <Col span="4" push="2">在产车实施日期: {{ item.issueTime }}</Col>
               <Col span="4" push="3">
                 <Tag type="dot" :color="item.standStateShow === 0 ? '' : (item.standStateShow === 1 ? 'warning' : (item.standStateShow === 2 ? 'success' : 'error'))">
                   {{ item.standStateShow === 0 ? '待发布' : (item.standStateShow === 1 ? '审核中' : (item.standStateShow === 2 ? '已发布' : '已驳回')) }}
                 </Tag>
               </Col>
               <Col span="6" push="6">
                 <Button @click="goProcess(item)" class="card-btn">流程</Button>
                 <Button @click="selectStandardPro(item,'show')" class="card-btn">查看</Button>
                 <Button @click="selectStandardPro(item,'edit')" class="card-btn">编辑</Button>
                 <Button @click="selectSarStandItems(item.id)" class="card-btn">查看表单</Button>
               </Col>
             </Row>
           </div>
       </div>
       <has-no-data pClass="content-detail" v-else></has-no-data>
       <loading :loading="loading">数据获取中</loading>
     </div>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
     <Drawer
       title="国内法规标准库"
       v-model="modalshowflag"
       width="850"
       :styles="styles"
       @on-close="resetForm"
     >
       <!-- 新增样式 -->
       <div class="standards-info-form">
         <Form ref="sarStandardsInfoForm" :model="sarStandardsInfoEO" :rules="sarStandardsInfoRules" class="label-input-form">
           <Row>
             <Col span="12">
               <FormItem label="国家/地区" prop="country" class="standards-info-item">
                 <!--<Input v-model="sarStandardsInfoEO.country" disabled="disabled"></Input>-->
                 <Select v-model="sarStandardsInfoEO.country" disabled>
                   <Option v-for="opt in countryOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="标准类别" prop="standSort" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.standSort" :disabled="formdisableflag">
                   <Option v-for="opt in standSortOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="适用车型" prop="applyArctic" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.applyArctic" multiple :disabled="formdisableflag" >
                   <Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="标准编号" prop="standNumber" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.standNumber" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="标准年份" prop="standYear" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.standYear" :disabled="formdisableflag" ></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="标准名称" prop="standName" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.standName" :disabled="formdisableflag" ></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="标准英文名称" prop="standEnName" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.standEnName" :disabled="formdisableflag" ></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="标准状态" prop="standState" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.standState" :disabled="formdisableflag" >
                   <Option v-for="opt in standStateOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="标准性质" prop="standNature" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.standNature" :disabled="formdisableflag">
                   <Option v-for="opt in standNatureOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="代替标准号" prop="replaceStandNum" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.replaceStandNum" @on-blur="testReplaceStandNum" placeholder="输入多个标准号，以逗号隔开" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="被代替标准号" prop="replacedStandNum" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.replacedStandNum" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="采用国际标准号" prop="interStandNum" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.interStandNum" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="采标程度" prop="adoptExtent" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.adoptExtent" :disabled="formdisableflag">
                   <Option v-for="opt in adoptExtentOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="能源种类" prop="emergyKind" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.emergyKind" multiple :disabled="formdisableflag">
                   <Option v-for="opt in emergyKindOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="适用认证" prop="applyAuth" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.applyAuth" multiple :disabled="formdisableflag">
                   <Option v-for="opt in applyAuthOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="发布日期" prop="issueTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.issueTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="实施日期" prop="putTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.putTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="新定型车实施日期" prop="newcarPutTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.newcarPutTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="在产车实施日期" prop="productPutTime" class="standards-info-item" title="在产车实施日期">
                 <DatePicker v-model="sarStandardsInfoEO.productPutTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="新生产车实施日期" prop="newproductPutTime" class="standards-info-item">
                 <DatePicker v-model="sarStandardsInfoEO.newproductPutTime" :disabled="formdisableflag"></DatePicker>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="起草单位" prop="draftingUnit" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.draftingUnit" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="起草人" prop="draftUser" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.draftUser" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="标准文本" prop="standFileList" class="standards-info-item">
                 <!--<input v-model="sarStandardsInfoEO.standFileList" v-show="false">
                 <Upload :show-upload-list="false" :action="uploadPath"
                         :on-success="(response, file, fileList) => handleUploadSucc(response, file, fileList, 'standFileList','standFileName')" multiple name="file">
                   <Button icon="ios-cloud-upload-outline" :disabled="formdisableflag">{{ sarStandardsInfoEO.standFileList.length === 0 ? '点击上传' : standFileName }}</Button>
                 </Upload>-->
                 <Button @click="importModalshowflagtemp=true" :disabled="formdisableflag">点击上传</Button>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="标准修改单" prop="standModifyFile" class="standards-info-item">
                 <Upload :show-upload-list="false"
                         :action="uploadPath"
                         :on-success="(response, file, fileList) => handleUploadSucc(response, file, fileList, 'standModifyFileList', 'standModifyFileName')" multiple name="file">
                   <Button icon="ios-cloud-upload-outline" :disabled="formdisableflag">{{ sarStandardsInfoEO.standModifyFile === '' ? '点击上传' : standModifyFileName }}</Button>
                 </Upload>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="草案" prop="draftFile" class="standards-info-item">
                 <Upload :show-upload-list="false"
                         :action="uploadPath"
                         :on-success="(response, file, fileList) => handleUploadSucc(response, file, fileList, 'draftFileList', 'draftFileName')" multiple name="file" >
                   <Button icon="ios-cloud-upload-outline" :disabled="formdisableflag">{{ sarStandardsInfoEO.draftFile === '' ? '点击上传' : draftFileName }}</Button>
                 </Upload>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="征求意见稿" prop="opinionFile" class="standards-info-item">
                 <Upload :show-upload-list="false"
                         :action="uploadPath"
                         :on-success="(response, file, fileList) => handleUploadSucc(response, file, fileList, 'opinionFileList', 'opinionFileName')" multiple name="file" >
                   <Button icon="ios-cloud-upload-outline" :disabled="formdisableflag">{{ sarStandardsInfoEO.opinionFile === '' ? '点击上传' : opinionFileName }}</Button>
                 </Upload>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="送审稿" prop="sentScreenFile" class="standards-info-item">
                 <Upload :show-upload-list="false"
                         :action="uploadPath"
                         :on-success="(response, file, fileList) => handleUploadSucc(response, file, fileList, 'sentScreenFileList', 'sentScreenFileName')" multiple name="file">
                   <Button icon="ios-cloud-upload-outline" :disabled="formdisableflag">{{ sarStandardsInfoEO.sentScreenFile === '' ? '点击上传' : sentScreenFileName }}</Button>
                 </Upload>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="报批稿" prop="approvalFile" class="standards-info-item">
                 <Upload :show-upload-list="false"
                         :action="uploadPath"
                         :on-success="(response, file, fileList) => handleUploadSucc(response, file, fileList, 'approvalFileList', 'approvalFileName')" multiple name="file" >
                   <Button icon="ios-cloud-upload-outline" :disabled="formdisableflag">{{ sarStandardsInfoEO.approvalFile === '' ? '点击上传' : approvalFileName }}</Button>
                 </Upload>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="关联文件" prop="relevanceFile" class="standards-info-item">
                 <Upload :show-upload-list="false"
                         :action="uploadPath"
                         :on-success="(response, file, fileList) => handleUploadSucc(response, file, fileList, 'relevanceFileList', 'relevanceFileName')" multiple name="file" >
                   <Button icon="ios-cloud-upload-outline" :disabled="formdisableflag">{{ sarStandardsInfoEO.relevanceFile === '' ? '点击上传' : relevanceFileName }}</Button>
                 </Upload>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="关键词" prop="tags" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.tags" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="内容摘要" prop="synopsis" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.synopsis" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="责任部门" prop="responsibleUnit" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.responsibleUnit" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="所属类别" prop="category" class="standards-info-item">
                 <Select v-model="sarStandardsInfoEO.category" multiple :disabled="formdisableflag">
                   <Option v-for="item in categoryOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                 </Select>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="备注" prop="remark" class="standards-info-item">
                 <Input v-model="sarStandardsInfoEO.remark" :disabled="formdisableflag"></Input>
               </FormItem>
             </Col>
           </Row>
         </Form>
       </div>
       <div class="demo-drawer-footer">
         <Button style="margin-right: 8px" @click="modalshowflag = false">取消</Button>
         <Button type="primary" v-if="!formdisableflag" @click="saveOrUpdateStands">保存修改</Button>
       </div>
     </Drawer>
     <!-- 导入模态窗 -->
     <Modal v-model="importModalshowflag" title="导入文件" >
       <Upload :action="importExcelUrl" ref="importfile" name="file" :format="['xlsx']" :on-format-error="handleFormatError" :on-success="importFileSuccess">
         <Button icon="ios-cloud-upload-outline">选择文件</Button>
       </Upload>
     </Modal>
     <Modal v-model="importModalshowflagtemp" title="导入文件1" >
       <Upload :action="uploadPath"
               :before-upload="handleUpload"
               :on-success="(response, file, fileList) => handleUploadSucc(response, file, fileList, 'standFileList','standFileName')" multiple name="file">
         <Button icon="ios-cloud-upload-outline" :disabled="formdisableflag">{{ sarStandardsInfoEO.standFileList.length === 0 ? '点击上传' : standFileName }}</Button>
       </Upload>
       <div v-if="file !== null">Upload file: {{ file.name }} <Button type="text" @click="upload" :loading="loadingStatus">{{ loadingStatus ? 'Uploading' : 'Click to upload' }}</Button></div>
     </Modal>
     <!-- 新增二级菜单模态窗 -->
     <Modal v-model="menuModalFlag" title="新增目录" @on-ok="newMenu" >
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
           <Button type="primary" icon="ios-add" :loading="searching" @click="addImportModal(2)">导入</Button>
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

<script src="./js/domesticStandardDatabase.js"></script>

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
  .domBtn {border:1px gray solid;background-color:#FFE6B0}
  .domBtn_Disabled {border:1px gray solid;background-color:#DFDFDF;color:#999999}
  .dom_tmp {
    width: 50px;
    height: 50px;
    position:absolute;
    color: #FFE6B0;
    font-size: 50px;
    display: flex;
    align-items: flex-start;
  }
  .active {background-color: #93C3CF}
</style>
