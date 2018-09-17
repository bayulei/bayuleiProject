package com.adc.da.activiti.vo;

public class StandardReleaseVO extends ApprovalProcessVO {


        private  String  flag;

        private  String  processInstanceId ;

        private  String  waitReleaseStandard ;

        private  String  standardFiles ;

        private  String  standardEngineerId ;

        private  String  releativeDepatmentIds ;

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

        public String getWaitReleaseStandard() {
                return waitReleaseStandard;
        }

        public void setWaitReleaseStandard(String waitReleaseStandard) {
                this.waitReleaseStandard = waitReleaseStandard;
        }

        public String getStandardFiles() {
                return standardFiles;
        }

        public void setStandardFiles(String standardFiles) {
                this.standardFiles = standardFiles;
        }

        public String getStandardEngineerId() {
                return standardEngineerId;
        }

        public void setStandardEngineerId(String standardEngineerId) {
                this.standardEngineerId = standardEngineerId;
        }

        public String getReleativeDepatmentIds() {
                return releativeDepatmentIds;
        }

        public void setReleativeDepatmentIds(String releativeDepatmentIds) {
                this.releativeDepatmentIds = releativeDepatmentIds;
        }
}
