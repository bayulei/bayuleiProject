<!-- 国内法规库 -->
<template>
 <div id="domesticRegulationsDatabase">
    <table-tools-bar>
      <div class="laws-info-form" slot="left">
        <Form ref="lawsInfo" :model="lawsInfo" :rules="lawsInfoRules" class="label-input-form">
          <FormItem label="文件号" prop="lawsNumber" class="laws-info-item">
            <Input v-model="lawsInfo.lawsNumber"></Input>
          </FormItem>
          <FormItem label="文件名称" prop="lawsName" class="laws-info-item">
            <Input v-model="lawsInfo.lawsName"></Input>
          </FormItem>
          <FormItem label="发布日期" prop="issueTime" class="laws-info-item">
            <DatePicker type="date" v-model="lawsInfo.issueTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          <Button type="primary" icon="ios-search" @click="searchLawsInfo"></Button>
        </Form>
      </div>
      <div slot="right">
        <Button type="primary" @click="openLawsModal">新增</Button>
        <Button type="primary" @click="modal2 = true">导入</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="tableColumn" :data="data"></Table>
    </div>
    <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>

   <!--新增修改查看法规模态框-->
   <full-modal v-model="showLawsInfoModal" v-if="showLawsInfoModal" ref="showLawsInfoModal">
     <div>
       <Form ref="SarLawsInfoEO" :model="SarLawsInfoEO" :rules="lawsInfoFormRules" class="label-input-form">
         <input v-model="SarLawsInfoEO.editLawsId" v-show="false">
         <Row>
           <Col span="8">
             <FormItem label="国家/地区" prop="country" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.country" disabled="disabled"></Input>
             </FormItem>
          </Col>
           <Col span="8">
             <FormItem label="文件性质" prop="country" class="laws-info-item">
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
               <Input v-model="SarLawsInfoEO.applyArctic"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="能源种类" prop="energyKind" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.energyKind"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="适用认证" prop="applyAuth" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.applyAuth"></Input>
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

     </div>
     <div class="save-laws-btn">
       <Button v-if="saveInfoBtn" type="primary" @click="saveLawsInfo">提交</Button>
       <Button @click="cancelAdd">取消</Button>
     </div>
   </full-modal>

   <!--导入模态框-->
   <Modal v-model="modal2" title="导入法规信息" @on-ok="importLawsInfo" @on-cancel="cancelAdd">
     <Form ref="lawsInfoImport" :model="lawsInfoImport" :label-width="80">
       <FormItem label="导入文件" prop="fileName" class="laws-info-item">
         <input type="file" ref="lawsInfoFile" id="lawsInfoFile" @change="lawsInfoFileBeforeUpload">
         <Button @click="openFile">导入文件</Button>
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
         <Col span="8">
           <Button type="primary" @click="openAddItemsModal">新增</Button>
           <Button type="primary" @click="modal2 = true">导入</Button>
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
       <Form ref="SarLawsItemsEO" :model="SarLawsItemsEO" :rules="lawsItemsFormRules" class="label-input-form">
         <input v-model="SarLawsItemsEO.id" v-show="false">
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
               <Input v-model="SarLawsItemsEO.applyArctic"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="能源种类" prop="energyKind" class="laws-info-item">
               <Input v-model="SarLawsItemsEO.energyKind"></Input>
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
       <Button v-if="saveInfoBtn" type="primary" @click="saveLawsItems">提交</Button>
       <Button @click="cancelAdd">取消</Button>
     </div>
   </full-modal>

   </div>
</template>

<script src="./js/DomesticRegulationsDatabase.js"></script>

<style lang="less">
  #domesticRegulationsDatabase{
    .searchBtn{
      width: 2rem;
      height: 0.72rem;
      line-height: 0.72rem;
      margin-left:0.2rem;
    }
    .laws-info-item{
      display:inline-block;
    }
  }
  .save-laws-btn{
    text-align: center;
  }
</style>
