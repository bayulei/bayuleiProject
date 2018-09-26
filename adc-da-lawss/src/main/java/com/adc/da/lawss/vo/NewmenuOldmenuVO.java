package com.adc.da.lawss.vo;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.lawss.entity.MsgFileEO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/9/13 19:29
 */
public class NewmenuOldmenuVO extends BaseEntity {

    private String oldMenuid;
    private String newMenuid;

    public String getOldMenuid() {
        return oldMenuid;
    }

    public void setOldMenuid(String oldMenuid) {
        this.oldMenuid = oldMenuid;
    }

    public String getNewMenuid() {
        return newMenuid;
    }

    public void setNewMenuid(String newMenuid) {
        this.newMenuid = newMenuid;
    }
}
