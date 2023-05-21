package com.sx.service;

import com.sx.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void update(Dept dept);
}
