package cn.chfsun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.chfsun.pojo.Dept;

@Mapper
public interface DeptMapper {
	/**
	 * 添加
	 * 
	 * @param dept
	 * @return
	 */
	@Insert("insert into dept values(null,#{deptName},#{localtion})")
	public int insert(Dept dept);

	/**
	 * 删除
	 * 
	 * @param deptNo
	 * @return
	 */
	@Delete("delete from dept where deptNo=#{deptNo}")
	public int del(@Param("deptNo") Integer deptNo);

	@Update("update dept set deptName=#{deptName},localtion=#{localtion} where deptNo=#{deptNo}")
	public int update(Dept dept);

	/**
	 * 查询单个
	 * 
	 * @param deptNo
	 * @return
	 */
	@Select("SELECT * FROM dept WHERE deptNo=#{deptNo}")
	@Results({ @Result(property = "deptNo", column = "deptNo") })
	Dept getDeptInfo(@Param("deptNo") Integer deptNo);

	/**
	 * 查询列表 映射实体
	 * 
	 * @return
	 */
	@Select("SELECT * FROM dept ")
	@Results({ @Result(id = true, column = "deptNo", property = "deptNo"),
			@Result(column = "deptName", property = "deptName"),
			@Result(column = "localtion", property = "localtion") })
	List<Dept> queryList();

}