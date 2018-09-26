<!-- 国家/地区-->

<template>
 <div id="national-area">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
       <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
       <Button type="info" class="query-button" @click="selectRegion">查询</Button>
     </div>
     <div slot="right">
       <Button type="info" @click="regionAdd">增加</Button>
       <Button type="error"  @click="regionBatchDel">删除</Button>
       <!--显示模态框-->
       <!--<Modal v-model="regionModal" :title="regionTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"-->
              <!--@on-ok="saveRegion">-->
         <!--<Form :model="regionModelAdd" label-position="right" :label-width="80">-->
           <!--<input v-model="regionModelAdd.id" v-show="false">-->
           <!--<FormItem label="选项">-->
             <!--<Input v-model="regionModelAdd.parts" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
           <!--</FormItem>-->
           <!--<FormItem label="数据编码">-->
             <!--<Input v-model="regionModelAdd.coding" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
           <!--</FormItem>-->
         <!--</Form>-->
       <!--</Modal>-->
       <Drawer
         :title="regionTitle"
         v-model="regionModal"
         width="900"
         :styles="styles"
       >
         <Form :model="regionModelAdd" ref="regionModelAdd" :rules="regionRules"  class="label-input-form" >
           <input v-model="regionModelAdd.id" v-show="false">
           <Row>
             <Col span="12">
               <FormItem label="选项" prop="dicTypeName"  label-position="top" class="standards-info-item">
                 <Input v-model="regionModelAdd.dicTypeName" :disabled='modalType === 3'></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="数据编码" prop="dicTypeCode" label-position="top" class="standards-info-item">
                 <Input v-model="regionModelAdd.dicTypeCode" :disabled='modalType === 3'></Input>
               </FormItem>
             </Col>
           </Row>
           <Row>
             <Col span="12">
               <FormItem label="创建日期" prop="creationDate"  label-position="top" class="standards-info-item">
                 <Input v-model="regionModelAdd.creationDate" :disabled='modalType === 3'></Input>
               </FormItem>
             </Col>
             <Col span="12">
               <FormItem label="创建人" prop="founder"  label-position="top" class="standards-info-item">
                 <Input v-model="regionModelAdd.founder" :disabled='modalType === 3'></Input>
               </FormItem>
             </Col>
           </Row>
         </Form>
         <div class="demo-drawer-footer" :class="{ 'disappear': modalType === 3 }">
           <Button style="margin-right: 8px" @click="closeModal">取消</Button>
           <Button type="primary" @click="saveRegion">提交</Button>
         </div>
       </Drawer>
     </div>
   </table-tools-bar>
   <div class="content">
     <loading :loading="loading">数据获取中</loading>
     <Table border ref="selection" :columns="regionTable" :data="regionData" @on-selection-change=" handleSelectone">
     </Table>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
   </div>
 </div>
</template>

<script src="./js/NationalArea.js"></script>

<style lang="less">
   #national-area{}
</style>
