package cn.chfsun.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import cn.chfsun.pojo.Dept;
import cn.chfsun.tool.Result;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;

@Service
public class DeptService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeptService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${service-url.dept-service}")
	private String deptServiceUrl;

	@HystrixCommand(fallbackMethod = "fallbackfail", commandKey = "getUserCommand", groupKey = "getUserGroup", threadPoolKey = "getUserThreadPool")
	public Result getDeptOne(Integer deptNo) {
		return restTemplate.getForObject(deptServiceUrl + "/dept/{deptNo}", Result.class, deptNo);
	}

	/**
	 * 注意：声明的参数需要包含controller的声明参数
	 * 
	 * @param deptNo
	 * @return
	 */
	public Result fallbackfail(@PathVariable Integer deptNo) {
		return new Result("服务调用失败", deptNo);
	}

	/**
	 * 这里忽略了NullPointerException，当id为100时抛出IndexOutOfBoundsException，id为2时抛出NullPointerException：
	 * 
	 * @param deptNo
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "fallbackfailException", ignoreExceptions = { NullPointerException.class })
	public Result getDeptInfoException(Integer deptNo) {
		if (deptNo == 100) {
			throw new IndexOutOfBoundsException();
		} else if (deptNo == 2) {
			throw new NullPointerException();
		}

		return restTemplate.getForObject(deptServiceUrl + "/dept/{deptNo}", Result.class, deptNo);
	}

	public Result fallbackfailException(@PathVariable Integer deptNo, Throwable e) {
		LOGGER.error("id {},throwable class:{}", deptNo, e.getClass());
		return new Result("服务调用失败", 500);
	}

	/**
	 * 模拟缓存
	 * 
	 * @param deptNo
	 * @return
	 */
	@CacheResult(cacheKeyMethod = "getCacheKey")
	@HystrixCommand(fallbackMethod = "fallbackfail", commandKey = "getDeptInfoByCache")
	public Result getDeptInfoByCache(Integer deptNo) {
		LOGGER.info("getDeptInfoByCache deptNo:{}", deptNo);
		return restTemplate.getForObject(deptServiceUrl + "/dept/{deptNo}", Result.class, deptNo);
	}

	/**
	 * 为缓存生成key的方法
	 *
	 * @return
	 */
	public String getCacheKey(Integer deptNo) {
		return String.valueOf(deptNo);
	}

	@HystrixCommand
	@CacheRemove(commandKey = "getDeptInfoByCache", cacheKeyMethod = "getCacheKey")
	public Result removeCache(Integer deptNo) {
		LOGGER.info("removeCache deptNo:{}", deptNo);
		return restTemplate.getForObject(deptServiceUrl + "/dept/{deptNo}", Result.class, deptNo);
	}

	/**
	 * 使用@HystrixCollapser实现请求合并
	 * 
	 * @param deptNo
	 * @return
	 */
	@HystrixCollapser(batchMethod = "listDeptByDeptNos", collapserProperties = {
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "100") })
	public Future<Dept> getDeptInfo(Integer deptNo) {
		return new AsyncResult<Dept>() {
			@Override
			public Dept invoke() {
				Result result = restTemplate.getForObject(deptServiceUrl + "/dept/{deptNo}", Result.class, deptNo);
				Map data = (Map) result.getData();
				Dept dept = BeanUtil.mapToBean(data, Dept.class, true);
				LOGGER.info("getDeptInfo deptName:{}", dept.getDeptName());
				return dept;
			}
		};
	}

	@HystrixCommand
	public List<Dept> listDeptByDeptNos(List<Integer> deptNos) {
		LOGGER.info("listDeptByDeptNos:{}", deptNos);
		Result result = restTemplate.getForObject(deptServiceUrl + "/dept/queryListByDeptNos?deptNos={1}", Result.class,
				CollUtil.join(deptNos, ","));
		return (List<Dept>) result.getData();
	}
}
