package com.jqmkj.WaterResponseSystem.util;

import com.jqmkj.WaterResponseSystem.entity.BaseEntity;
import com.jqmkj.WaterResponseSystem.entity.SysDepartment;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.lang.reflect.Field;

public class SqlUtils {

    /**
     * 双表关联
     * 外键id须是：主表表名_id,如 department.id = user.sys_department_id
     * 要求属性注解有@Column 或 @JoinColumn
     * @param t
     * @param e
     * @param <T>
     * @param <E>
     * @return
     */

    public static <T ,E> StringBuffer leftJoin(Class<T> t,Class<E> e){
        StringBuffer sb = new StringBuffer("select ");
        Field[] tFields = t.getDeclaredFields();
        Field[] tSuperFields = t.getSuperclass().getDeclaredFields();
        Field[] eFields = e.getDeclaredFields();
        Field[] eSuperFields = e.getSuperclass().getDeclaredFields();
        appendFidles(sb, tFields,"t");
        appendFidles(sb, tSuperFields,"t");
        appendFidles(sb, eFields,"e");
        int i = 1;
        int length = eSuperFields.length;
        appendSupperFields(sb,eSuperFields,i,length,"e");
        sb.append(" from ")
          .append(t.getAnnotation(Table.class).name())
          .append(" t left join ")
          .append(e.getAnnotation(Table.class).name())
          .append(" e on t.id = e.")
          .append(t.getAnnotation(Table.class).name())
          .append("_id");
        return sb;
    }

    public static <T ,E, K> StringBuffer leftJoin(Class<T> t, Class<K> k,Class<E> e){
        StringBuffer sb = new StringBuffer("select ");
        Field[] tFields = t.getDeclaredFields();
        Field[] tSuperFields = t.getSuperclass().getDeclaredFields();
        Field[] eFields = e.getDeclaredFields();
        Field[] eSuperFields = e.getSuperclass().getDeclaredFields();
        Field[] kFields = k.getDeclaredFields();
        Field[] kSuperFields = k.getSuperclass().getDeclaredFields();
        appendFidles(sb, tFields,"t");
        appendFidles(sb, tSuperFields,"t");
        appendFidles(sb, kFields,"k");
        appendFidles(sb, kSuperFields,"k");
        appendFidles(sb, eFields,"e");
        int i = 1;
        int length = eSuperFields.length;
        appendSupperFields(sb,eSuperFields,i,length,"e");
        sb.append(" from ")
                .append(t.getAnnotation(Table.class).name())
                .append(" t left join ")
                .append(k.getAnnotation(Table.class).name())
                .append(" k on t.id = k.")
                .append(t.getAnnotation(Table.class).name())
                .append("_id left join ")
                .append(e.getAnnotation(Table.class).name())
                .append(" e on k.id = e.")
                .append(k.getAnnotation(Table.class).name())
                .append("_id");
        return sb;
    }

    public static String condition(String sql){
        return sql;
    }

    public static <T extends BaseEntity,E extends BaseEntity> String condition(T record1,E record2){
        StringBuffer sb = new StringBuffer(" where 1=1 ");
        forEachFieldsNotNullSetToCondition((T) record1, sb,"t.");
        forEachFieldsNotNullSetToCondition((E) record2, sb, "e.");
        return sb.toString();
    }

    public static <T extends BaseEntity,E extends BaseEntity,K extends BaseEntity> String condition(T record1,E record2,K record3){
        StringBuffer sb = new StringBuffer(" where 1=1 ");
        forEachFieldsNotNullSetToCondition((T) record1, sb,"t.");
        forEachFieldsNotNullSetToCondition((E) record2, sb,"e.");
        forEachFieldsNotNullSetToCondition((K) record3, sb,"k.");
        return sb.toString();
    }

    public static void main(String[] args) {
        SysUser u = new SysUser();
        u.setId(1111111111l);
        u.setUsername("hahaha");
        u.setPassword("123");
        SysDepartment sysDepartment = new SysDepartment();
        sysDepartment.setUpdatePerson("dadads");
        System.out.println(condition(u,sysDepartment,null));

    }


    private static <T extends BaseEntity> void forEachFieldsNotNullSetToCondition(T record, StringBuffer sb,String prefix) {
        if(record != null) {
            Field[] tFields = record.getClass().getDeclaredFields();
            Field[] tSuperFields = record.getClass().getSuperclass().getDeclaredFields();
            for (Field field : tSuperFields) {
                boolean hasColumnName = field.isAnnotationPresent(Column.class);
                if (hasColumnName) {
                    Object fieldValue = ReflectionUtil.getFieldValue(record, field.getName());
                    if (!StringUtils.isEmpty(fieldValue)) {
                        sb.append(" and ").append(prefix).append(field.getName()).append(" = '").append(fieldValue).append("'");
                    }
                }
            }
            for (Field field : tFields) {
                boolean hasColumnName = field.isAnnotationPresent(Column.class);
                boolean hasJoinColumnName = field.isAnnotationPresent(JoinColumn.class);
                if (hasColumnName || hasJoinColumnName) {
                    Object fieldValue = ReflectionUtil.getFieldValue(record, field.getName());
                    if (!StringUtils.isEmpty(fieldValue)) {
                        sb.append(" and t.").append(field.getName()).append(" = '").append(fieldValue).append("'");
                    }
                }
            }
        }
    }


    public static void appendSupperFields(StringBuffer sb, Field[] superFields, int i, int length,String str) {
        for(Field field : superFields){    //遍历父类
            boolean hasColumnName = field.isAnnotationPresent(Column.class);
            if(hasColumnName){
                String columnName = field.getAnnotation(Column.class).name();
                if(!"".equals(columnName)){
                    sb.append(str).append(".").append(columnName).append(" ").append(str).append(columnName);
                }
            }
            if(i<length){
                sb.append(",");
            }
            i++;
        }
    }

    public static void appendFidles(StringBuffer sb, Field[] fields,String str) {
        for(Field field : fields){
            boolean hasColumnName = field.isAnnotationPresent(Column.class);
            boolean hasJoinColumnName = field.isAnnotationPresent(JoinColumn.class);
            if(hasColumnName){
                String columnName = field.getAnnotation(Column.class).name();
                if(!"".equals(columnName)){
                   /* sb.append(columnName).append(" ").append(str).append(",");*/
                    sb.append(str).append(".").append(columnName).append(" ").append(str).append(columnName).append(",");
                }
            }else if (hasJoinColumnName){
                String columnName = field.getAnnotation(JoinColumn.class).name();
                if(!"".equals(columnName)){
                   /* sb.append(columnName).append(" ").append(str).append(",");*/
                    sb.append(str).append(".").append(columnName).append(" ").append(str).append(columnName).append(",");
                }
            }
        }
    }


}
