<!-- 企业标准库 -->
<template>
  <div id="enterpriseStandardDatabase">
    <div class="tree">
      <Tree :data="tree" :render="renderContent"></Tree>
    </div>
    <div class="tree-right">
      <!-- 顶部工具栏 -->
      <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false">
        <div slot="left">
          <label-input v-model="sarStandardsSearch.classifyCode" placeholder="根据分类代号查找" clearable label="分类代号" class="my-input" />
          <label-input v-model="sarStandardsSearch.standCode" placeholder="根据标准编号查找" clearable label="标准编号" class="my-input" />
          <label-input v-model="sarStandardsSearch.standName" placeholder="根据标准名称查找" clearable label="标准名称" class="my-input" />
          <!--<br><br>-->
          <!--<label-input v-model="sarStandardsSearch.standGenera" placeholder="根据标准大类查找" clearable label="标准大类" class="my-input" />-->
          <!--<label-input v-model="sarStandardsSearch.standSubclass" placeholder="根据标准细类查找" clearable label="标准细类" class="my-input" />-->
          <!--<label-select v-model="sarStandardsSearch.issueTime" :options="issueTimeOptions" placeholder="根据发布日期查找" clearable label="发布日期" class="my-input" />-->
          <!--<label-select v-model="sarStandardsSearch.putTime" :options="putTimeOptions" placeholder="根据实施日期查找" clearable label="实施日期" class="my-input" />-->
          <!--<label-input v-model="sarStandardsSearch.responsibleUnit" placeholder="根据责任部门查找" clearable label="责任部门" class="my-input" />-->
          <Button type="primary" icon="ios-search" :loading="searching" @click="getBussionStandTable"></Button>
          <Button type="primary"  @click="clearAllSearch">清空查询</Button>
        </div>
        <div slot="right">
          <Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>
        </div>
      </table-tools-bar>
      <div class="content">
        <div class="action-bar">
          <Checkbox :value="checkAll" size="large" @on-change="handleSelectAll" :indeterminate="indeterminate">全选</Checkbox>
          <Button type="info" size="small" @click="exportStandard">下载</Button>
          <Button type="primary" size="small" @click="addModal">新增</Button>
          <Button type="error" size="small" @click="clickDropMenu('deleteMenu')">删除</Button>
          <Dropdown trigger="click" style="margin-left: 20px" @on-click="clickDropMenu">
            <Button type="primary" icon="ios-arrow-down">设置</Button>
            <DropdownMenu slot="list">
              <DropdownItem name="newMenu">新建</DropdownItem>
              <DropdownItem name="editMenu">编辑</DropdownItem>
              <DropdownItem name="deleteMenu">删除</DropdownItem>
            </DropdownMenu>
          </Dropdown>
          <Button type="primary"  :loading="searching" @click="addImportModal(1)">导入</Button>
          <Button type="primary" @click="configurationStandard">配置标准</Button>
        </div>
        <div class="content-detail" v-if="standinfoList.length > 0">
          <div class="card " v-for="(item, index) in standinfoList" :key="index" :class="{ 'selected': item.checked }" @click="handleCardClick(item)">
            <Row>
              <Col span="5">
              <Checkbox v-model="item.checked" size="large"></Checkbox>
              标准编号: {{ item.standCode }}
              </Col>
              <Col span="4" push="1">
              <b>《{{ item.standName }}》</b>
              </Col>
              <Col span="4" push="2">大类名称:{{ item.standGenera }}</Col>
              <Col span="4" push="2">细类名称:{{ item.standSubclass }}</Col>
              <Col span="3" push="4">
              <Icon type="md-star" size="26" style="margin-right:5px"></Icon>
              <Icon type="ios-redo" size="26"></Icon>
              </Col>
            </Row>
            <Row>
              <Col span="4">分类代号: {{ item.classifyCode }}</Col>
              <Col span="4" push="2">发布日期:{{ item.issueTime }}</Col>
              <Col span="4" push="3">实施日期:{{ item.putTime }}</Col>
              <Col span="4" push="3">代替标准:{{ item.replaceStandNum }}</Col>
              <Col span="6" push="2">
              <Button @click = "goProcess(item)">流程</Button>
              <Button @click = "selectStandardPro(item,'show')">查看</Button>
              <Button @click = "selectStandardPro(item,'edit')">编辑</Button>
              <Button @click = "selectSarStandItems(item.id)">查看表单</Button>
              </Col>
            </Row>
            <Row>
              <Col span="4">责任部门:{{ item.responsibleUnit }}</Col>
              <Col span="4" push="2"></Col>
              <Col span="4" push="3"></Col>
              <Col span="4" push="3"></Col>
              <Col span="6" push="2"></Col>
            </Row>
          </div>
        </div>
        <has-no-data pClass="content-detail" v-else></has-no-data>
        <loading :loading="loading">数据获取中</loading>
      </div>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
      <!-- 新增、编辑模态窗 -->
      <full-modal v-model="modalshowflag" v-if="modalshowflag" ref="modalshow" >
        <!--    新增样式     -->
        <div class="standards-info-form">
          <Form ref="sarStandardsInfoForm" :model="sarBussionessStandEO" :rules="sarBussionessStandRules" class="label-input-form">
            <Row>
              <Col span="12">
              <FormItem label="大类" prop="country" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.standGenera" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="细类" prop="standSort" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.standSubclass" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="分类代号" prop="applyArctic" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.classifyCode" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="标准编号" prop="standCode" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.standCode" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="标准名称" prop="standName" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.standName" :disabled="formdisableflag" ></Input>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="标准英文名称" prop="standEnName" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.standEnName" :disabled="formdisableflag" ></Input>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="适用车型" prop="applyArctic" class="standards-info-item">
                <Select v-model="sarBussionessStandEO.applyArctic" multiple :disabled="formdisableflag" >
                  <Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="能源种类" prop="energyKind" class="standards-info-item">
                <Select v-model="sarBussionessStandEO.energyKind" multiple :disabled="formdisableflag">
                  <Option v-for="opt in energyKindOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
                </Select>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="发布日期" prop="issueTime" class="standards-info-item">
               <DatePicker v-model="sarBussionessStandEO.issueTime" :disabled="formdisableflag"></DatePicker>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="实施日期" prop="putTime" class="standards-info-item">
               <DatePicker v-model="sarBussionessStandEO.putTime" :disabled="formdisableflag"></DatePicker>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="实施年份" prop="putYear" class="standards-info-item">
                <DatePicker v-model="sarBussionessStandEO.putYear" :disabled="formdisableflag"></DatePicker>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="首发日期" prop="firstPutTime" class="standards-info-item">
                <DatePicker v-model="sarBussionessStandEO.firstPutTime" :disabled="formdisableflag"></DatePicker>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="引用标准" prop="interStandNum" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.quoteStand" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="代替标准号" prop="replaceStandNum" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.replaceStandNum" @on-blur="testReplaceStandNum" placeholder="输入多个标准号，以逗号隔开" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="被代替标准号" prop="replacedStandNum" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.replacedStandNum" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="标准状态" prop="standState" class="standards-info-item">
                <Select v-model="sarBussionessStandEO.standStatus" :disabled="formdisableflag" >
                  <Option v-for="opt in standStateOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
                </Select>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="关键词" prop="tags" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.tags" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="标准文本" prop="synopsis" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.standFile" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <FormItem label="责任部门" prop="responsibleUnit" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.responsibleUnit" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
              <Col span="12">
              <FormItem label="主要编制者" prop="draftUser" class="standards-info-item">
                <Input v-model="sarBussionessStandEO.citationUser" :disabled="formdisableflag"></Input>
              </FormItem>
              </Col>
            </Row>
          </Form>
          <Button v-if="!formdisableflag" type="primary" @click="saveOrUpdateStands" >保存修改</Button>
        </div>
      </full-modal>
      <!-- 导入模态窗 -->
      <Modal v-model="importModalshowflag" title="导入文件" >
        <Upload :action="importExcelUrl" ref="importfile" name="file" :format="['xlsx']" :on-format-error="handleFormatError" :on-success="importFileSuccess">
          <Button icon="ios-cloud-upload-outline">选择文件</Button>
        </Upload>
      </Modal>
      <!-- 新增二级菜单模态窗 -->
      <Modal v-model="menuModalFlag" title="新增目录" @on-ok="newMenu" @on-cancel="closeModal" ref="menuRefModal">
        <Form  :model="sarMenu" :rules="sarMenuRules" class="label-input-form">
          <FormItem label="名称" prop="menuName" class="standards-info-item">
            <Input v-model="sarMenu.menuName"></Input>
          </FormItem>
          <FormItem label="排序号" prop="displaySeq" class="standards-info-item">
            <Input v-model="sarMenu.displaySeq"   placeholder="只允许输入数字" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></Input>
          </FormItem>
        </Form>
      </Modal>
      <!--  查看标准条目列表   -->
      <full-modal v-model="modalStandItemflag" v-if="modalStandItemflag" ref="" >
        <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false" class="label-input-form">
          <div slot="left">
          </div>
          <div slot="right" style="margin-right: 70px">
            <Button type="primary" icon="ios-add" :loading="searching" @click="addItemModal">新增</Button>
            <Button type="primary" icon="ios-add" :loading="searching" @click="addImportModal(2)">导入</Button>
            <Button type="primary" @click="isAdvancedSearch = true">删除</Button>
            <!--<Button type="primary" @click="">保存</Button>-->
            <Button type="primary" @click="exportStandard">导出</Button>
          </div>
        </table-tools-bar>
        <Table border ref="" :columns="standItemTableColumn" :data="standItemList"></Table>
      </full-modal>
      <!--  新增标准条目  -->
      <full-modal v-model="modalItemaddShowflag" v-if="modalItemaddShowflag" ref="modalshow" >
        <div class="standardsItem-info-form">
          <Form ref="" :model="standItemEO"  class="label-input-form">
            <FormItem label="条目号" prop="" class="standards-info-item">
              <Input v-model="standItemEO.itemsNum" :disabled="formdisableflag"></Input>
            </FormItem>
            <FormItem label="条目名称" prop="" class="standards-info-item">
              <Input v-model="standItemEO.itemsName" :disabled="formdisableflag" ></Input>
            </FormItem>
            <FormItem label="涉及零部件" prop="" class="standards-info-item">
              <Input v-model="standItemEO.parts" :disabled="formdisableflag" ></Input>
            </FormItem>
            <FormItem label="特殊生效日期" prop="" class="standards-info-item">
              <DatePicker v-model="standItemEO.tackTime" :disabled="formdisableflag"></DatePicker>
            </FormItem>
            <FormItem label="适用车型" prop="applyArctic" class="standards-info-item">
              <Select v-model="standItemEO.applyArctic" multiple :disabled="formdisableflag" >
                <Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
              </Select>
            </FormItem>
            <FormItem label="能源种类" prop="energyKind" class="standards-info-item">
              <Select v-model="standItemEO.energyKind" multiple :disabled="formdisableflag">
                <Option v-for="opt in energyKindOptions" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
              </Select>
            </FormItem>
            <FormItem label="责任部门" prop="responsibleUnit" class="standards-info-item">
              <Input v-model="standItemEO.responsibleUnit" :disabled="formdisableflag"></Input>
            </FormItem>
            <FormItem label="备注" prop="" class="standards-info-item">
              <Input v-model="standItemEO.remarks" :disabled="formdisableflag"></Input>
            </FormItem>
          </Form>
          <Button v-if="!formdisableflag" type="primary" @click="saveOrUpdateStandItem" >保存修改</Button>
        </div>
      </full-modal>
    </div>
  </div>
</template>

<script src="./js/enterpriseStandardDatabase.js"></script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  @import '../../../../assets/zTree/css/metroStyle/metroStyle.css';
  #enterpriseStandardDatabase{
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
        height: 33%;
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
</style>
