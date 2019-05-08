package com.clearsys.professorama.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProfessoramaApiApplication.class)
@DataJpaTest
@ActiveProfiles("test")
public class ProfessoramaApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}

