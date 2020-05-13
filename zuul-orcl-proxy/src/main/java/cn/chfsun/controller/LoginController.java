package cn.chfsun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/dologin")
	public String dologin(String name) {
		return "你好，欢迎你：" + name;
	}
}
