<!-- 采标程度 -->

<template>
 <div id="degree-standard">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
       <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
       <Button type="info" class="query-button" @click="selectCollection">查询</Button>
     </div>
     <div slot="right">
       <Button type="info" @click="collectionAdd">增加</Button>
       <Button type="error"  @click="collectionBatchDel">删除</Button>
       <!--显示模态框-->
       <!--<Modal v-model="collectionModal" :title="collectionTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"-->
              <!--@on-ok="saveCollection">-->
         <!--<Form :model="collectionModelAdd" label-position="right" :label-width="80">-->
           <!--<input v-model="collectionModelAdd.id" v-show="false">-->
           <!--<FormItem label="选项">-->
             <!--<Input v-model="collectionModelAdd.parts" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
           <!--</FormItem>-->
           <!--<FormItem label="数据编码">-->
             <!--<Input v-model="collectionModelAdd.coding" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
           <!--</FormItem>-->
         <!--</Form>-->
       <!--</Modal>-->
       <Drawer
         :title="collectionTitle"
         v-model="collectionModal"
         width="900"
         :styles="styles"
       >
         <Form :model="collectionModelAdd" ref="collectionModelAdd" :rules="collectionRules"  class="label-input-form" >
           <input v-model="collectionModelAdd.id" v-show="false">
           <Row>
             <Col span="12">
               <FormItem label="选项" prop="dicTypeName"  label-position="top" class="standards-info-item">
                 <Input v-model="collectionModelAdd.dicTypeName" :disabled='modalType === 3'></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="数据编码" prop="dicTypeCode" label-position="top" class="standards-info-item">
                 <Input v-model="collectionModelAdd.dicTypeCode" :disabled='modalType === 3'></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="创建日期" prop="creationDate"  label-position="top" class="standards-info-item">
                 <Input v-model="collectionModelAdd.creationDate" :disabled='modalType === 3'></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="创建人" prop="founder"  label-position="top" class="standards-info-item">
                 <Input v-model="collectionModelAdd.founder" :disabled='modalType === 3'></Input>
               </FormItem>
             </Col>
           </Row>
         </Form>
         <div class="demo-drawer-footer" :class="{ 'disappear': modalType === 3 }">
           <Button style="margin-right: 8px" @click="closeModal">取消</Button>
           <Button type="primary" @click="saveCollection">提交</Button>
         </div>
       </Drawer>
     </div>
   </table-tools-bar>
   <div class="content">
     <loading :loading="loading">数据获取中</loading>
     <Table border ref="selection" :columns="collectionTable" :data="collectionData" @on-selection-change=" handleSelectone">
     </Table>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
   </div>
 </div>
</template>

<script src="./js/DegreeStandard.js"></script>

<style lang="less">
   #degree-standard{}
</style>
