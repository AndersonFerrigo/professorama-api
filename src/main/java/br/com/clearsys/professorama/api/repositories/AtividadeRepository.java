package br.com.clearsys.professorama.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearsys.professorama.api.entities.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long>  {

}
