package com.clearsys.professorama.api.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AtividadeDto {

	private Long id;
	private String materia;
	private String serie;
	private String nomeProfessor;
	private String dataPostagem;
	private String dataEntrega;
	private String descricao;
	
	public AtividadeDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = " O campo matéria não pode ser vazia")
	@Length(min = 1, max = 50, message = "Matéria deve conter entre 1 e 50 caracteres.")
	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@NotNull(message = "O campo serie não pode ser vazio")
	@Length(min = 1, max = 20, message = "Data inicial deve conter entre 1 e 20 caracteres.")
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	@NotNull(message = "O campo nome professor não pode ser vazio")
	@Length(min = 3, max = 255, message = "Nome do professor tem que ser maior do que 3 letras. ")
	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	@NotNull(message = "O campo data da postagem não pode ser vazio")
	@Length(min = 1, max = 20, message = "Data da postagem deve conter entre 1 e 20 caracteres.")
	public String getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(String dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	@NotNull(message = "O campo data entrega não pode ser vazio")
	@Length(min = 1, max = 20, message = "Data de entrega deve conter entre 1 e 20 caracteres.")
	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@NotNull(message = "O campo descrição não pode ser vazio")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
		
}
