<!-- 所属类别 -->

<template>
  <div class="classification">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
        <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
        <Button type="info" class="query-button" @click="selectClassification">查询</Button>
      </div>
      <div slot="right">
        <Button type="info" @click="classificationAdd">增加</Button>
        <Button type="error"  @click="classificationBatchDel">删除</Button>
        <!--显示模态框-->
        <!--<Modal v-model="classificationModal" :title="classificationTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"-->
               <!--@on-ok="saveClassification">-->
          <!--<Form :model="classificationModelAdd" label-position="right" :label-width="80">-->
            <!--<input v-model="classificationModelAdd.id" v-show="false">-->
            <!--<FormItem label="选项">-->
              <!--<Input v-model="classificationModelAdd.parts" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
            <!--</FormItem>-->
            <!--<FormItem label="数据编码">-->
              <!--<Input v-model="classificationModelAdd.coding" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
            <!--</FormItem>-->
          <!--</Form>-->
        <!--</Modal>-->
        <Drawer
          :title="classificationTitle"
          v-model="classificationModal"
          width="900"
          :styles="styles"
        >
          <Form :model="classificationModelAdd" ref="classificationModelAdd" :rules="classificationRules"  class="label-input-form" >
            <input v-model="classificationModelAdd.id" v-show="false">
            <Row>
              <Col span="12">
                <FormItem label="选项" prop="dicTypeName"  label-position="top" class="standards-info-item">
                  <Input v-model="classificationModelAdd.dicTypeName" :disabled='modalType === 3'></Input>
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem label="数据编码" prop="dicTypeCode" label-position="top" class="standards-info-item">
                  <Input v-model="classificationModelAdd.dicTypeCode" :disabled='modalType === 3'></Input>
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
                <FormItem label="创建日期" prop="creationDate"  label-position="top" class="standards-info-item">
                  <Input v-model="classificationModelAdd.creationDate" :disabled='modalType === 3'></Input>
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem label="创建人" prop="founder"  label-position="top" class="standards-info-item">
                  <Input v-model="classificationModelAdd.founder" :disabled='modalType === 3'></Input>
                </FormItem>
              </Col>
            </Row>
          </Form>
          <div class="demo-drawer-footer" :class="{ 'disappear': modalType === 3 }">
            <Button style="margin-right: 8px" @click="closeModal">取消</Button>
            <Button type="primary" @click="saveClassification">提交</Button>
          </div>
        </Drawer>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="classificationTable" :data="classificationData" @on-selection-change=" handleSelectone">
      </Table>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    </div>
  </div>
</template>

<script src="./js/Category.js"></script>

<style lang="less" scoped>
  .classification{

  }
</style>
