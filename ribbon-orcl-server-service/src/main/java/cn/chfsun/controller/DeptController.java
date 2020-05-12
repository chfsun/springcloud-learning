package cn.chfsun.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.chfsun.pojo.Dept;
import cn.chfsun.pojo.Result;
import cn.chfsun.service.DeptService;

@RestController
@RequestMapping("/dept")
public class DeptController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	private DeptService deptService;

	@Value("${server.port}")
	private String port;

	@PostMapping("/insert")
	public Result insert(@RequestBody Dept dept) {
		int rs = deptService.insert(dept);
		if (rs > 0) {
			return new Result("操作成功", 200);
		} else {
			return new Result("操作失败", -200);
		}

	}

	@GetMapping("/{deptNo}")
	public Result<Dept> getDept(@PathVariable Integer deptNo) {
		Dept dept = deptService.queryOne(deptNo);
		LOGGER.info("当前端口为:" + port + "，根据deptNo获取部门信息，部门名称为：{}", dept.getDeptName());
		return new Result<Dept>(dept);
	}

	@GetMapping("/listDept")
	public Result<List<Dept>> listDept() {
		List<Dept> deptList = deptService.queryList();
		LOGGER.info("获取部门列表总数为：{}", deptList.size());
		return new Result<List<Dept>>(deptList);
	}

	@GetMapping("/queryListByDeptNos")
	public Result<List<Dept>> queryListByDeptNos(@RequestParam List<Integer> deptNos) {
		/*
		 * List<Integer> lists = new ArrayList<Integer>(); lists.add(1); lists.add(3);
		 * lists.add(6);
		 */
		List<Dept> deptList = deptService.queryListByDeptNos(deptNos);
		LOGGER.info("获取部门列表总数为：{}", deptList.size());
		return new Result<List<Dept>>(deptList);
	}

	@PostMapping("/update")
	public Result update(@RequestBody Dept dept) {
		int rs = deptService.edit(dept);
		if (rs > 0) {
			return new Result("操作成功", 200);
		} else {
			return new Result("操作失败", -200);
		}
	}

	@PostMapping("/delete/{deptNo}")
	public Result delete(@PathVariable Integer deptNo) {
		int rs = deptService.del(deptNo);
		if (rs > 0) {
			return new Result("操作成功", 200);
		} else {
			return new Result("操作失败", -200);
		}
	}
}