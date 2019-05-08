package com.clearsys.professorama.api.services;

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

import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.repositories.ProfessorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration")
@ActiveProfiles("test")
public class ProfessorServiceTest {

	@MockBean
	private ProfessorRepository professorRepository;
	
	@Autowired
	private ProfessorService professorService;
	
	private static final int ID = 12;
	private static final String NOME = "Roberto";
	
	@Before
	public void testBuscaProfessorPorId() {
		BDDMockito.given(this.professorRepository.findById(Mockito.anyInt())).willReturn(new Professor());
		BDDMockito.given(this.professorRepository.findByNome(Mockito.anyString())).willReturn(new Professor());
	
	}
	
	@Test
	public void testBuscaPorId() {
		Optional<Professor> professor = this.professorService.buscarPorId(ID);
	    assertTrue(professor.isPresent());
	}
	
	@Test
	public void testBuscarPorNome() {
		Optional<Professor> professor = this.professorService.buscarPorNome(NOME);
		assertTrue(professor.isPresent());
	}
		
}