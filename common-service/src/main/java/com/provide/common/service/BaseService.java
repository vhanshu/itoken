package com.provide.common.service;

import com.github.pagehelper.PageInfo;
import com.provide.commondomain.domain.BaseDomain;

/**
 * @author vhans
 */
public interface BaseService<T extends BaseDomain> {

    /**
     * 通用添加
     * @param t
     * @param createBy
     * @return
     */
    int insert(T t, String createBy);

    /**
     * 通用删除
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 通用更新
     * @param t
     * @param updateBy
     * @return
     */
    int update(T t, String updateBy);

    /**
     * 通用查询单个
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * @param t
     * @return 数目
     */
    int count(T t);

    /**
     * 通用查询并分页
     * @param pageNum 页码
     * @param pageSize 页条目数
     * @param t
     * @return
     */
    PageInfo<T> page(int pageNum, int pageSize, T t);
}
