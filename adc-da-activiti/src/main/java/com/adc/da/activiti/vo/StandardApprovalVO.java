package com.adc.da.activiti.vo;

import java.io.Serializable;
import java.util.List;

public class StandardApprovalVO extends ApprovalProcessVO implements Serializable {


    private static final long serialVersionUID = 3362990175004982375L;

    /**
     *  附件
     */
    private String fileUrl;

    /**
     *  分发的接受人（所有编制部门标准化员）
     */
    private List<String> assignerUserList;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<String> getAssignerUserList() {
        return assignerUserList;
    }

    public void setAssignerUserList(List<String> assignerUserList) {
        this.assignerUserList = assignerUserList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
