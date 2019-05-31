package com.clearsys.professorama.api.repositories;


import java.util.Optional;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Aluno;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "AlunoRepository.systemLogin",
			query="select * from aluno where usuario = :usuario and senha = :senha")
})

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	@Override
	Optional<Aluno> findById(Long id);
	//Aluno systemLogin(@Param ("usuario") String usuario, @Param("senha") String senha);
	Aluno findByUsuario(String user); 
	Aluno findBySenha(String password);
	Aluno findBySerie(String serie);
}
