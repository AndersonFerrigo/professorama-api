package com.clearsys.professorama.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.repositories.AlunoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration")
@ActiveProfiles("test")
public class AlunoServiceTest {
	
	@MockBean
	private AlunoRepository alunoRepository;
	
	@Autowired
	private AlunoService alunoService;
	
	private static final int ID = 123;
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.alunoRepository.findById(Mockito.anyInt())).willReturn(new Aluno());
		BDDMockito.given(this.alunoRepository.save(Mockito.any(Aluno.class))).willReturn(new Aluno());
	}
	
	@Test
	public void testBuscarAlunoPeloId() {
		Optional<Aluno> aluno = this.alunoService.buscarPorId(ID);
		assertTrue(aluno.isPresent());
	}
	
	@Test
	public void testPersistirAluno() {
		Aluno aluno = this.alunoService.persistir(new Aluno());
		assertNotNull(aluno);
	}
}
