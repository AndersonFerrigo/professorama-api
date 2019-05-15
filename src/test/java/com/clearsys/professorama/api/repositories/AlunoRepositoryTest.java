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
	private static final String user = "joaojoao";
	private static final String password = "1234";
	private static final int anoLetivo = 3;
	private static final String nivelEscolar = "Medio";
	
	
	@Before
	public  void setUp() throws Exception{
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setNivelEscolar(nivelEscolar);
		aluno.setSerie(anoLetivo);
		aluno.setUsuario(user);
		aluno.setSenha(PasswordUtils.gerarBCrypt(password));
	
		this.alunoRepository.save(aluno);
	}
	
	@After
	public final void tearDown() {
		this.alunoRepository.deleteAll();
	}
	
	@Test
	public void testBuscaLogin() {
		Aluno aluno = this.alunoRepository.findByUsuario(user);
		assertEquals(user, aluno.getUsuario());
	}
	
	
	@Test
	public void findBySerieAndNivelEscolar() {
		Aluno aluno = this.alunoRepository.findByNivelEscolar(nivelEscolar);
		assertEquals(nivelEscolar, aluno.getNivelEscolar());
	}
	
	@Test
	public void findBySerie() {
		Aluno aluno = this.alunoRepository.findBySerie(anoLetivo);
		assertEquals(anoLetivo, aluno.getSerie());
	}

	
}
