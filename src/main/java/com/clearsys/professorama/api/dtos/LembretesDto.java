package com.clearsys.professorama.api.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class LembretesDto {

	private Long id;
	private String materia;
	private String serie;
	private String data;
	private String descricao;

	public LembretesDto() {
		// TODO Auto-generated constructor stub
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

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@NotNull(message = "O campo data não pode ser vazio")
	@Length(min = 1, max = 20, message = "Data deve conter entre 1 e 20 caracteres.")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@NotNull(message = "O campo descrição não pode ser vazio")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "LembretesDto [id=" + id + ", materia=" + materia + ", serie=" + serie + ", data=" + data
				+ ", descricao=" + descricao + "]";
	}

}
