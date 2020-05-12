package cn.chfsun;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.chfsun.pojo.Dept;
import cn.chfsun.service.DeptService;

@SpringBootTest
class RibbonOrclServerServiceApplicationTests {
	@Autowired
	private DeptService deptService;

	/*
	 * @Test public void testqueryOne() { Dept dept = deptService.queryOne(1);
	 * System.out.println(dept.getDeptName()); }
	 */

	/*
	 * @Test public void testqueryList() { List<Dept> list =
	 * deptService.queryList(); for (Dept dept : list) {
	 * System.out.println(dept.getDeptName()); } }
	 */

	@Test
	public void queryListByDeptNos() {
		List<Integer> lists = new ArrayList<Integer>();
		lists.add(1);
		lists.add(3);
		lists.add(6);
		List<Dept> list = deptService.queryListByDeptNos(lists);
		for (Dept dept : list) {
			System.out.println(dept.getDeptName());
		}
	}

}
