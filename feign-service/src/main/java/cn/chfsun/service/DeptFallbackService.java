package cn.chfsun.service;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.chfsun.pojo.Dept;
import cn.chfsun.tool.Result;

@Component
public class DeptFallbackService implements DeptService {

	@Override
	public Result insert(Dept dept) {
		return new Result("调用失败，服务被降级", 500);
	}

	@Override
	public Result<Dept> getDept(Integer deptNo) {
		return new Result("调用失败，服务被降级", 500);
	}

	@Override
	public Result<List<Dept>> listDept() {
		return new Result("调用失败，服务被降级", 500);
	}

	@Override
	public Result<List<Dept>> queryListByDeptNos(List<Integer> deptNos) {
		return new Result("调用失败，服务被降级", 500);
	}

	@Override
	public Result update(Dept dept) {
		return new Result("调用失败，服务被降级", 500);
	}

	@Override
	public Result delete(Integer deptNo) {
		return new Result("调用失败，服务被降级", 500);
	}

}
