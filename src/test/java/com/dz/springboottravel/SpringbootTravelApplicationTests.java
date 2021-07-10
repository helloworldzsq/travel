package com.dz.springboottravel;

import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.ProgramService;
import com.dz.springboottravel.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class SpringbootTravelApplicationTests {

	@Autowired
	private ProgramService programService;
	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
		Program byId = programService.getById(7);
		byId.setTitle("suzhou1");
		boolean b = programService.updateById(byId);
		System.out.println(b);
	}
	@Test
	void test1(){
		String s="1 2 3 4 ";
		String[] s1 = s.split(" ");
		for (int i = 0; i < s1.length; i++) {
			System.out.println(s1[i]);
		}
		System.out.println(s1.length);
	}
	@Test
	void test2(){
		HashMap<String, Integer> map = new HashMap<>();
		map.put("dz",100);
		map.put("zsq",200);
	}
}
