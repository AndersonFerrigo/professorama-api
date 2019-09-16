package com.clearsys.professorama.api.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.repositories.AlunoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AlunoServiceTest {

	@MockBean
	private AlunoRepository alunoRepository;

	@Autowired
	private AlunoService alunoService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.alunoRepository.save(ArgumentMatchers.any(Aluno.class))).willReturn(new Aluno());
	}

	@Test
	public void testPersistirAluno() {
		Aluno aluno = this.alunoService.persistir(new Aluno());
		assertNotNull(aluno);
	}
}
