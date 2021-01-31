package com.clearsys.professorama.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/aluno")
@CrossOrigin(origins = "*")

public class AlunoController {

	private static final Logger LOG = LoggerFactory.getLogger(AlunoController.class);

	@Autowired
	private AlunoService alunoService;

	public AlunoController() { }
	
	//OK
	@PostMapping
	public ResponseEntity<Response<AlunoDto>> cadastrar(@Valid @RequestBody AlunoDto alunoDto, BindingResult result)
			throws NoSuchAlgorithmException {

		LOG.info("Cadastrando Aluno {}", alunoDto.toString());
		Response<AlunoDto> response = new Response<AlunoDto>();

		validarDadosExixtentes(alunoDto, result);
		Aluno aluno = this.converterDtoParaAluno(alunoDto, result);

		if (result.hasErrors()) {
			LOG.error("Erro validando dados cadastro de Aluno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.alunoService.persistir(aluno);
		response.setData(this.converterCadastroAlunoDto(aluno));
		return ResponseEntity.ok(response);
	}

	//OK
	@GetMapping
	public List<Aluno> listarAlunos(){
		LOG.info("Listando todos os alunos");
		return alunoService.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<AlunoDto>> findById(@PathVariable("id") Long id) {

		LOG.info("Buscando aluno pelo id {}", id);
		Response<AlunoDto> response = new Response<AlunoDto>();
		Optional<Aluno> aluno = this.alunoService.findById(id);

		if (!aluno.isPresent()) {
			LOG.info("Aluno não encontrado para o id {}", id);
			response.getErrors().add("Aluno não encontrado para o id " + id);
			return ResponseEntity.notFound().build();
		}

		LOG.info("Buscando aluno pelo id {} antes de chamar o metodo ", id);
		response.setData(this.converterCadastroAlunoDto(aluno.get()));
		return ResponseEntity.ok(response);
	}

	//OK
	@GetMapping(value = "/login/usuario/{usuario}/senha/{senha}")
	public ResponseEntity <Aluno> logar(@PathVariable ("usuario") String usuario,
				@PathVariable("senha") String senha )throws NoSuchAlgorithmException {

		LOG.info("Buscando aluno pelo usuario {} e pela senha {} ", usuario, senha);
		
		Optional<Aluno> aluno = alunoService.alunoLogin(usuario, senha);
		
		if (!aluno.isPresent()) {
			LOG.error("Aluno não encontrado.");
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(aluno.get());
	}
	
	//OK
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<Response<AlunoDto>> buscarPorUsuario(@PathVariable("usuario") String usuario) {
		LOG.info("Buscando aluno pelo usuario {}", usuario);
		Response<AlunoDto> response = new Response<AlunoDto>();
		Optional<Aluno> aluno = this.alunoService.findByUsuario(usuario);

		if (!aluno.isPresent()) {
			LOG.info("Aluno não encontrado para o usuario {}", usuario);
			response.getErrors().add("Aluno não encontrado para o usuario " + usuario);
			return ResponseEntity.badRequest().body(response);
		}

		LOG.info("Buscando aluno pelo usuario  {} antes de chamar o metodo ", usuario);

		response.setData(this.converterCadastroAlunoDto(aluno.get()));
		return ResponseEntity.ok(response);
	}

	//OK
	@GetMapping(value = "/ra/{ra}")
	public ResponseEntity<Aluno> buscarPorRa(@PathVariable("ra") String ra) throws NoSuchAlgorithmException {
		LOG.info("Buscando aluno pelo ra {}", ra);

		Optional<Aluno> aluno = this.alunoService.findByRa(ra);

		if (!aluno.isPresent()) {
			LOG.error("Erro validando dados login de Aluno: {}", aluno);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		LOG.info("Buscando aluno pelo ra {} antes de chamar o metodo ", ra);
		converterCadastroAlunoDto(aluno.get());
		return ResponseEntity.ok(aluno.get());
	}

	//OK
	@PutMapping(value = "/atualiza/{id}")
	public ResponseEntity<Response<AlunoDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody AlunoDto alunoDto, BindingResult result)
			throws ParseException, NoSuchAlgorithmException {

		LOG.info("Atualizando aluno {}", alunoDto.toString());
		Response<AlunoDto> response = new Response<AlunoDto>();
	
		alunoDto.setId(id);
		Aluno aluno = this.converterDtoParaAluno(alunoDto, result);

		if (result.hasErrors()) {
			LOG.error("Erro validando aluno {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		aluno = this.alunoService.persistir(aluno);
		response.setData(this.converterCadastroAlunoDto(aluno));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/deletar/{ra}")
	public ResponseEntity<Response<String>> remover(@PathVariable("ra") String ra) {
		LOG.info("Removendo aluno pelo ra : {}", ra);

		Response<String> response = new Response<String>();

		Optional<Aluno> aluno = this.alunoService.findByRa(ra);

		if (!aluno.isPresent()) {
			LOG.error("Erro ao remover aluno pelo ra: {}", ra);
			response.getErrors().add("Erro ao remover aluno. Registro não encontrado para o ra " + ra);
			return ResponseEntity.badRequest().body(response);
		}

		this.alunoService.deletar(ra);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	
	/**
	 * Verifica se o Aluno já existe no banco de dados
	 * 
	 * @param alunoDto
	 * @param result
	 */
	private void validarDadosExixtentes(AlunoDto alunoDto, BindingResult result) {
		this.alunoService.findByUsuario(alunoDto.getUsuario())
				.ifPresent(alu -> result.addError(new ObjectError("Aluno", "Usuario já existe")));

	}

	/**
	 * Converte os dados de DTO para Aluno
	 * 
	 * @param alunoDto
	 * @param result
	 * @return aluno
	 * @throws NoSuchAlgorithmException
	 */
	private Aluno converterDtoParaAluno(AlunoDto alunoDto, BindingResult result) throws NoSuchAlgorithmException {

		Aluno aluno = new Aluno();
		aluno.setId(alunoDto.getId());
		aluno.setNome(alunoDto.getNome());
		aluno.setSerie(alunoDto.getSerie());
		aluno.setRa(alunoDto.getRa());
		aluno.setUsuario(alunoDto.getUsuario());
		aluno.setSenha(alunoDto.getSenha());
		return aluno;
	}

	private AlunoDto converterCadastroAlunoDto(Aluno aluno) {

		AlunoDto alunoDto = new AlunoDto();

		alunoDto.setId(aluno.getId());
		alunoDto.setNome(aluno.getNome());
		alunoDto.setSerie(aluno.getSerie());
		alunoDto.setRa(aluno.getRa());
		alunoDto.setUsuario(aluno.getUsuario());
		alunoDto.setSenha(aluno.getSenha());

		return alunoDto;
	}

}
