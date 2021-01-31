package com.clearsys.professorama.api.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.clearsys.professorama.api.entities.Atividade;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AtividadeRepositoryTest {

	@Autowired
	private AtividadeRepository atividadeRepository;

	private static final String materia = "historia";
	private static final String serie = "3 Ensino medio";
	private static final String dataInicio = "13/05/2019";
	private static final String dataEntrega = "27/05/2019";
	private static final String descricao = "Descreva a ascenção do imperio Otomano";

	@Before
	public void setUp() throws Exception {
		Atividade atividade = new Atividade();

		atividade.setMateria(materia);
		atividade.setSerie(serie);
		
		atividade.setDataEntrega(dataEntrega);
		atividade.setDescricao(descricao);

		this.atividadeRepository.save(atividade);
	}

	@After
	public final void tearDown() {
		this.atividadeRepository.deleteAll();
	}

	@Test
	public void buscaPorMateria() {
		List<Atividade> atividade = this.atividadeRepository.findByMateria(materia);
		assertEquals(materia, ((Atividade) atividade).getMateria());
	}

	@Test
	public void buscaPorDataEntrega() {
		Atividade atividade = this.atividadeRepository.findByDataEntrega(dataEntrega);
		assertEquals(dataEntrega, atividade.getDataEntrega());
	}

}
