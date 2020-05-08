package cn.chfsun.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chfsun.pojo.Dept;
import cn.chfsun.service.DeptService;
import cn.chfsun.tool.Result;
import cn.hutool.core.thread.ThreadUtil;

@RestController
@RequestMapping("/hystrix")
public class DeptController {
	@Autowired
	private DeptService deptService;

	@GetMapping("/dept/{deptNo}")
	public Result getDeptInfo(@PathVariable Integer deptNo) {
		return deptService.getDeptOne(deptNo);
	}

	@GetMapping("/exception/{deptNo}")
	public Result getDeptInfoException(@PathVariable Integer deptNo) {
		return deptService.getDeptInfoException(deptNo);
	}

	/**
	 * 模拟缓存
	 * 
	 * @param deptNo
	 * @return
	 */
	@GetMapping("/cache/{deptNo}")
	public Result getDeptInfoByCache(@PathVariable Integer deptNo) {
		// HystrixRequestContext.initializeContext();// 初始化请求上下文
		deptService.getDeptInfoByCache(deptNo);
		deptService.getDeptInfoByCache(deptNo);
		deptService.removeCache(deptNo);// 移除缓存
		deptService.getDeptInfoByCache(deptNo);
		deptService.getDeptInfoByCache(deptNo);
		return new Result("操作成功", 200);
	}

	@GetMapping("/queryByCollapser")
	public Result queryByCollapser() throws ExecutionException, InterruptedException {
		Future<Dept> future1 = deptService.getDeptInfo(1);
		Future<Dept> future2 = deptService.getDeptInfo(3);
		future1.get();
		future2.get();
		ThreadUtil.safeSleep(200);
		Future<Dept> future3 = deptService.getDeptInfo(6);
		future3.get();
		return new Result("操作成功", 200);
	}
}
