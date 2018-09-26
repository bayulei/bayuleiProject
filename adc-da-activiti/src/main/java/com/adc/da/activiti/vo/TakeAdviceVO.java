package com.adc.da.activiti.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

public class TakeAdviceVO extends ApprovalProcessVO {
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String standardFiles;

    private List<String> interfacePersonList = new ArrayList<>();

    private List<String> engineerIdList = new ArrayList<>();

    private List<String> copyPersonIdList = new ArrayList<>();

    private String flag;

    private String comment;

    private String taskId;

    private List<TakeAdviceEngineersOpinionVO> analyList = new ArrayList<>();

    private List<TakeAdviceEngineersOpinionVO> suggestList = new ArrayList<>();

    Map<String, List<TakeAdviceEngineersOpinionVO>> analyMap = new HashMap<>();

    Map<String, List<TakeAdviceEngineersOpinionVO>> suggestMap = new HashMap<>();


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<String, List<TakeAdviceEngineersOpinionVO>> getAnalyMap() {
        return analyMap;
    }

    public void setAnalyMap(Map<String, List<TakeAdviceEngineersOpinionVO>> analyMap) {
        this.analyMap = analyMap;
    }

    public Map<String, List<TakeAdviceEngineersOpinionVO>> getSuggestMap() {
        return suggestMap;
    }

    public void setSuggestMap(Map<String, List<TakeAdviceEngineersOpinionVO>> suggestMap) {
        this.suggestMap = suggestMap;
    }

    public List<TakeAdviceEngineersOpinionVO> getAnalyList() {
        return analyList;
    }

    public void setAnalyList(List<TakeAdviceEngineersOpinionVO> analyList) {
        this.analyList = analyList;
    }

    public List<TakeAdviceEngineersOpinionVO> getSuggestList() {
        return suggestList;
    }

    public void setSuggestList(List<TakeAdviceEngineersOpinionVO> suggestList) {
        this.suggestList = suggestList;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStandardFiles() {
        return standardFiles;
    }

    public void setStandardFiles(String standardFiles) {
        this.standardFiles = standardFiles;
    }

    public List<String> getInterfacePersonList() {
        return interfacePersonList;
    }

    public void setInterfacePersonList(List<String> interfacePersonList) {
        this.interfacePersonList = interfacePersonList;
    }

    public List<String> getCopyPersonIdList() {
        return copyPersonIdList;
    }

    public void setCopyPersonIdList(List<String> copyPersonIdList) {
        this.copyPersonIdList = copyPersonIdList;
    }

    public List<String> getEngineerIdList() {
        return engineerIdList;
    }

    public void setEngineerIdList(List<String> engineerIdList) {
        this.engineerIdList = engineerIdList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
