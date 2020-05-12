package cn.chfsun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chfsun.mapper.DeptMapper;
import cn.chfsun.pojo.Dept;

@Service
public class DeptService {
	@Autowired
	private DeptMapper deptMapper;

	public int insert(Dept dept) {
		return deptMapper.insert(dept);
	};

	public int del(Integer deptNo) {
		return deptMapper.del(deptNo);
	};

	public int edit(Dept dept) {
		return deptMapper.edit(dept);
	};

	public Dept queryOne(Integer deptNo) {
		return deptMapper.queryOne(deptNo);
	};

	public List<Dept> queryList() {
		return deptMapper.queryList();
	};

	public List<Dept> queryListByDeptNos(List<Integer> deptNos) {
		return deptMapper.queryListByDeptNos(deptNos);
	}
}