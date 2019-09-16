package com.clearsys.professorama.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.clearsys.professorama.api.entities.Professor;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class ProfessorRepositoryTest {

	@Autowired
	private ProfessorRepository professorRepository;

	private static final String nome = "Antonio";
	private static final String user = "AntonioProf";
	private static final String password = "portuguesAnt";

	@Before
	public void setUp() throws Exception {
		Professor professor = new Professor();
		professor.setNome(nome);
		professor.setUsuario(user);
		professor.setSenha(password);

		this.professorRepository.save(professor);
	}

	@After
	public final void tearDown() {
		this.professorRepository.deleteAll();
	}

	@Test
	public void findByNome() {
		Professor professor = this.professorRepository.findByNome(nome);
		assertEquals(nome, professor.getNome());
	}

	@Test
	public void findByUser() {
		Professor professor = this.professorRepository.findByUsuario(user);
		assertEquals(user, professor.getUsuario());

	}

}
