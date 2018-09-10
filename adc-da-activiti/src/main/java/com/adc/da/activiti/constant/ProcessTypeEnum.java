package com.adc.da.activiti.constant;

/**
 * 流程类型枚举类
 *
 * @ClassName:ProcessTypeEnum
 * @author: DuYunbao
 * date: 2018/9/7 14:02
 */
public enum ProcessTypeEnum {

    TAKE_ADVICE_PROCESS("1", "标准征求意见审批流程"),
    RULE_NOTICE_PROCESS("2", "标准法规实施通知及排查流程"),
    RULE_SUIT_PROCESS("3", "项目标准法规符合性分析流程"),
    STANDARD_ERROR_PROCESS("4", "企业标准勘误审批流程"),
    STANDARD_RELEASE_PROCESS("5", "企业标准发布审批流程"),
    STANDARD_REVIEW_PROCESS("6", "企业标准有效性复审流程"),
    STANDARD_ADJUST_PROCESS("7", "企业标准制修订计划调整申请流程"),
    STANDARD_YEAR_PLAN_PROCESS("8", "企业技术标准制修订年度计划审批发布流程"),
    STANDARD_BUY_PROCESS("9", "标准购买审批流程"),
    VEHICLE_VERIFICATION_PROCESS("10", "标准购买审批流程");

    private String value;
    private String text;

    private ProcessTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String getText(String value) {
        if (ProcessTypeEnum.TAKE_ADVICE_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.TAKE_ADVICE_PROCESS.getText();
        }
        if (ProcessTypeEnum.RULE_NOTICE_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.RULE_NOTICE_PROCESS.getText();
        }
        if (ProcessTypeEnum.RULE_SUIT_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.RULE_SUIT_PROCESS.getText();
        }
        if (ProcessTypeEnum.STANDARD_ERROR_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.STANDARD_ERROR_PROCESS.getText();
        }
        if (ProcessTypeEnum.STANDARD_RELEASE_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.STANDARD_RELEASE_PROCESS.getText();
        }
        if (ProcessTypeEnum.STANDARD_REVIEW_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.STANDARD_REVIEW_PROCESS.getText();
        }
        if (ProcessTypeEnum.STANDARD_ADJUST_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.STANDARD_ADJUST_PROCESS.getText();
        }
        if (ProcessTypeEnum.STANDARD_YEAR_PLAN_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.STANDARD_YEAR_PLAN_PROCESS.getText();
        }
        if (ProcessTypeEnum.STANDARD_BUY_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.STANDARD_BUY_PROCESS.getText();
        }
        if (ProcessTypeEnum.VEHICLE_VERIFICATION_PROCESS.getValue().equals(value)) {
            return ProcessTypeEnum.VEHICLE_VERIFICATION_PROCESS.getText();
        }
        return null;
    }


}
