package com.adc.da.activiti.service;


import com.adc.da.activiti.controller.StandardAdjustProcessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("StandardAdjustProcessService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class StandardAdjustProcessService {

    private static final Logger logger = LoggerFactory.getLogger(StandardAdjustProcessService.class);
}
