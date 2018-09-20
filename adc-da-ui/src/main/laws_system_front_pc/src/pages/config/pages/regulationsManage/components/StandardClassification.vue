<!-- 标准/文件性质 -->
<template>
  <div class="standard-classification">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
        <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
        <Button type="info" class="query-button" @click="selectClass">查询</Button>
      </div>
      <div slot="right">
        <Button type="info" @click="classAdd">增加</Button>
        <Button type="error"  @click="classBatchDel">删除</Button>
        <!--显示模态框-->
        <!--<Modal v-model="classModal" :title="classTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"-->
               <!--@on-ok="saveClass">-->
          <!--<Form :model="classModelAdd" label-position="right" :label-width="80">-->
            <!--<input v-model="classModelAdd.id" v-show="false">-->
            <!--<FormItem label="选项">-->
              <!--<Input v-model="classModelAdd.dicTypeName" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
            <!--</FormItem>-->
            <!--<FormItem label="数据编码">-->
              <!--<Input v-model="classModelAdd.dicTypeCode" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>-->
            <!--</FormItem>-->
          <!--</Form>-->
        <!--</Modal>-->
        <Drawer
          :title="classTitle"
          v-model="classModal"
          width="900"
          :styles="styles"
          @on-close="cleanValue"
        >
          <Form :model="classModelAdd" ref="classModelAdd" :rules="classRules">
            <input v-model="classModelAdd.id" v-show="false">
            <Row :gutter="32">
              <Col span="12">
                <FormItem label="选项" prop="dicTypeName"  label-position="top">
                  <Input v-model="classModelAdd.dicTypeName" :disabled='modalType === 3'></Input>
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem label="数据编码" prop="dicTypeCode" label-position="top">
                  <Input v-model="classModelAdd.dicTypeCode" :disabled='modalType === 3'></Input>
                  </FormItem>
              </Col>
            </Row>
          </Form>
          <div class="demo-drawer-footer">
            <Button style="margin-right: 8px" @click="closeModal">取消</Button>
            <Button type="primary" @click="saveClass">提交</Button>
          </div>
        </Drawer>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="classTable" :data="classData" @on-selection-change=" handleSelectone">
      </Table>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    </div>
  </div>
</template>

<script src="./js/StandardClassification.js"></script>

<style lang="less">
  .standard-classification {
  }
  .hide-modal-footer{
    .ivu-modal-footer{
      display: none;
    }
  }
  .ivu-modal-confirm .ivu-modal-confirm-footer{
    display: block;
  }
  .demo-drawer-footer{
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    background: #fff;
  }
</style>
