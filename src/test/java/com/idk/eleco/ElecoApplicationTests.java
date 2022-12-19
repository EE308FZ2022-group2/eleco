package com.idk.eleco;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElecoApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		String[] array = {"one","two","three"};
		String s = array.toString();
		System.out.println(s);
	}


}
