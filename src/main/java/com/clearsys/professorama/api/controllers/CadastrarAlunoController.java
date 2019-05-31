package com.clearsys.professorama.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clearsys.professorama.api.dtos.AlunoDto;
import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.services.AlunoService;
import com.clearsys.professorama.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-aluno")
@CrossOrigin(origins ="*")

public class CadastrarAlunoController {

	private static final  Logger LOG =LoggerFactory.getLogger(CadastrarAlunoController.class );

	@Autowired
	private AlunoService alunoService;
	
	public CadastrarAlunoController() {
	}
	
	@PostMapping
	public ResponseEntity<Response<AlunoDto>> cadastrar (@Valid @RequestBody AlunoDto alunoDto, 
			BindingResult result )throws NoSuchAlgorithmException{
			
		LOG.info("Cadastrando Aluno {}", alunoDto.toString());
		Response<AlunoDto> response = new Response<AlunoDto>();
		
		validarDadosExixtentes(alunoDto, result );
		Aluno aluno = this.converterDtoParaAluno(alunoDto, result);
		
		if(result.hasErrors()) {
			LOG.error("Erro validando dados cadastro de Aluno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
			
		}
	
		this.alunoService.persistir(aluno);
		
		response.setData(this.converterCadastroAlunoDto(aluno));
		return ResponseEntity.ok(response);
		
		
	}

	private void validarDadosExixtentes(AlunoDto alunoDto, BindingResult result) {
		this.alunoService.buscarPorUsuario(alunoDto.getUsuario())
				.ifPresent(alu->result.addError(new ObjectError("Aluno", "Usuario já existe")));
		
	}

	private Aluno converterDtoParaAluno(AlunoDto alunoDto, BindingResult result)
			throws NoSuchAlgorithmException{
		
		Aluno aluno = new Aluno();
		aluno.setId(alunoDto.getId());
		aluno.setNome(alunoDto.getNome());
		aluno.setSerie(alunoDto.getSerie());
		aluno.setUsuario(alunoDto.getUsuario());
		aluno.setSenha(PasswordUtils.gerarBCrypt(alunoDto.getSenha()));
		return aluno;
	}
	
	private AlunoDto converterCadastroAlunoDto(Aluno aluno) {
	
		AlunoDto alunoDto = new AlunoDto();
		
		alunoDto.setId(aluno.getId());
		alunoDto.setNome(aluno.getNome());
		alunoDto.setSerie(aluno.getSerie());
		alunoDto.setUsuario(aluno.getUsuario());
		alunoDto.setSenha(aluno.getSenha());
		
		return alunoDto;
	}


	
}
