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

import com.clearsys.professorama.api.entities.Atividade;
import com.clearsys.professorama.api.repositories.AtividadeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AtividadeServiceTest {
	
	@MockBean
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private AtividadeService atividadeService;

	@Before
	public void setup () throws Exception{
		BDDMockito.given(this.atividadeRepository.save(ArgumentMatchers.any(Atividade.class))).willReturn(new Atividade());
	}
	
	@Test
	public void testPersistirAtividade() {
		Atividade atividade = this.atividadeService.persistir(new Atividade());
		assertNotNull(atividade);
	}
	
}
