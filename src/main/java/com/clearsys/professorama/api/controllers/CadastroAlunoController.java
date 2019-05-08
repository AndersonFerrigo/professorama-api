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

import com.clearsys.professorama.api.dtos.CadastroAlunoDto;
import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.services.AlunoService;
import com.clearsys.professorama.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-aluno")
@CrossOrigin(origins ="*")
public class CadastroAlunoController {

	private static final  Logger LOG =LoggerFactory.getLogger(CadastroAlunoController.class );
	
	@Autowired
	private AlunoService alunoService;

	public CadastroAlunoController() {
		
	}
	
	@PostMapping
	public ResponseEntity<Response<CadastroAlunoDto>> cadastrar (@Valid @RequestBody CadastroAlunoDto alunoDto, 
			BindingResult result )throws NoSuchAlgorithmException{
			
		LOG.info("Cadastrando Aluno {}", alunoDto.toString());
		Response<CadastroAlunoDto> response = new Response<CadastroAlunoDto>();
		
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

	
	/**
	 * Verifica se o Aluno já existe no banco de dados
	 * 
	 * @param alunoDto
	 * @param result
	 */
	private void validarDadosExixtentes(CadastroAlunoDto alunoDto, BindingResult result) {
		this.alunoService.buscarPorRA(alunoDto.getRa())
				.ifPresent(alu->result.addError(new ObjectError("Aluno", "Ra já existe")));
		
		
		this.alunoService.buscarPorUsuario(alunoDto.getUsuario())
				.ifPresent(alu->result.addError(new ObjectError("Aluno", "Usuario já existe")));
		
	}

	/**
	 * Converte os dados de DTO para Aluno 
	 * 
	 * @param alunoDto
	 * @param result
	 * @return aluno
	 * @throws NoSuchAlgorithmException
	 */
	private Aluno converterDtoParaAluno(CadastroAlunoDto alunoDto, BindingResult result)
			throws NoSuchAlgorithmException{
		
		Aluno aluno = new Aluno();
		
		aluno.setNome(alunoDto.getNome());
		aluno.setSenha(PasswordUtils.gerarBCrypt(alunoDto.getRa()));
		aluno.setUsuario(alunoDto.getUsuario());
		aluno.setSerie(alunoDto.getSerie());
		aluno.setNivelEscolar(alunoDto.getNivelEscolar());
		
		return aluno;
	}
	
	private CadastroAlunoDto converterCadastroAlunoDto(Aluno aluno) {
	
		CadastroAlunoDto alunoDto = new CadastroAlunoDto();
		
		alunoDto.setId(aluno.getId());
		alunoDto.setNome(aluno.getNome());
		alunoDto.setUsuario(aluno.getUsuario());
		alunoDto.setRa(aluno.getSenha());
		alunoDto.setSerie(aluno.getSerie());
		alunoDto.setNivelEscolar(aluno.getNivelEscolar());
		
		return alunoDto;
	}


}
