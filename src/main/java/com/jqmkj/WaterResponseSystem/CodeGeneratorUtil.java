package com.jqmkj.WaterResponseSystem;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.MonitofactorsMapper;
import com.jqmkj.WaterResponseSystem.entity.*;
import com.jqmkj.WaterResponseSystem.entity.other.Option;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.util.StringUtil;

import javax.persistence.Transient;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CodeGeneratorUtil {
    @Autowired
    private MonitofactorsMapper monitofactorsMapper;

    private static final String CHARSET = "UTF-8";


    public void createEntity(){
        List<Monitofactors> factors = monitofactorsMapper.selectAll();
        List<String> lists = Lists.newArrayList("avg","min","max","cou","flag");

        String tableName = "deviceinfo_day_data";
        String entityName  = StringUtil.underlineToCamelhump(tableName);
        entityName = StringUtils.capitalize(entityName);
        System.out.println("entityName="+entityName);
        Map<String, Object> input = Maps.newHashMap();
        input.put("tableName",tableName);
        input.put("entityName",entityName);

        List<Option> datas = Lists.newArrayList();
        for (Monitofactors f:factors){
            for(String suffix:lists){
                Option edata = new Option();
                String avg = f.getSno()+"_"+suffix;
                String avgEntity = f.getSno()+StringUtils.capitalize(suffix);
                edata.setName(avg);
                edata.setCode(avgEntity);
                datas.add(edata);
            }

        }
        input.put("datas",datas);

        entity("/Users/arafat/workspace/IdeaProjects/WaterResponseSystem-new",input);
    }

    private  void entity(String workDir, Map<String, Object> input) {
        String entityName = input.get("entityName").toString();

        String packageName = "com/jqmkj/WaterResponseSystem/entity";//input.get("packageName").toString().replaceAll("\\.", "/");
        StringBuffer sb = new StringBuffer();
        sb.append(workDir).append("/src/main/java/")
                .append(packageName+"/")
                .append(entityName+".java");

        //StringBuffer sb = new StringBuffer();
        //sb.append(workDir+"/"+entityName+".java");
        String fileName = sb.toString();
        File myFile = new File(fileName);
        myFile.getParentFile().mkdirs();
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileName);
        buildFile("templates/deviceinfodata.ftl", fileName, input,false);
    }

    /**
     * -------------------生成模板---------------------

    private static void createTemplate(String myBasePackage,String packageName,String daoPackageName,String modelPackageName,String clsName) {
        Map<String, Object> root = new HashMap<String, Object>();
        //子文件的包名
        root.put("packageName", packageName);
        root.put("modelPackageName", modelPackageName);
        root.put("daoPackageName", daoPackageName);
        //实体类名称
        root.put("className", clsName);// 类名称
        //实体类名称首字母小写，驼峰式
        root.put("smallClassName", MybatisGenerator.lowerCapital(clsName));// 类名称的首字母小写
        root.put("myBasePackage",myBasePackage);

        String workDir = (String) System.getProperties().get("user.dir");
        System.out.println(workDir+" , "+modelPackageName+"."+clsName);
        try {
            Class cls = Class.forName(modelPackageName+"."+clsName);
            Field field = cls.getDeclaredField("id");
            //System.out.println(field);
            if(field!=null){
                String idField = field.getType().getName();
                int idx = idField.lastIndexOf(".");
                idField = idField.substring(idx+1);
                root.put("idField", idField);
            }
            //service(workDir, root);
            //serviceImpl(workDir, root);

            //daoTest(workDir, root, "daoPackageName");
        } catch (NoSuchFieldException | SecurityException
                | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("没有主键id无法生成:["+clsName+"]");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{

        }
//		catch (Exception e) {
//			System.out.println("没有主键id无法生成:["+clsName+"]");
//		}
    }
     */
    /**
     * 生成文件
     *
     * @param templateName 模板文件
     * @param fileName 生成文件
     * @param root 参数
     */
    @SuppressWarnings("deprecation")
    private void buildFile(String templateName, String fileName, Map root,boolean force) {

        Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_0);

        freemarkerCfg.setClassForTemplateLoading(CodeGeneratorUtil.class, "/");
        freemarkerCfg.setEncoding(Locale.getDefault(), CHARSET);
        Template template;
        try {
            template = freemarkerCfg.getTemplate(templateName);
            template.setEncoding(CHARSET);
            File htmlFile = new File(fileName);
            boolean exists = htmlFile.exists();
            if(exists && !force){
                System.out.println(fileName+"已存在，不强制覆盖");
                return;
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), CHARSET));
            template.process(root, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //###############################################################################################################//
    //                                            生成模版包含Mapper,Service
    //###############################################################################################################//
    private String getOutputPath(String type,String entityName){
        StringBuffer sb = new StringBuffer();
        if("mapperXml".equals(type))
        {
            sb.append("src/main/resources/mapper/");
            if(!Strings.isNullOrEmpty(entityName)){
                sb.append(entityName+"Mapper.xml");
            }
        }
        else if("mapper".equals(type)){
            sb.append("src/main/java/com/jqmkj/WaterResponseSystem/basemapper/mapper/");
            if(!Strings.isNullOrEmpty(entityName)){
                sb.append(entityName+"Mapper.java");
            }
        }
        else if("service".equals(type)){
            sb.append("src/main/java/com/jqmkj/WaterResponseSystem/service/");
            if(!Strings.isNullOrEmpty(entityName)){
                sb.append(entityName+"Service.java");
            }
        }
        else if("serviceImpl".equals(type)){
            sb.append("src/main/java/com/jqmkj/WaterResponseSystem/service/impl/");
            if(!Strings.isNullOrEmpty(entityName)){
                sb.append(entityName+"ServiceImpl.java");
            }
        }
        return sb.toString();
    }
    public <T> void createTemplate_mapper(String workDir,Class<T> clz,boolean force){

        Map<String, Object>  root = Maps.newHashMap();
        String baseField = "";createSql_entitity(clz);
        root.put("baseField",baseField);
        String entityName  = StringUtil.underlineToCamelhump(clz.getSimpleName());
        entityName = StringUtils.capitalize(entityName);
        root.put("entityName",entityName);
        //
        String mapperFtl         = "templates/mapper.ftl";
        String mapperXmlFtl      = "templates/mapperXml.ftl";
        String serviceFtl        = "templates/service.ftl";
        String serviceImplFtl    = "templates/serviceImpl.ftl";

        String outPathMapperXml     = workDir+getOutputPath("mapperXml",entityName);
        String outPathMapper        = workDir+getOutputPath("mapper",entityName);
        String outPathService    = workDir+getOutputPath("service",entityName);
        String outPathServiceImpl = workDir+getOutputPath("serviceImpl",entityName);
        //
        //System.out.println(outPathMapperXml);
        //System.out.println(outPathMapper);
        //boolean force = true;
        buildFile(mapperFtl, outPathMapper, root,force);
        buildFile(mapperXmlFtl, outPathMapperXml, root,force);
        buildFile(serviceFtl, outPathService, root,force);
        buildFile(serviceImplFtl, outPathServiceImpl, root,force);

//        try {
//            //buildFile(serviceImplFtl, outPathServiceImpl, root,true);
//            String content = "";
//            File destMapperXml = new File(outPathServiceImpl);
//            List<String> lines = Files.readLines(destMapperXml, Charset.defaultCharset());
//            lines.add(lines.size()-1,content);
//            String s = Joiner.on("\t\n").join(lines);
//            Files.write(s.getBytes(),destMapperXml);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    //###############################################################################################################//
    //                                            生成模版string
    //###############################################################################################################//
    public <T> String  createSql_entitity(Class<T> clz) {
        StringBuffer sb = new StringBuffer();
        String prefix = "dd.";
        try {
            sb.append("<sql id=\"baseField\">\t\n");
            Field[] fs0 =clz.getSuperclass().getDeclaredFields();
            for(Field f:fs0){
                Annotation at = f.getAnnotation(Transient.class);
                if(at==null)
                    sb.append(prefix+StringUtil.camelhumpToUnderline(f.getName())+",\t\n");
            }
            Field[] fs = clz.getDeclaredFields();
            for(Field f:fs){
                Annotation at = f.getAnnotation(Transient.class);
                if(at==null)
                    sb.append(prefix+StringUtil.camelhumpToUnderline(f.getName())+",\t\n");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("</sql>");
            //System.out.println(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //生成EnumColumn
    public <T> void  createClolumn_entitity(Class<T> clz) {
        StringBuffer sb = new StringBuffer();
        //String prefix = "d.";
        try {
            sb.append("public enum EnumColumn {\t\n");
            Field[] fs0 =clz.getSuperclass().getDeclaredFields();
            for(Field f:fs0){
                String name  =f.getName();
                String undelinename = StringUtil.camelhumpToUnderline(name);
                Annotation at = f.getAnnotation(Transient.class);
                //System.out.println(name+","+at);
                if(at==null)
                    sb.append(name+"(\""+undelinename+"\")"+",\t\n");
            }
            Field[] fs = clz.getDeclaredFields();
            for(Field f:fs){
                String name  =f.getName();
                String undelinename = StringUtil.camelhumpToUnderline(name);
                Annotation at = f.getAnnotation(Transient.class);

                if(at==null)
                    sb.append(name+"(\""+undelinename+"\")"+",\t\n");
                //sb.append(prefix+StringUtil.camelhumpToUnderline(f.getName())+",\t\n");

            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(";\t\n");
            sb.append("private final String column;\t\n");
            sb.append("public String value() {return this.column;}\t\n");
            sb.append("public String getValue() {return this.column;}\t\n");
            sb.append("EnumColumn(String column) {this.column = column;}\t\n");
            sb.append("}");
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        CodeGeneratorUtil codeutil =new CodeGeneratorUtil();
        //codeutil.createSql_entitity(Devicegps.class);
        //codeutil.createClolumn_entitity(Devicegps.class);
        //System.out.println(Region.EnumColumn.parentId.value());

        String workDir  = "/Users/arafat/workspace/IdeaProjects/WaterResponseSystem-new/";
        boolean force = false;
        codeutil.createTemplate_mapper(workDir,DeviceInfoRealTimeData.class,force);

    }
}
