package com.jqmkj.WaterResponseSystem.util;

import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TkExampleUtil2<T> {

    /**TkExampleUtil2 较 TkExampleUtil做了改进
     * 封装表单查询条件，供通用mapper里的Xxample查询
     * 默认查询条件是表单元素name值与实体类属性名一样按eaqals计算
     * 若加上自定义注解@FormSearchCondition 在实体类属性
     *
     * @param request
     * @param t
     * @param <T>
     * @return
     * @FormSearchCondition:表单元素name值须与注解里的属性值一样 name:按eaqals计算
     * like:模糊查询
     * beginName:最小值
     * endName:最大值
     */
    public static <T> Example setExample(HttpServletRequest request, Class<T> t) {
        Map<String, Object> map = FormUtil.getFormForMap(request);
        Example example = new Example(t);
        Example.Criteria criteria = example.createCriteria();
        Example.Criteria criteria2 = example.createCriteria();
        Field[] fields = t.getDeclaredFields();
        Field[] superFields = t.getSuperclass().getDeclaredFields();
        List<Field> likeFieldName = new ArrayList<>();
        String likeValue = "";
        boolean flag = true;
        for (Field field : fields) {    //遍历自身属性
            boolean isCondition = field.isAnnotationPresent(FormSearchCondition.class);
            if (isCondition) {//有注解下
                FormSearchCondition annontation = field.getAnnotation(FormSearchCondition.class);
                if (!"".equals(annontation.name())) {
                    Object value = map.get(annontation.name());
                    if (!StringUtils.isEmpty(value)) {
                        criteria.andEqualTo(field.getName(), value);
                    }
                } else if (!"".equals(annontation.like())) {
                    Object value = map.get(annontation.like());
                    if (!StringUtils.isEmpty(value)) {
                        if(flag){
                            likeValue="%" + value + "%";
                            flag = !flag;
                        }
                        likeFieldName.add(field);
                    }
                } else {
                    if (!"".equals(annontation.beginName())) {
                        Object value = map.get(annontation.beginName());
                        if (!StringUtils.isEmpty(value)) {
                            criteria.andGreaterThanOrEqualTo(field.getName(), value);
                        }
                    }
                    if (!"".equals(annontation.endName())) {
                        Object value = map.get(annontation.endName());
                        if (!StringUtils.isEmpty(value)) {
                            criteria.andLessThanOrEqualTo(field.getName(), value);
                        }
                    }
                }
            } else {//无注解下
                Object value = map.get(field.getName());
                if (!StringUtils.isEmpty(value)) {
                    criteria.andEqualTo(field.getName(), value);
                }
            }

        }
        for (Field field : superFields) {
            boolean isCondition = field.isAnnotationPresent(FormSearchCondition.class);
            if (isCondition) {//有注解下
                FormSearchCondition annontation = field.getAnnotation(FormSearchCondition.class);
                if (!"".equals(annontation.name())) {
                    Object value = map.get(annontation.name());
                    if (!StringUtils.isEmpty(value)) {
                        criteria.andEqualTo(field.getName(), value);
                    }
                } else if (!"".equals(annontation.like())) {
                    Object value = map.get(annontation.like());
                    if (!StringUtils.isEmpty(value)) {
                        likeFieldName.add(field);
                    }
                } else {
                    if (!"".equals(annontation.beginName())) {
                        Object value = map.get(annontation.beginName());
                        if (!StringUtils.isEmpty(value)) {
                            criteria.andGreaterThanOrEqualTo(field.getName(), value);
                        }
                    }
                    if (!"".equals(annontation.endName())) {
                        Object value = map.get(annontation.endName());
                        if (!StringUtils.isEmpty(value)) {
                            criteria.andLessThanOrEqualTo(field.getName(), value);
                        }
                    }
                }
            } else {//无注解下
                Object value = map.get(field.getName());
                if (!StringUtils.isEmpty(value)) {
                    criteria.andEqualTo(field.getName(), value);
                }
            }

        }
        if (likeFieldName.size() > 0) {
            for (int i = 0; i < likeFieldName.size(); i++) {
                if(i==0) {
                    criteria2.andLike(likeFieldName.get(i).getName(), likeValue);
                }else {
                    criteria2.orLike(likeFieldName.get(i).getName(), likeValue);
                }
            }
        }
        example.and(criteria2);
        return example;
    }


}
