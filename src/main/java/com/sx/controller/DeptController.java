package com.sx.controller;

import com.sx.pojo.Result;
import com.sx.service.DeptService;
import com.sx.anno.Log;
import com.sx.pojo.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList= deptService.list();
        return Result.success(deptList);
    }
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门"+id);
        deptService.delete(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
