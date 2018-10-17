package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.*;
import com.jqmkj.WaterResponseSystem.pojo.EasyUIResult;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.QueryInfo;
import com.jqmkj.WaterResponseSystem.service.BrandService;
import com.jqmkj.WaterResponseSystem.service.CategoryService;
import com.jqmkj.WaterResponseSystem.service.DeviceinfoPictureService;
import com.jqmkj.WaterResponseSystem.service.DeviceinfoService;
import com.jqmkj.WaterResponseSystem.util.*;
import com.jqmkj.WaterResponseSystem.entity.*;
import com.jqmkj.WaterResponseSystem.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceinfoController {

    @Value("${file.localPath}")
    private  String localPath;

    @Value("${file.loadPath}")
    private  String loadPath;

    @Autowired
    private DeviceinfoService deviceinfoService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private DeviceinfoPictureService deviceinfoPictureService;

    @PostMapping("/deviceinfos")
    public MessageInfo add(Deviceinfo deviceinfo) {
        MessageInfo result = MessageInfo.getInstance();
        try {
            deviceinfoService.insertSelective(deviceinfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("设备添加异常");
        }
        return result;
    }

    @PutMapping(value = "/deviceinfos")
    public MessageInfo update(Deviceinfo deviceinfo) {
        MessageInfo result = MessageInfo.getInstance();
        try {
            deviceinfoService.updateByPrimaryKeySelective(deviceinfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("设备更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/deviceinfos")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator = ShiroUtil.getCurrentSysUser();
        try {
            //此处log记录
            deviceinfoService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            result.setResult(false);
            result.setMsg("设备删除异常");
        }
        return result;
    }

    @GetMapping("/deviceinfos")
    public EasyUIResult queryDeviceinfoList(QueryInfo queryInfo, HttpServletRequest request) {
        List<Category> categories = categoryService.selectAll();
        List<Brand> brands = brandService.selectAll();
        EasyUIResult results = deviceinfoService.queryListPage(queryInfo, TkExampleUtil2.setExample(request, Deviceinfo.class));
        List<Deviceinfo> deviceinfos = (List<Deviceinfo>) results.getRows();
        deviceinfos.forEach(d -> {
            categories.forEach(c -> {
                if (c.getId().equals(d.getId())) d.setCategoryName(c.getName());
            });
            brands.forEach(b -> {
                if (b.getId().equals(d.getId())) d.setBrandName(b.getName());
            });
            Example example = new Example(DeviceinfoPicture.class);
            // example.clear();
            example.createCriteria().andEqualTo("deviceinfoId",d.getId());
            List<DeviceinfoPicture> deviceinfoPictures = deviceinfoPictureService.selectByExample(example);
            if(deviceinfoPictures.size()>0) {
                d.setDeviceinfoPictureName(deviceinfoPictures.get(0).getName());
            }
        });
        results.setRows(deviceinfos);
        return results;
    }

    @GetMapping("/deviceinfos/export")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = categoryService.selectAll();
        List<Brand> brands = brandService.selectAll();
        List<Deviceinfo> deviceinfos = deviceinfoService.selectByExample(TkExampleUtil2.setExample(request, Deviceinfo.class));
        deviceinfos.forEach(d -> {
            brands.forEach(b -> {
                if (b.getId().equals(d.getId())) d.setBrandName(b.getName());
            });
            categories.forEach(c -> {
                if (c.getId().equals(d.getId())) d.setCategoryName(c.getName());
            });
        });
        ExcelUtils.getListToExcel(deviceinfos, "sheet", "设备明细表", request, response, Deviceinfo.class);
    }

    @GetMapping("/deviceinfos/findAll")
    public List<Deviceinfo> queryDeviceinfoList() {
        return deviceinfoService.selectAll();
    }


    @GetMapping("/deviceinfos/getPhotoCountById")
    public Integer getPhotoCountById(Long id){
        Example example = new Example(DeviceinfoPicture.class);
        example.createCriteria().andEqualTo("deviceinfoId",id);
        int count = deviceinfoPictureService.selectCountByExample(example);
        return 3-count;
    }

    @PostMapping("/deviceinfos/photoUpload")
    public MessageInfo photoUpload(DeviceinfoPicture deviceinfoPicture, HttpServletRequest request) {
        MessageInfo result = MessageInfo.getInstance();
        try {
            List<String> urls = UploadImageUtil.upload(localPath,request);
            for (String url : urls) {
                deviceinfoPicture.setName(url);
                deviceinfoPictureService.insertSelective(deviceinfoPicture);
            }
        } catch (Exception e) {
            result.setResult(false);
            result.setMsg("设备删除异常");
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/deviceinfos/getPhotoById")
    public List<DeviceinfoPicture> photos(Long id){
        Example example = new Example(DeviceinfoPicture.class);
        example.createCriteria().andEqualTo("deviceinfoId",id);
        return deviceinfoPictureService.selectByExample(example);
    }

    @DeleteMapping("/deviceinfos/{id}/photos")
    public MessageInfo deletePhoto(@PathVariable Long id,@RequestParam("src") String src,HttpServletRequest request){
        MessageInfo result = MessageInfo.getInstance();
        try {
            deviceinfoPictureService.deleteByPrimaryKey(id);
            DeleteFileUtil.deleteFile(localPath+src);
            DeleteFileUtil.deleteFile(localPath+"/small/"+src);
        } catch (Exception e) {
            result.setMsg("删除失败");
            result.setResult(false);
        }
        return result;
    }


}
