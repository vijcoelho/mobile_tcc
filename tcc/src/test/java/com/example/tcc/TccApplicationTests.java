package com.example.tcc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.tcc.model.User;
import com.example.tcc.model.UserDao;

@SpringBootTest
class TccApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	void contextLoads() {
		User user = new User();
		user.setUserName("adm");
		user.setUserEmail("adm@email.com");
		user.setUserPassword("adm123");

		userDao.save(user);
	}

}
