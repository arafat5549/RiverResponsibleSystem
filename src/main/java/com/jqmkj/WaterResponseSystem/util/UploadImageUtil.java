package com.jqmkj.WaterResponseSystem.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UploadImageUtil {

    //private static final String local = "D:\\photo";


    /**
     * 上传文件
     * @param file	上传的文件
     * @param uploadPath	上传路径相对路径
     * @param uploadPath	原图实际路径
     * @return
     */
    public static String uploadImage(MultipartFile file, String uploadPath, String fileName){
        InputStream is = null;
        OutputStream os = null;
        try {
            is = file.getInputStream();
            String des = uploadPath + fileName;
            os = new FileOutputStream(des) ;
            byte[] buffer = new byte[1024];
            int len = 0;

            while((len = is.read(buffer))>0){
                os.write(buffer, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return fileName;
    }

    //缩略图
    public static String thumbnailUploadImage(MultipartFile file, int width, int height, String uploadPath, String fileName){
        String des = uploadPath +fileName;//缩略图实际存储路径
        try {
            Thumbnails.of(file.getInputStream()).size(width, height).toFile(des);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadPath + fileName;
    }

    public static List<String> upload(String local,HttpServletRequest request) {
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
        String upaloadUrl =local;//+"/upload/photo/";//得到当前工程路径拼接上文件名
        // String upaloadUrl ="http://127.0.0.1:8000/upload/photo/";//得到当前工程路径拼接上文件名
        String upaloadUrlSmall =upaloadUrl+"small/";//得到当前工程路径拼接上文件名
        File dir = new File(upaloadUrlSmall);
        if(!dir.exists())//目录不存在则创建
            dir.mkdirs();
        List<String> paths = new ArrayList<>();
        for(MultipartFile file :files.values()){
            String pic_type = file.getContentType();
            // Thread.sleep(1);
            String fileName = System.currentTimeMillis()+"";
            if(pic_type.equals("image/jpeg")){
                fileName =   fileName.concat(".jpeg");
            } else if (pic_type.equals("image/png")){
                fileName = fileName.concat(".png");
            } else if(pic_type.equals("image/bmp")){
                fileName =  fileName.concat(".bmp");
            } else if(pic_type.equals("image/gif")){
                fileName = fileName.concat(".gif");
            } else fileName = fileName.concat(".jpg");
            paths.add(uploadImage(file,upaloadUrl,fileName));
            thumbnailUploadImage(file,32,40,upaloadUrlSmall,fileName);
        }
        return paths;
    }

}

