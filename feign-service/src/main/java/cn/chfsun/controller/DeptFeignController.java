package cn.chfsun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.chfsun.pojo.Dept;
import cn.chfsun.service.DeptService;
import cn.chfsun.tool.Result;

@RestController
@RequestMapping("/feign")
public class DeptFeignController {
	@Autowired
	private DeptService deptService;

	@PostMapping("/insert")
	public Result insert(@RequestBody Dept dept) {
		return deptService.insert(dept);
	}

	@GetMapping("/{deptNo}")
	public Result<Dept> getDept(@PathVariable Integer deptNo) {
		return deptService.getDept(deptNo);
	}

	@GetMapping("/listDept")
	public Result<List<Dept>> listDept() {
		return deptService.listDept();
	}

	@GetMapping("/queryListByDeptNos")
	public Result<List<Dept>> queryListByDeptNos(@RequestParam List<Integer> deptNos) {
		return deptService.queryListByDeptNos(deptNos);
	}

	@PostMapping("/update")
	public Result update(@RequestBody Dept dept) {
		return deptService.update(dept);
	}

	@PostMapping("/delete/{deptNo}")
	public Result delete(@PathVariable Integer deptNo) {
		return deptService.delete(deptNo);

	}
}
