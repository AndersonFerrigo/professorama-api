package com.clearsys.professorama.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.services.AlunoService;

@RestController
@RequestMapping("/api/login-aluno")
@CrossOrigin(origins ="*")
public class AlunoLoginController {
		 
	private static final  Logger LOG =LoggerFactory.getLogger(AlunoLoginController.class );
	
	@Autowired
	private AlunoService alunoService;
	
	public AlunoLoginController(){}
	
	@RequestMapping(value="/login/usuario/{usuario}/senha/{senha}" ,method=RequestMethod.GET )
	public ResponseEntity<Aluno>logarAluno (@PathVariable ("usuario") String usuario, 
							@PathVariable ("senha") String senha) throws NoSuchAlgorithmException{ 
			
		LOG.info("Buscando aluno pelo usuario {} e pela senha {} ", usuario, senha);
		
		Optional<Aluno> aluno = this.alunoService.buscarLogin(usuario, senha);
	
		LOG.info("Validando dados login de Aluno: {}", aluno);
		
		if(!aluno.isPresent()){
			LOG.error("Erro validando dados login de Aluno: {}", aluno);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
		return ResponseEntity.ok(aluno.get());
	}
	
	@GetMapping("/{usuario}")
	public ResponseEntity<Aluno>buscarPorUsuario(@PathVariable("usuario") String usuario){
		LOG.info("Buscando aluno pelo usuario {}", usuario);
		
		Optional<Aluno> aluno = this.alunoService.buscarPorUsuario(usuario);
	
		if(!aluno.isPresent()) {
			LOG.info("Aluno não encontrado para o usuario {}", usuario);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		LOG.info("Buscando aluno pelo usuario  {} antes de chamar o metodo ", usuario);
		
		return ResponseEntity.ok(aluno.get());
	}
}
