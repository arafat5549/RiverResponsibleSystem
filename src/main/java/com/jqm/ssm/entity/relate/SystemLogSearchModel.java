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
     * ����SystemLog��ֻ��createPerson,cerateDate,û��updatePerson,updateDate,
     * ����createPerson,cerateDate Ҳ�� updatePerson,updateDate,
     */
}
