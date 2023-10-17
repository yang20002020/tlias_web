package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
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

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //rollbackFor 控制 回滚事务 异常类型：所有的异常Exception.class
    @Transactional(rollbackFor =Exception.class)  //spring事务管理
    @Override
    public void delete(Integer id) throws Exception {
        deptMapper.deleteById(id); //根据部门id删除部门数据

        if(true){
            throw new Exception("出错了.....");
        }
        empMapper.deleteByDeptId(id);//根据部门id删除部门下的员工
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }
}
