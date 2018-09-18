<!-- 国内法规库 -->
<template>
 <div id="domesticRegulationsDatabase">
   <div class="tree">
     <ul id="treeDemo" class="ztree"></ul>
   </div>
   <div class="tree-right">
    <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false">
      <div slot="left">
        <label-input v-model="lawsInfo.lawsNumber" placeholder="根据文件号查找" clearable label="文件号"  />
        <label-input v-model="lawsInfo.lawsName" placeholder="根据文件名称查找" clearable label="文件名称"  />
        <Button type="primary" icon="ios-search" @click="searchLawsInfo"></Button>
      </div>
      <div slot="right">
        <Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <div class="action-bar">
        <Checkbox :value="checkAll" size="large" @on-change="handleSelectAll" :indeterminate="indeterminate">全选</Checkbox>
        <Button type="info" size="small" @click="exportLawsInfo">导出</Button>
        <Button type="primary" @click="openLawsModal">新增</Button>
        <Button type="primary" @click="modal2 = true">导入</Button>
      </div>

      <div class="content-detail" v-if="infoListData.length > 0">
        <div class="card domBtn" v-for="(item, index) in infoListData" :key="index" :class="{ 'selected': item.checked }" @click="handleCardClick(item, $event)">
          <Row>
            <Col span="5">
            <Checkbox v-model="item.checked" size="large"></Checkbox>
            文件号: {{ item.lawsNumber }}
            </Col>
            <Col span="4" push="1">
            <b>文件性质：{{ item.propertyName }}</b>
            </Col>
            <Col span="4" push="2">文件名称：{{ item.lawsName }}</Col>
            <Col span="4" push="2">发布单位：{{ item.issueUnit }}</Col>
            <Col span="3" push="4">
            <Icon type="md-star" size="26" style="margin-right:5px"></Icon>
            <Icon type="ios-redo" size="26"></Icon>
            </Col>
          </Row>
          <Row>
            <Col span="4">实施日期: {{ item.putTime }}</Col>
            <Col span="4" push="2">发布日期: {{ item.issueTime }}</Col>
            <Col span="4" push="3">适用车型: {{ item.applyArcticShow }}</Col>
            <Col span="6" push="6">
            <Button @click = "editLawsInfo(item,'show')">查看</Button>
            <Button @click = "editLawsInfo(item,'edit')">编辑</Button>
            <Button @click = "removeLawsInfo(item.id)">删除</Button>
            <Button @click = "searchLawsItems(item.id)">查看表单</Button>
            </Col>
          </Row>
        </div>
      </div>
      <has-no-data pClass="content-detail" v-else></has-no-data>
      <loading :loading="loading">数据获取中</loading>
    </div>
    <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>

   <!--新增修改查看法规模态框-->
   <!--<full-modal v-model="showLawsInfoModal" v-if="showLawsInfoModal" ref="showLawsInfoModal">-->
     <!--<div>-->
       <!--<Form ref="SarLawsInfoEO" :model="SarLawsInfoEO" :rules="lawsInfoFormRules" class="label-input-form">-->
         <!--<input v-model="SarLawsInfoEO.editLawsId" v-show="false">-->
         <!--<Row>-->
           <!--<Col span="8">-->
             <!--<FormItem label="国家/地区" prop="country" class="laws-info-item">-->
               <!--<Input v-model="SarLawsInfoEO.country" disabled="disabled"></Input>-->
             <!--</FormItem>-->
          <!--</Col>-->
           <!--<Col span="8">-->
             <!--<FormItem label="文件性质" prop="lawsProperty" class="laws-info-item">-->
               <!--<Select v-model="SarLawsInfoEO.lawsProperty">-->
                 <!--<Option v-for="opt in lawsPropertyOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>-->
               <!--</Select>-->
             <!--</FormItem>-->
           <!--</Col>-->
           <!--<Col span="8">-->
             <!--<FormItem label="文件号" prop="lawsNumber" class="laws-info-item">-->
               <!--<Input v-model="SarLawsInfoEO.lawsNumber"></Input>-->
             <!--</FormItem>-->
           <!--</Col>-->
         <!--</Row>-->
         <!--<Row>-->
           <!--<Col span="8">-->
             <!--<FormItem label="文件名称" prop="lawsName" class="laws-info-item">-->
               <!--<Input v-model="SarLawsInfoEO.lawsName"></Input>-->
             <!--</FormItem>-->
           <!--</Col>-->
           <!--<Col span="8">-->
             <!--<FormItem label="发布单位" prop="issueUnit" class="laws-info-item">-->
               <!--<Input v-model="SarLawsInfoEO.issueUnit"></Input>-->
             <!--</FormItem>-->
           <!--</Col>-->
           <!--<Col span="8">-->
             <!--<FormItem label="文件状态" prop="lawsState" class="laws-info-item">-->
               <!--<Select v-model="SarLawsInfoEO.lawsState">-->
                 <!--<Option v-for="opt in lawsStatusOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>-->
               <!--</Select>-->
             <!--</FormItem>-->
           <!--</Col>-->
         <!--</Row>-->
         <!--<Row>-->
           <!--<Col span="8">-->
             <!--&lt;!&ndash;<FormItem label="发布日期" prop="issueTime" class="laws-info-item">&ndash;&gt;-->
               <!--&lt;!&ndash;<DatePicker type="date" v-model="SarLawsInfoEO.issueTime" format="yyyy-MM-dd"></DatePicker>&ndash;&gt;-->
             <!--&lt;!&ndash;</FormItem>&ndash;&gt;-->
           <!--</Col>-->
           <!--<Col span="8">-->
             <!--&lt;!&ndash;<FormItem label="实施日期" prop="putTime" class="laws-info-item">&ndash;&gt;-->
               <!--&lt;!&ndash;<DatePicker v-model="SarLawsInfoEO.putTime"></DatePicker>&ndash;&gt;-->
             <!--&lt;!&ndash;</FormItem>&ndash;&gt;-->
           <!--</Col>-->
           <!--<Col span="8">-->
             <!--<FormItem label="代替文件号" prop="replaceLawsNum" class="laws-info-item">-->
               <!--<Input v-model="SarLawsInfoEO.replaceLawsNum"></Input>-->
             <!--</FormItem>-->
           <!--</Col>-->
         <!--</Row>-->
         <!--<Row>-->
           <!--<Col span="8">-->
             <!--<FormItem label="适用车型" prop="applyArctic" class="laws-info-item">-->
               <!--<Select v-model="SarLawsInfoEO.applyArctic" multiple style="width:200px">-->
                 <!--<Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>-->
               <!--</Select>-->
             <!--</FormItem>-->
           <!--</Col>-->
           <!--<Col span="8">-->
             <!--<FormItem label="能源种类" prop="energyKind" class="laws-info-item">-->
               <!--<Select v-model="SarLawsInfoEO.energyKind" multiple style="width:200px">-->
                 <!--<Option v-for="item in energyKindOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>-->
               <!--</Select>-->
             <!--</FormItem>-->
           <!--</Col>-->
           <!--<Col span="8">-->
             <!--<FormItem label="适用认证" prop="applyAuth" class="laws-info-item">-->
               <!--<Select v-model="SarLawsInfoEO.applyAuth" multiple style="width:200px">-->
                 <!--<Option v-for="item in applyAuthOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>-->
               <!--</Select>-->
             <!--</FormItem>-->
           <!--</Col>-->
         <!--</Row>-->
         <!--<Row>-->
           <!--<Col span="8">-->
             <!--<FormItem label="责任部门" prop="responsibleUnit" class="laws-info-item">-->
               <!--<Input v-model="SarLawsInfoEO.responsibleUnit"></Input>-->
             <!--</FormItem>-->
           <!--</Col>-->
           <!--<Col span="8">-->
             <!--<FormItem label="链接" prop="linkUri" class="laws-info-item">-->
               <!--<Input v-model="SarLawsInfoEO.linkUri"></Input>-->
             <!--</FormItem>-->
           <!--</Col>-->
         <!--</Row>-->
       <!--</Form>-->
     <!--</div>-->
     <!--<div class="save-laws-btn">-->
       <!--<Button v-if="saveInfoBtn" type="primary" @click="saveLawsInfo">提交</Button>-->
       <!--<Button @click="cancelAdd">取消</Button>-->
     <!--</div>-->
   <!--</full-modal>-->

     <Drawer
       title="Create"
       v-model="showLawsInfoModal"
       width="900"
       :styles="styles"
     >
       <Form ref="SarLawsInfoEO" :model="SarLawsInfoEO" :rules="lawsInfoFormRules" class="label-input-form">
         <input v-model="SarLawsInfoEO.editLawsId" v-show="false">
         <Row>
           <Col span="8">
             <FormItem label="国家/地区" prop="country" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.country" disabled="disabled"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="文件性质" prop="lawsProperty" class="laws-info-item">
               <Select v-model="SarLawsInfoEO.lawsProperty">
                 <Option v-for="opt in lawsPropertyOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
               </Select>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="文件号" prop="lawsNumber" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.lawsNumber"></Input>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="文件名称" prop="lawsName" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.lawsName"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="发布单位" prop="issueUnit" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.issueUnit"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="文件状态" prop="lawsState" class="laws-info-item">
               <Select v-model="SarLawsInfoEO.lawsState">
                 <Option v-for="opt in lawsStatusOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
               </Select>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="发布日期" prop="issueTime" class="laws-info-item">
               <DatePicker type="date" v-model="SarLawsInfoEO.issueTime" format="yyyy-MM-dd"></DatePicker>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="实施日期" prop="putTime" class="laws-info-item">
               <DatePicker v-model="SarLawsInfoEO.putTime"></DatePicker>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="代替文件号" prop="replaceLawsNum" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.replaceLawsNum"></Input>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="适用车型" prop="applyArctic" class="laws-info-item">
               <Select v-model="SarLawsInfoEO.applyArctic" multiple style="width:200px">
                 <Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
               </Select>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="能源种类" prop="energyKind" class="laws-info-item">
               <Select v-model="SarLawsInfoEO.energyKind" multiple style="width:200px">
                 <Option v-for="item in energyKindOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
               </Select>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="适用认证" prop="applyAuth" class="laws-info-item">
               <Select v-model="SarLawsInfoEO.applyAuth" multiple style="width:200px">
                 <Option v-for="item in applyAuthOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
               </Select>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="责任部门" prop="responsibleUnit" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.responsibleUnit"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="链接" prop="linkUri" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.linkUri"></Input>
             </FormItem>
           </Col>
         </Row>
       </Form>
     </Drawer>

   <!--导入模态框-->
   <Modal v-model="modal2" title="导入法规信息" @on-ok="importLawsInfo" @on-cancel="cancelAdd">
     <Form ref="lawsInfoImport" :model="lawsInfoImport" :label-width="80">
       <FormItem label="导入文件" prop="fileName" class="laws-info-item">
         <input type="file" ref="lawsInfoFile" id="lawsInfoFile">
       </FormItem>
     </Form>
   </Modal>

   <!--查看分解单模态框-->
   <full-modal v-model="showLawsItemsModal" v-if="showLawsItemsModal" ref="showLawsItemsModal">
     <Form ref="lawsItemsSearch" :model="lawsItemsSearch" class="label-input-form">
       <Row>
         <Col span="8">
           <FormItem label="责任部门" prop="responsibleUnit" class="laws-info-item">
             <Input v-model="lawsItemsSearch.responsibleUnit"></Input>
           </FormItem>
         </Col>
         <Col span="10">
           <Button type="primary" icon="ios-search" @click="searchLawsItemsByUnit"></Button>
           <Button type="primary" @click="openAddItemsModal">新增</Button>
           <Button type="primary" @click="importItemsModal = true">导入</Button>
         </Col>
       </Row>
     </Form>
     <div class="content">
       <loading :loading="loading">数据获取中</loading>
       <Table border ref="selection" :columns="itemsTableColumn" :data="itemsData"></Table>
     </div>
     <pagination :total="itemsTotal" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
   </full-modal>

   <!--新增修改查看法规条目模态框-->
   <full-modal v-model="addLawsItemsModal" v-if="addLawsItemsModal" ref="addLawsItemsModal">
     <div>
       <Form ref="SarLawsItemsEO" :model="SarLawsItemsEO" :rules="addLawsItemsFormRules" class="label-input-form">
         <input v-model="SarLawsItemsEO.id" v-show="false">
         <input v-model="SarLawsItemsEO.lawsId" v-show="false">
         <Row>
           <Col span="8">
             <FormItem label="条目号" prop="itemsNum" class="laws-info-item">
               <Input v-model="SarLawsItemsEO.itemsNum"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="条目名称" prop="itemsName" class="laws-info-item">
               <Input v-model="SarLawsItemsEO.itemsName"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="涉及零部件" prop="parts" class="laws-info-item">
               <Input v-model="SarLawsItemsEO.parts"></Input>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="特殊生效日期" prop="tackTime" class="laws-info-item">
               <DatePicker type="date" v-model="SarLawsItemsEO.tackTime" format="yyyy-MM-dd"></DatePicker>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="适用车型" prop="applyArctic" class="laws-info-item">
               <Select v-model="SarLawsItemsEO.applyArctic" multiple style="width:200px">
                 <Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
               </Select>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="能源种类" prop="energyKind" class="laws-info-item">
               <Select v-model="SarLawsItemsEO.energyKind" multiple style="width:200px">
                 <Option v-for="item in energyKindOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
               </Select>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="责任部门" prop="responsibleUnit" class="laws-info-item">
               <Input v-model="SarLawsItemsEO.responsibleUnit"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="备注" prop="remarks" class="laws-info-item">
               <Input v-model="SarLawsItemsEO.remarks"></Input>
             </FormItem>
           </Col>
         </Row>
       </Form>

     </div>
     <div class="save-laws-btn">
       <Button v-if="saveLawsItemsBtn" type="primary" @click="saveLawsItems">提交</Button>
       <Button @click="cancelAddItems">取消</Button>
     </div>
   </full-modal>

   <!--导入模态框-->
   <Modal v-model="importItemsModal" title="导入法规条目" @on-ok="importLawsItems" @on-cancel="cancelAdd">
     <Form ref="lawsItemsImport" :model="lawsItemsImport" :label-width="80">
       <FormItem label="导入文件" prop="fileName" class="laws-info-item">
         <input type="file" ref="lawsItemsFile" id="lawsItemsFile">
       </FormItem>
     </Form>
   </Modal>
   </div>
 </div>
</template>

<script src="./js/DomesticRegulationsDatabase.js"></script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  @import '../../../../assets/zTree/css/metroStyle/metroStyle.css';
  #domesticRegulationsDatabase{
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
    .ivu-row{
      height: 50%;
      display: -webkit-box;
      display: -ms-flexbox;
      display: flex;
      display: -ms-flex;
      -webkit-box-align: center;
      -ms-flex-align: center;
      align-items: center;
    }
  }
  .active {background-color: #93C3CF}
</style>
