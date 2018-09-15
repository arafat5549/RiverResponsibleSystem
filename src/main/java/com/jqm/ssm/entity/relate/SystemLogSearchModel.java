package com.jqm.ssm.entity.relate;

import com.jqm.ssm.misc.Constants;

import java.io.Serializable;

public class SystemLogSearchModel extends BaseSearchModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String remark;

    private Byte logTypeId;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(Byte logTypeId) {
        this.logTypeId = logTypeId;
    }

    /**
     * 由于SystemLog中只有createPerson,cerateDate,没有updatePerson,updateDate,
     * 所以createPerson,cerateDate 也是 updatePerson,updateDate,
     */
}
