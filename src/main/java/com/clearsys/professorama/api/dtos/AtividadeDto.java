package com.clearsys.professorama.api.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AtividadeDto {

	private Long id;
	private String materia;
	private String serie;
	private String dataEntrega;
	private String descricao;
	private Long professor_id;

	public AtividadeDto() {
	}

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

	public void setSerie(String anoDestinado) {
		this.serie = anoDestinado;
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

	public Long getProfessor_id() {
		return professor_id;
	}

	public void setProfessor_id(Long professor_id) {
		this.professor_id = professor_id;
	}

	@Override
	public String toString() {
		return "CadastroAtividadeDto [id=" + id + ", materia=" + materia + ", serie=" + serie + ", dataEntrega="
				+ dataEntrega + ", descricao=" + descricao + ", professor_id=" + professor_id + "]";
	}

}
