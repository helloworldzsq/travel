package com.dz.springboottravel;

import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.ProgramService;
import com.dz.springboottravel.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
