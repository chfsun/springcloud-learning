package cn.chfsun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.chfsun.pojo.Dept;
import cn.chfsun.pojo.Result;

@RestController
@RequestMapping("/dept")
public class DeptRibbonController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${service-url.dept-service}")
	private String deptServiceUrl;

	@GetMapping("/{deptNo}")
	public Result getDept(@PathVariable Integer deptNo) {
		return restTemplate.getForObject(deptServiceUrl + "/dept/{1}", Result.class, deptNo);
	}

	@PostMapping("/insert")
	public Result insert(@RequestBody Dept dept) {
		return restTemplate.postForObject(deptServiceUrl + "/dept/insert", dept, Result.class);
	}

	@PostMapping("/update")
	public Result update(@RequestBody Dept dept) {
		return restTemplate.postForObject(deptServiceUrl + "/dept/update", dept, Result.class);
	}

	@PostMapping("/delete/{id}")
	public Result delete(@PathVariable Integer deptNo) {
		return restTemplate.postForObject(deptServiceUrl + "/dept/delete/{1}", null, Result.class, deptNo);
	}
}
