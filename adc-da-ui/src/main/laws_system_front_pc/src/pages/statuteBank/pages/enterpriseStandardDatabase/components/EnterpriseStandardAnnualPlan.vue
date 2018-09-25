<!-- 企业标准年度计划 -->
<template>
  <div class="personal-data">
    <table-tools-bar>
      <div class="stand-plan-form" slot="left">
        <Form ref="standPlan" :model="standPlan" :rules="standPlanRules" class="label-input-form">
          <FormItem label="标准名称" prop="standName" class="laws-info-item">
            <Input v-model="standPlan.standName"></Input>
          </FormItem>
          <FormItem label="制修订类别" prop="makeRevisonType" class="laws-info-item">
            <Input v-model="standPlan.makeRevisonType"></Input>
          </FormItem>
          <FormItem label="代替标准代号" prop="replaceStandNum" class="laws-info-item">
            <Input v-model="standPlan.replaceStandNum"></Input>
          </FormItem>
          <!--<FormItem label="编制部门" prop="compileUnit" class="laws-info-item">
            <Input v-model="standPlan.compileUnit"></Input>
          </FormItem>-->
          <Button type="primary" icon="ios-search" @click="searchStandPlan"></Button>
          <Button type="primary" @click="openPlanModal(null,'addOpt')">新增</Button>
          <Button type="primary" @click="exportStandPlan">导出</Button>
        </Form>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table width="100%" border ref="selection" :columns="standPlanTable" :data="standPlanListDatas" @on-select="getSelectedDatas"></Table>
    </div>
    <pagination :total="standPlanTotal" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    <Drawer :title="showStandPlanTitle" v-model="showStandPlanModal" width="900">
      <Form ref="submitStandPlan" :model="submitStandPlan" :rules="submitStandPlanFormRules" class="label-input-form">
        <input v-model="submitStandPlan.id" v-show="false">
        <Row>
          <Col span="12">
          <FormItem label="标准名称" prop="standName" class="laws-info-item">
            <Input v-model="submitStandPlan.standName"></Input>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="制修订类别" prop="makeRevisonType" class="laws-info-item">
            <Select v-model="submitStandPlan.makeRevisonType">
              <Option v-for="opt in makeRevisonTypeOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
            </Select>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="代替标准代号" prop="replaceStandNum" class="laws-info-item">
            <Input v-model="submitStandPlan.replaceStandNum"></Input>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="编制部门" prop="compileUnit" class="laws-info-item">
            <Input v-model="submitStandPlan.compileUnit"></Input>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="评审级别" prop="reviewLevel" class="laws-info-item">
            <Select v-model="submitStandPlan.reviewLevel">
              <Option v-for="opt in reviewLevelOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
            </Select>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="主要编制者" prop="compilersUser" class="laws-info-item">
            <Input v-model="submitStandPlan.compilersUser"></Input>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="完成部门评审提交时间" prop="finishReviewTime" class="laws-info-item">
            <DatePicker type="date" v-model="submitStandPlan.finishReviewTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="报批稿完成时间" prop="approvalDraftTime" class="laws-info-item">
            <DatePicker type="date" v-model="submitStandPlan.approvalDraftTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="评审稿提交日期" v-if="reviewSubmitTime" prop="reviewSubmitTime" class="laws-info-item">
            <DatePicker type="date" v-model="submitStandPlan.reviewSubmitTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="评审会日期" v-if="reviewMeetTime" prop="reviewMeetTime" class="laws-info-item">
            <DatePicker type="date" v-model="submitStandPlan.reviewMeetTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
          <FormItem label="评审后修改提交时间" v-if="reviewModifyTime" prop="reviewModifyTime" class="laws-info-item">
            <DatePicker type="date" v-model="submitStandPlan.reviewModifyTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          </Col>
          <Col span="12">
          <FormItem label="开始流程日期" v-if="startFlowTime" prop="startFlowTime" class="laws-info-item">
            <DatePicker type="date" v-model="submitStandPlan.startFlowTime" format="yyyy-MM-dd"></DatePicker>
          </FormItem>
          </Col>
        </Row>
      </Form>
      <div class="demo-drawer-footer">
        <Button style="margin-right: 8px" @click="cancelSubmit">取消</Button>
        <Button type="primary" @click="saveStandPlan">提交</Button>
      </div>
    </Drawer>

  </div>
</template>

<script src="../js/enterpriseStandardAnnualPlan.js"></script>

<style lang="less">
  #enterpriseStandardAnnualPlan{
    .panel-content{
      padding: 0.2rem;
      padding-bottom: 0;
    }
  }
</style>
