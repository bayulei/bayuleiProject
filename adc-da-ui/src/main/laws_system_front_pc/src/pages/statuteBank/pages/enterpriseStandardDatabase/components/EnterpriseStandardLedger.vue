<!-- 企业标准台账 -->
<template>
  <div class="personal-data">
    <table-tools-bar>
      <div class="sar-compile-form" slot="left">
        <Form ref="sarCompile" :model="sarCompile" :rules="sarCompileRules" class="label-input-form">
          <FormItem label="标准编号" prop="standNumber" class="laws-info-item">
            <Input v-model="sarCompile.standNumber"></Input>
          </FormItem>
          <FormItem label="标准名称" prop="standName" class="laws-info-item">
            <Input v-model="sarCompile.standName"></Input>
          </FormItem>
          <FormItem label="标准细类" prop="classifyCode" class="laws-info-item">
            <Input v-model="sarCompile.classifyCode"></Input>
          </FormItem>
          <FormItem label="分类代号" prop="busStandSubclass" class="laws-info-item">
            <Input v-model="sarCompile.busStandSubclass"></Input>
          </FormItem>
          <Button type="primary" icon="ios-search" @click="searchSarCompile"></Button>
          <Button type="primary" @click="openAddModal">新增</Button>
          <Button type="primary" @click="">导出</Button>
        </Form>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table width="100%" border ref="selection" :columns="sarCompileTable" :data="sarCompileListDatas"></Table>
    </div>
    <pagination :total="sarCompileTotal" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>

    <Drawer :title="showSarCompileTitle" v-model="showSarCompileModal" width="900" :styles="styles">
      <Form ref="submitSarCompile" :model="submitSarCompile" :rules="submitSarCompileFormRules" class="label-input-form">
        <input v-model="submitSarCompile.id" v-show="false">
        <Row>
          <Col span="12">
          <FormItem label="标准大类名称" prop="busStandClassify" class="laws-info-item">
            <Input v-model="submitSarCompile.busStandClassify"></Input>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="标准细类名称" prop="busStandSubclass" class="laws-info-item">
            <Input v-model="submitSarCompile.busStandSubclass"></Input>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="分类代号" prop="classifyCode" class="laws-info-item">
            <Input v-model="submitSarCompile.classifyCode"></Input>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="标准编号" prop="standNumber" class="laws-info-item">
            <Input v-model="submitSarCompile.standNumber"></Input>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="标准名称" prop="standName" class="laws-info-item">
            <Input v-model="submitSarCompile.standName"></Input>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="发布日期" prop="issueTime" class="laws-info-item">
            <DatePicker type="date" v-model="submitSarCompile.issueTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="实施日期" prop="putTime" class="laws-info-item">
            <DatePicker type="date" v-model="submitSarCompile.putTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="实施年份" prop="putYear" class="laws-info-item">
            <Input v-model="submitSarCompile.putYear"></Input>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="代替标准" prop="replaceStandNum" class="laws-info-item">
            <Input v-model="submitSarCompile.replaceStandNum"></Input>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="责任部门" prop="dutyUnit" class="laws-info-item">
            <Input v-model="submitSarCompile.dutyUnit"></Input>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="备注" prop="remarks" class="laws-info-item">
            <Input v-model="submitSarCompile.remarks"></Input>
          </FormItem>
          </Col>
        </Row>
      </Form>
      <div class="demo-drawer-footer">
        <Button style="margin-right: 8px" @click="cancelSubmit">取消</Button>
        <Button type="primary" @click="saveSarCompile">提交</Button>
      </div>
    </Drawer>
  </div>
</template>

<script src="../js/enterpriseStandardLedger.js"></script>

<style lang="less">
  #enterpriseStandardLedger{
    .panel-content{
      padding: 0.2rem;
      padding-bottom: 0;
    }
  }
  .personal-data{
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
</style>
