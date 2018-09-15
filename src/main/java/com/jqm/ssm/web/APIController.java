package com.jqm.ssm.web;

import com.google.common.collect.Lists;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.dao.DevicegpsDao;
import com.jqm.ssm.dto.BaseGpsResult;
import com.jqm.ssm.dto.GpsFeature;
import com.jqm.ssm.entity.Devicegps;
import com.jqm.ssm.enums.CategoryEnum;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IDevicegpsService;
import com.jqm.ssm.service.IUserService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangyaoyao on 2018/8/31.
 *
 * 用于给地图传输JSON数据
 * 地图数据需要特定DTO对象 # BaseGpsResult
 */
@Controller
@RequestMapping("/api")
public class APIController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IUserService userService;
    @Autowired
    SystemService systemService;
    @Autowired
    RedisCache redisCache;
    @Autowired
    IDevicegpsService devicegpsService;
    @Autowired
    DevicegpsDao devicegpsDao;

    /**
     * 返回地图openlayer所需要的数据
     * 设备信息
     * @param offset
     * @param limit
     * @param response
     * @return
     */
    @RequestMapping(value = "/device", method = RequestMethod.GET)
    @ResponseBody
    public String device(Integer offset, Integer limit, HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);

        List<Devicegps> list =  devicegpsDao.getDeviceinfoListByCid(CategoryEnum.CATEGORY_WATERSCAN.getState());
                //devicegpsService.selectListByMap(null);
        List<GpsFeature> glist = Lists.newArrayList();
        for (Devicegps devicegps:list){
            GpsFeature<Devicegps> g = new GpsFeature<Devicegps>(devicegps);
            glist.add(g);
        }
        return JsonUtil.objectToJson(new BaseGpsResult(glist));
    }

    /**
     * 返回地图openlayer所需要的数据
     * @param offset
     * @param limit
     * @param response
     * @return
     */
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    @ResponseBody
    public String monitor(Integer offset, Integer limit, HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);

        List<Devicegps> list =  devicegpsDao.getDeviceinfoListByCid(CategoryEnum.CATEGORY_MONITOR.getState());
                //selectListByMap(null);
        List<GpsFeature> glist = Lists.newArrayList();
        for (Devicegps devicegps:list){
            GpsFeature<Devicegps> g = new GpsFeature<Devicegps>(devicegps);
            glist.add(g);
        }
        return JsonUtil.objectToJson(new BaseGpsResult(glist));
    }
}
