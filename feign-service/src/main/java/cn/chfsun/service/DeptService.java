package cn.chfsun.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cn.chfsun.pojo.Dept;
import cn.chfsun.tool.Result;

@FeignClient(value = "ribbon-orcl-server-service", fallback = DeptFallbackService.class)
public interface DeptService {
	@PostMapping("/dept/insert")
	Result insert(@RequestBody Dept dept);

	@GetMapping("/dept/{deptNo}")
	Result<Dept> getDept(@PathVariable Integer deptNo);

	@GetMapping("/dept/listDept")
	Result<List<Dept>> listDept();

	@GetMapping("/dept/queryListByDeptNos")
	Result<List<Dept>> queryListByDeptNos(@RequestParam List<Integer> deptNos);

	@PostMapping("/dept/update")
	Result update(@RequestBody Dept dept);

	@PostMapping("/dept/delete/{deptNo}")
	Result delete(@PathVariable Integer deptNo);
}
