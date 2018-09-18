<!-- 本地产品/项目库 -->
<template>
  <div id="localProductDatabase">
    <div class="content">
      <div class="action-bar">
        <Button type="info" size="small">下载</Button>
        <Button type="primary" size="small" @click="addProductModal">新增</Button>
        <Button type="error" size="small" @click="deleteLocalProduct()">删除</Button>
      </div>
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="" :columns="localProTableColumn" :data="localProTableList"></Table>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    </div>

    <!--     新增本地企业     -->
    <full-modal v-model="modalProductAddShowflag" v-if="modalProductAddShowflag" ref="modalProductAddShow" >
      <div class="standardsItem-info-form">
        <Form ref="" :model="localProEO"  class="label-input-form">
          <FormItem label="产品/项目代号" prop="" class="standards-info-item">
            <Input v-model="localProEO.prodectCode" :disabled="formdisableflag"></Input>
          </FormItem>
          <FormItem label="产品系列" prop="" class="standards-info-item">
            <Input v-model="localProEO.productSet" :disabled="formdisableflag" ></Input>
          </FormItem>
          <FormItem label="产品名称" prop="" class="standards-info-item">
            <Input v-model="localProEO.productName" :disabled="formdisableflag" ></Input>
          </FormItem>
          <FormItem label="产品品牌" prop="" class="standards-info-item">
            <Input v-model="localProEO.productBrand" :disabled="formdisableflag"></Input>
          </FormItem>
          <FormItem label="产品类别" prop="" class="standards-info-item">
            <Select v-model="localProEO.productType" multiple :disabled="formdisableflag" >
              <Option v-for="item in productTypeOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          </FormItem>
          <FormItem label="能源种类" prop="" class="standards-info-item">
            <Select v-model="localProEO.energyKind" multiple :disabled="formdisableflag">
              <Option v-for="opt in emergyKindOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
            </Select>
          </FormItem>
          <FormItem label="车型附件" prop="" class="standards-info-item">
            <Input v-model="localProEO.carModeFile" :disabled="formdisableflag"></Input>
          </FormItem>
        </Form>
        <Button v-if="!formdisableflag" type="primary" @click="saveOrUpdateLocalPro" >保存修改</Button>
      </div>
    </full-modal>

    <!--     新增本地企业     -->
    <full-modal v-model="modalProDataShowflag" v-if="modalProDataShowflag" ref="" >
      <Card style="width:100%">
        <Row>
          <Col span="5">产品/项目代号: {{ localProEO.prodectCode }}</Col>
          <Col span="4" push="1"><b>产品系列:《{{ localProEO.productSet }}》</b></Col>
          <Col span="4" push="2">产品名称:{{ localProEO.productName }}</Col>
          <Col span="4" push="2">产品品牌:{{ localProEO.productBrand }}</Col>
        </Row>
        <Row style="margin-top: 20px">
          <Col span="5">产品类别:{{ localProEO.productTypeShow }}</Col>
          <Col span="4" push="1">能源种类: {{ localProEO.energyKindShow }}</Col>
          <Col span="4" push="2">车型附件: {{ localProEO.carModeFile }}</Col>
          <Col span="4" push="2"><Button @click = "selectStandLawsForProduct">新增</Button></Col>
        </Row>
      </Card>
      <br/>
      <Tabs type="card"  @on-click="tabsClick">
        <TabPane  v-for="item in proveTypeOptions" :label="item.label"  :name="item.value" >
          <Table border ref="" :columns="localProDataTableColumn" :data="localProDataTableList"></Table>
        </TabPane>
      </Tabs>
    </full-modal>

    <!--     新增本地企业     -->
    <full-modal v-model="modalProDataAddflag" v-if="modalProDataAddflag" ref="" >
      <Button @click = "saveProData">保存</Button>
      <Table border ref="" :columns="selectProDataTableColumn" :data="selectProDataTableList" @on-select="selectOneStand" @on-select-all="selectAllStand" @on-select-cancel="cancleSelectStand"></Table>
    </full-modal>

  </div>
</template>

<script src="./js/localProductDatabase.js"></script>

<style lang="less">
  #localProductDatabase{

  }
</style>
