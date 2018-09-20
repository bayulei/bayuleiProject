<!-- 资料中心模块配置 -->
<template>
  <div class="InformationCenterConfig">
      <div class="container">
        <div class="header">
          <Button type="info" @click="informationAdd">新增</Button>
          <!--<Button type="error" style="margin-left: 15px" @click="informationBatchDel">删除</Button>-->
          <!-- 显示模态框 -->
          <!--<Modal v-model="informationModal" :title="informationTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"-->
                 <!--@on-ok="saveInformation">-->
            <!--<Form :model="informationModelAdd" label-position="right" :label-width="80">-->
              <!--<input v-model="informationModelAdd.id" v-show="false">-->
              <!--<FormItem label="模块类型">-->
                <!--<Input v-model="informationModelAdd.moduleType" :style="{width:6+'rem'}" disabled></Input>-->
              <!--</FormItem>-->
              <!--<FormItem label="模块名称">-->
                <!--<Input v-model="informationModelAdd.moduleName" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
              <!--</FormItem>-->
            <!--</Form>-->
          <!--</Modal>-->
          <Drawer
            :title="informationTitle"
            v-model="informationModal"
            width="900"
            :styles="styles"
          >
            <Form :model="informationModelAdd" ref="informationModelAdd" :rules="informationRules"  class="label-input-form" >
              <input v-model="informationModelAdd.id" v-show="false">
              <Row>
                <Col span="12">
                  <FormItem label="模块类型" prop="moduleType"  label-position="top" class="standards-info-item">
                    <Input v-model="informationModelAdd.moduleType" disabled></Input>
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem label="模块名称" prop="moduleName" label-position="top" class="standards-info-item">
                    <Input v-model="informationModelAdd.moduleName" :disabled='modalType === 3'></Input>
                  </FormItem>
                </Col>
              </Row>
            </Form>
            <div class="demo-drawer-footer" :class="{ 'disappear': modalType === 3 }">
              <Button style="margin-right: 8px" @click="closeModal">取消</Button>
              <Button type="primary" @click="saveInformation">提交</Button>
            </div>
          </Drawer>
        </div>
        <div class="content">
          <loading :loading="loading">数据获取中</loading>
          <Table border ref="selection" :columns="informationTable" :data="informationData" @on-selection-change="handleSelectone">
          </Table>
          <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
        </div>
      </div>
  </div>
</template>
<script src="./js/informationCenterConfig.js"></script>
<style lang="less">
  .InformationCenterConfig{
    display: flex;
    background: #FFF;
    .container{
      width: 100%;
      margin: 1rem;
    }
    .header{
      margin-bottom:0.7rem;
    }
    .content{
      width: 100%;
      height: calc(100% - 20px);
      overflow-x: hidden;
      overflow-y: auto;
    }
  }
  .disappear{
    display: none;
  }
  .ivu-modal-confirm .ivu-modal-confirm-footer{
    display: block;
  }
   .content .ivu-table-wrapper .ivu-table-tip{
    width: 100%;
    height: calc(100% - 8px);
    overflow: hidden;
  }
  .content .ivu-table-wrapper .ivu-table-tip table {
    height: 100%;
  }
</style>
