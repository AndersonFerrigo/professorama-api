package com.clearsys.professorama.api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Aluno;

@Transactional(readOnly = true)
public interface AlunoRepository extends JpaRepository<Aluno, String>{
	
	Aluno findByUsuario(String user); 
	Aluno findBySenha(String password);
	Aluno findByNivelEscolar(String nivelEscolar);
	Aluno findBySerie(int serie);
}
