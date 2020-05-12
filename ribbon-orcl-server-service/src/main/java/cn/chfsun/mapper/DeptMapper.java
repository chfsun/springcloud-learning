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
	 * insert
	 * 
	 * @param dept
	 * @return
	 */
	@Insert("insert into dept values(null,#{deptName},#{localtion})")
	public int insert(Dept dept);

	/**
	 * delete
	 * 
	 * @param deptNo
	 * @return
	 */
	@Delete("delete from dept where deptNo=#{deptNo}")
	public int del(@Param("deptNo") Integer deptNo);

	/**
	 * update
	 * 
	 * @param dept
	 * @return
	 */
	@Update("update from dept set deptName=#{deptName},localtion=#{localtion} where deptNo=#{deptNo} ")
	public int edit(Dept dept);

	/**
	 * queryOne
	 * 
	 * @param deptNo
	 * @return
	 */
	@Select("select * from dept where deptNo=#{deptNo}")
	public Dept queryOne(@Param("deptNo") Integer deptNo);

	/**
	 * queryList
	 * 
	 * @return
	 */
	@Select("select * from dept ")
	public List<Dept> queryList();

	// select
	/*
	 * @Select("<script>select * from dept where  deptNo  in" +
	 * "<foreach item='item' index='index' collection='deptNos' open='(' close=')' separator=','>"
	 * + "#{item.deptNo}</foreach></script>") public List<Dept>
	 * queryListByDeptNos(@Param("deptNos") List<Dept> deptNos);
	 */

	@Select("<script>select deptNo,deptName,localtion from dept where  deptNo  in"
			+ "<foreach item='item' index='index' collection='deptNos' open='(' close=')' separator=','>"
			+ "#{item}</foreach></script>")
	@Results(value = { @Result(column = "deptName", property = "deptName"),
			@Result(column = "localtion", property = "localtion") })
	public List<Dept> queryListByDeptNos(@Param("deptNos") List<Integer> deptNos);
}
