package com.jqm.ssm.entity.relate;

import com.jqm.ssm.misc.Constants;

import java.util.Date;

public class BaseSearchModel {


    // private Integer pageNo = 1;
    private Integer iDisplayLength = Constants.DEFALUT_LIMIT;
    private Integer iDisplayStart = 0;
    /* Integer iDisplayStart, Integer iDisplayLength*/

    public BaseSearchModel(){}

    public BaseSearchModel(Integer iDisplayStart, Integer iDisplayLength){
        this.iDisplayLength = iDisplayLength;
        this.iDisplayStart = iDisplayStart;//(this.pageNo-1)*this.iDisplayLength;
        // this.pageNo = this.iDisplayStart/this.iDisplayLength+1;

    }

    public Integer getIDisplayStart() {
        return iDisplayStart == null ? 0 : iDisplayStart;
    }
    public void setIDisplayStart(Integer iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }
    public Integer getPageNo() {
        return this.iDisplayStart/this.iDisplayLength+1;
    }
    /* public void setPageNo(Integer pageNo) {
         this.pageNo = pageNo;
     }*/
    public Integer getIDisplayLength() {
        return iDisplayLength == null ? 10 :  iDisplayLength;
    }
    public void setIDisplayLength(Integer iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    /**
     * keyword: 模糊查询
     */
    private String keyWord;

    private String updatePerson;

    private String updateDateMin;

    private String updateDateMax;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String getUpdateDateMin() {
        return updateDateMin;
    }

    public void setUpdateDateMin(String updateDateMin) {
        this.updateDateMin = updateDateMin;
    }

    public String getUpdateDateMax() {
        return updateDateMax;
    }

    public void setUpdateDateMax(String updateDateMax) {
        this.updateDateMax = updateDateMax;
    }
}
