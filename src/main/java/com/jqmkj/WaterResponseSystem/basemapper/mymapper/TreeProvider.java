package com.jqmkj.WaterResponseSystem.basemapper.mymapper;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class TreeProvider {

    public <T> String getAllTree(Class<T> t){
        StringBuffer sb = new StringBuffer("select ");
        Field[] fields = t.getDeclaredFields();
        Field[] superFields = t.getSuperclass().getDeclaredFields();
        appendFidles(sb, fields);
        int i = 1;
        int length = superFields.length;
        appendSupperFields(sb, superFields, i, length);
        sb.append(" from ");
        sb.append(t.getAnnotation(Table.class).name())
                .append(" where  parent_id = 0 or parent_id is null");
        return sb.toString();
    }


    public <T> String getByParentId(Class<T> t){
        StringBuffer sb = new StringBuffer("select ");
        Field[] fields = t.getDeclaredFields();
        Field[] superFields = t.getSuperclass().getDeclaredFields();
        appendFidles(sb, fields);
        int i = 1;
        int length = superFields.length;
        appendSupperFields(sb, superFields, i, length);
        sb.append(" from ")
                .append(t.getAnnotation(Table.class).name())
                .append(" where parent_id = #{id}");
        return sb.toString();
    }

    /**
     *要求属性注解有@Column 或 @JoinColumn
     */

    public static void appendSupperFields(StringBuffer sb, Field[] superFields, int i, int length) {
        for(Field field : superFields){    //遍历父类
            boolean hasColumnName = field.isAnnotationPresent(Column.class);
            if(hasColumnName){
                String columnName = field.getAnnotation(Column.class).name();
                if(!"".equals(columnName)){
                    sb.append(columnName);
                }
            }
            if(i<length){
                sb.append(",");
            }
            i++;
        }
    }

    public static void appendFidles(StringBuffer sb, Field[] fields) {
        for(Field field : fields){
            boolean hasColumnName = field.isAnnotationPresent(Column.class);
            boolean hasJoinColumnName = field.isAnnotationPresent(JoinColumn.class);
            if(hasColumnName){
                String columnName = field.getAnnotation(Column.class).name();
                if(!"".equals(columnName)){
                    sb.append(columnName).append(",");
                }
            }else if (hasJoinColumnName){
                String columnName = field.getAnnotation(JoinColumn.class).name();
                if(!"".equals(columnName)){
                    sb.append(columnName).append(",");
                }
            }
        }
    }

}
