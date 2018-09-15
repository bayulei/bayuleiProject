package com.adc.da.activiti.vo;

import java.io.Serializable;
import java.util.List;

public class CorrigendaApprovalVO extends ApprovalProcessVO implements Serializable {


    private static final long serialVersionUID = 3362990175004982375L;

    /**
     *  附件
     */
    private List<String> fileIdList;

    private List<CorrigendaApprovalListVO> corrigendaApprovalList;

    public List<String> getFileIdList() {
        return fileIdList;
    }

    public void setFileIdList(List<String> fileIdList) {
        this.fileIdList = fileIdList;
    }

    public List<CorrigendaApprovalListVO> getCorrigendaApprovalList() {
        return corrigendaApprovalList;
    }

    public void setCorrigendaApprovalList(List<CorrigendaApprovalListVO> corrigendaApprovalList) {
        this.corrigendaApprovalList = corrigendaApprovalList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
