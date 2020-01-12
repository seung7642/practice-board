package com.neowiz.practice.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
@SpringBootTest
public class BoardApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() throws Exception {
		String hello = "hello";

//		mvc.perform(get("/hello"))
//				.andExpect(status().isOk())
//				.andExpect(content().string(hello));
	}

}
