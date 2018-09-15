package com.adc.da.activiti.vo;

import java.util.ArrayList;
import java.util.List;

public class StandardAdjustVO extends ApprovalProcessVO {

        private  String  flag;

        private  String  processInstanceId ;

        private List<StandardAdjustFromVO> standardAdjustFromVOList = new ArrayList<StandardAdjustFromVO>();

        public String getFlag() {
                return flag;
        }

        public void setFlag(String flag) {
                this.flag = flag;
        }

        public String getProcessInstanceId() {
                return processInstanceId;
        }

        public void setProcessInstanceId(String processInstanceId) {
                this.processInstanceId = processInstanceId;
        }

        public List<StandardAdjustFromVO> getStandardAdjustFromVOList() {
                return standardAdjustFromVOList;
        }

        public void setStandardAdjustFromVOList(List<StandardAdjustFromVO> standardAdjustFromVOList) {
                this.standardAdjustFromVOList = standardAdjustFromVOList;
        }
}
