package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.SysCorpEO;

import java.util.LinkedList;
import java.util.List;
/**
 *
 * <br>
 * <b>功能：</b>SYS_CORP SysCorpEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-05-05 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SysCorpEODao extends BaseDao<SysCorpEO> {

    SysCorpEO  selectByCorpCode(String corpCode);

    Integer batchInsertCorp(List<SysCorpEO> sysCorpEOS);

	void addCorpeos(LinkedList<SysCorpEO> sCorpEOs);

}
