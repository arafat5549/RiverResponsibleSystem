package com.jqmkj.WaterResponseSystem.basemapper.mymapper;

import com.jqmkj.WaterResponseSystem.pojo.PageTree;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface JoinMapper<T> {//未深入测试

    /**
     * 双表左关联通用
     * @param <T>  左表
     * @param <E>  右表，外键所在表，如Department leftJoin User,User中含department_id,on Department.id = User.pepartmnent_id
     *           要求属性注解有@Column 或 @JoinColumn
     * @return
     */
    @SelectProvider(type = JoinProvider.class,method = "left2JoinNotCondition")
    <T,E> List<PageTree> leftJoin(Class<T> t, Class<E> e);

    @SelectProvider(type = JoinProvider.class,method = "left2JoinWithBeanCondition")
    <T,E> List<PageTree> leftJoin(Class<T> t,Class<E> e, T record1, E record2);

    @SelectProvider(type = JoinProvider.class,method = "left2JoinWithSqlCondition")
    <T,E> List<PageTree> leftJoin(Class<T> t,Class<E> e,String sql);

    @SelectProvider(type = JoinProvider.class,method = "left2JoinWithBeanAndSqlCondition")
    <T,E> List<PageTree> leftJoin(Class<T> t,Class<E> e, T record1, E record2,String sql);

    /**
     * 三张表左关联查询通用
     * @param <T>
     * @param <E>
     * @param <K>
     * @return
     */
    @SelectProvider(type = JoinProvider.class,method = "left3JoinNotCondition")
    <T,E,K> List<PageTree> leftJoin(Class<T> t,Class<E> e,Class<K> k);

    @SelectProvider(type = JoinProvider.class,method = "left3JoinWithBeanCondition")
    <T,E,K> List<PageTree> leftJoin(Class<T> t,Class<E> e,Class<K> k, T record1, E record2, K record3);

    @SelectProvider(type = JoinProvider.class,method = "left3JoinWithSqlCondition")
    <T,E,K> List<PageTree> leftJoin(Class<T> t,Class<E> e,Class<K> k,String sql);

    @SelectProvider(type = JoinProvider.class,method = "left3JoinWithBeanAndSqlCondition")
    <T,E,K> List<PageTree> leftJoin(Class<T> t,Class<E> e,Class<K> k, T record1, E record2, K record3,String sql);



}
