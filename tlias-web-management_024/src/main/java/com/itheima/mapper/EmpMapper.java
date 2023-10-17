package com.itheima.mapper;
import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    /**
     * 查询总记录数
     * @return
     */
   // @Select("select count(*) from emp")
   // public Long count();

    /**
     * 分页查询,获取列表数据
     * @param start
     * @param pageSize
     * @return
     */
   // @Select("select * from emp limit #{start},#{pageSize}")
   // public List<Emp> page(Integer start, Integer pageSize);

    /**
     * 员工信息查询
     * @return 插件可以协助实现分页查询
     */

    @Select("select * from emp")
    public List<Emp> list_1();

    /**
     * 员工信息查询 使用xml映射文件方式 解决动态sql
     * @return
     */

    public List<Emp> list(String name, Short gender,LocalDate begin,LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            " values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 更新员工 通过动态sql
     * @param emp
     */

    void update(Emp emp);
    @Select("select *from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id删除该部门员工数据
     * @param deptId
     */
    @Delete("delete from emp where dept_id= #{deptId}")
    void deleteByDeptId(Integer deptId);
}
