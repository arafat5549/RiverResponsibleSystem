package com.jqm.ssm.util;

import com.jqm.ssm.dao.DeviceinfoLogDao;
import com.jqm.ssm.dao.SystemLogDao;
import com.jqm.ssm.entity.Deviceinfo;
import com.jqm.ssm.entity.DeviceinfoLog;
import com.jqm.ssm.entity.SystemLog;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.enums.DictionayEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 日记工具类
 * 用于调用日记
 */
@Component
public class LogUtil {

    @Autowired
    private DictionaryUtil dictionaryUtil;
    @Autowired
    private SystemLogDao systemLogDao;
    @Autowired
    private DeviceinfoLogDao deviceinfoLogDao;

    private void setSystemLogInsert(SystemLog syslog, User operator){
        Date d = new Date();
        syslog.setCreatePerson(operator.getUsername());
        //syslog.setUpdatePerson(operator.getUsername());
        syslog.setCreateDate(d);
        //syslog.setUpdateDate(d);
    }

    private void setDeviceinfoLogInsert(DeviceinfoLog dlog, User operator){
        Date d = new Date();
        dlog.setCreatePerson(operator.getUsername());
        //dlog.setUpdatePerson(operator.getUsername());
        dlog.setCreateDate(d);
        //dlog.setUpdateDate(d);
    }

    /**
     * 插入系统日记
     * @param log
     * @param operator
     * @param dictenum
     */
    public void logSys(String log ,User operator, DictionayEnum dictenum){

        if(dictenum == DictionayEnum.DICT_TYPE_SYSTEM_LOG){
            SystemLog sysLog = new SystemLog();
            sysLog.setRemark(log);
            setSystemLogInsert(sysLog,operator);
            sysLog.setLogTypeId((byte)dictenum.getCode());
            systemLogDao.insertSelective(sysLog);
        }
    }

    /**
     * 插入设备日记
     * @param log
     * @param operator
     * @param dictenum
     * @param device
     */
    public void logDevice(String log , User operator, DictionayEnum dictenum, Deviceinfo device){
        if(dictenum == DictionayEnum.DICT_TYPE_DEVICE_LOG){
            DeviceinfoLog dlog = new DeviceinfoLog();
            dlog.setRemark(log);
            dlog.setDeviceinfoId(device.getId());
            dlog.setLogTypeId((byte)dictenum.getCode());
            setDeviceinfoLogInsert(dlog,operator);
            deviceinfoLogDao.insertSelective(dlog);
        }
    }
}
