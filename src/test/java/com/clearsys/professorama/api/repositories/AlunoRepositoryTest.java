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

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AlunoRepositoryTest {

	@Autowired
	private AlunoRepository alunoRepository;

	private static final String nome = "joao";
	private static final String serie = "3 Ensino Medio";
	private static final String user = "joaojoao";
	private static final String password = "1234";

	@Before
	public void setUp() throws Exception {
		Aluno aluno = new Aluno();

		aluno.setNome(nome);
		aluno.setSerie(serie);
		aluno.setUsuario(user);
		aluno.setSenha(PasswordUtils.gerarBCrypt(password));

		this.alunoRepository.save(aluno);
	}

	@After
	public final void tearDown() {
		this.alunoRepository.deleteAll();
	}

	@Test
	public void testBuscaPorUsuario() {
		Aluno aluno = this.alunoRepository.findByUsuario(user);
		assertEquals(user, aluno.getUsuario());
	}

	@Test
	public void testBuscaPorSenha() {
		Aluno aluno = this.alunoRepository.findBySenha(password);
		assertEquals(password, aluno.getSenha());
	}

	@Test
	public void findBySerie() {
		Aluno aluno = this.alunoRepository.findBySerie(serie);
		assertEquals(serie, aluno.getSerie());
	}

}
