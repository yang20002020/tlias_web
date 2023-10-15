package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize,String name, Short gender,LocalDate begin,LocalDate end);

    /**
     *  分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    void save(Emp emp);


    void update(Emp emp);

    /**
     * 员工登录
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
