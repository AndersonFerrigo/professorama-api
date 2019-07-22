package com.clearsys.professorama.api.repositories;


import java.util.Optional;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.clearsys.professorama.api.entities.Aluno;

@Transactional(readOnly = false)
@NamedQueries({
	@NamedQuery(name = "AlunoRepository.findByUsuarioAndSenha",
			query="select id,nome,serie,perfil,usuario,senha from aluno where usuario =:? and senha =:?"),
	
})

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String>{
	
	Optional<Aluno> findById(int id);
    
	Optional<Aluno> findByUsuarioAndSenha(@Param("usuario") String usuario, @Param("senha") String senha);
	Aluno findByUsuario(String user);
	Aluno findBySenha(String password);
	Aluno findBySerie(String serie);
	Optional<Aluno> findByRa(String ra);
	
	@Modifying
	@Query(value = "delete from aluno where ra =:ra ", nativeQuery = true)
	void deleteAlunoByRa(@Param("ra") String ra);
	
	
}
