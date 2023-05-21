package com.sx.service.impl;

import com.sx.mapper.EmpMapper;
import com.sx.pojo.DeptLog;
import com.sx.service.DeptLogService;
import com.sx.service.DeptService;
import com.sx.mapper.DeptMapper;
import com.sx.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list(){
        return deptMapper.list();
    }

//    @Transactional(rollbackFor = Exception.class)
    @Transactional
    @Override
    public void delete(Integer id){
        try {
            deptMapper.deleteById(id);//删除部门
            int i=1/0;
            empMapper.deleteByDeptId(id);//根据部门id删除该部门下员工
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是"+id+"号部门");
            //调用其他业务类中的方法
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept){
        deptMapper.update(dept);
    }


}
