package com.clearsys.professorama.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clearsys.professorama.api.dtos.AlunoDto;
import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.services.AlunoService;
import com.clearsys.professorama.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/aluno")
@CrossOrigin(origins ="*")
public class AlunoController {

	private static final  Logger LOG =LoggerFactory.getLogger(AlunoController.class );
	
	@Autowired
	private AlunoService alunoService;

	public AlunoController() {
		
	}
	
	/*
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

*/
	/**
	 * Busca um aluno pelo id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<AlunoDto>> listarPorId(@PathVariable("id") Long id){
		LOG.info("Buscando aluno pelo id {}", id);
		Response<AlunoDto> response = new Response<AlunoDto>();
		Optional<Aluno> aluno = this.alunoService.buscarPorId(id);
	
		if(!aluno.isPresent()) {
			LOG.info("Aluno não encontrado para o id {}", id);
			response.getErrors().add("Aluno não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		LOG.info("Buscando aluno pelo id {} antes de chamar o metodo ", id);
		
		response.setData(this.converterCadastroAlunoDto(aluno.get()));
		return ResponseEntity.ok(response);
	}
	
/*	
	@GetMapping(value = "/{usuario}/{senha}")
	public ResponseEntity<Response<AlunoDto>> buscarLogin(@PathVariable("user") String usuario, @PathVariable("senha") String senha){
		LOG.info("Buscando aluno pelo usuario {} e senha {}", usuario,senha);
		Response<AlunoDto> response = new Response<AlunoDto>();
		Aluno aluno = this.alunoService.buscarLogin(usuario, senha);
	
		if(!aluno.equals(null)) {
			LOG.info("Aluno não encontrado para o usuario {} e senha",usuario, senha);
			response.getErrors().add("Aluno não encontrado para o usuario =  " + usuario + " e senha = " + senha );
			return ResponseEntity.badRequest().body(response);
		}
		
		LOG.info("Buscando aluno pelo usuario {} e senha {} antes de chamar o metodo ", usuario, senha);
		
		response.setData(this.converterCadastroAlunoDto(aluno));
		return ResponseEntity.ok(response);
	}
	
	*/
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<AlunoDto>> atualizar(@PathVariable("id") Long id,
				@Valid @RequestBody AlunoDto alunoDto, BindingResult result ) throws ParseException, NoSuchAlgorithmException{
		
		LOG.info("Atualizando aluno {}", alunoDto.toString() );
		Response<AlunoDto> response = new Response<AlunoDto>();
		validarAluno(alunoDto, result);
	
		Aluno aluno = this.converterDtoParaAluno(alunoDto, result);
		
		
			if(result.hasErrors()) {
				LOG.error("Erro validando aluno {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
				
			}
		
		aluno = this.alunoService.persistir(aluno);
		response.setData(this.converterCadastroAlunoDto(aluno));
		
		return ResponseEntity.ok(response);
		
		
	}
	
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
		LOG.info("Removendo aluno {}", id);
		
		Response<String> response = new Response<String>();
	
		Optional<Aluno> aluno = this.alunoService.buscarPorId(id);
		
		if(!aluno.isPresent()) {
			LOG.error("Erro ao remover aluno {}", id);
			response.getErrors().add("Erro ao remover aluno. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.alunoService.remover(id);
		return ResponseEntity.ok(response);
	
	
}

	
	
	/**
	 * Verifica se o Aluno já existe no banco de dados
	 * 
	 * @param alunoDto
	 * @param result
	 */
	private void validarDadosExixtentes(AlunoDto alunoDto, BindingResult result) {
		this.alunoService.buscarPorUsuario(alunoDto.getUsuario())
				.ifPresent(alu->result.addError(new ObjectError("Aluno", "Usuario já existe")));
		
	}

	
	private void validarAluno(@Valid AlunoDto alunoDto, BindingResult result) {
		if (alunoDto.getId()== 0 ) {
			result.addError(new ObjectError("aluno", "aluno não informado."));
			return;
		}

		LOG.info("Validando aluno id {}: ", alunoDto.getId());
		
		Optional<Aluno> aluno = this.alunoService.buscarPorId(alunoDto.getId());
		
		if (!aluno.isPresent()) {
			result.addError(new ObjectError("aluno", "Aluno não encontrado. ID inexistente."));
		}
		
	}
		
	

	
	/**
	 * Converte os dados de DTO para Aluno 
	 * 
	 * @param alunoDto
	 * @param result
	 * @return aluno
	 * @throws NoSuchAlgorithmException
	 */
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
