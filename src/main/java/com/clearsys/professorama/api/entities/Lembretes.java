package com.clearsys.professorama.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lembrete")
public class Lembretes implements Serializable {

	/**
	 * Entidade responsável por representar um determinado assunto no sistema
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String materia;
	private String serie;
	private String data;
	private String assunto;
	private String descricao;

	public Lembretes() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "materia", nullable = false)
	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@Column(name = "serie")
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Column(name = "data", nullable = false)
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Column(name = "descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Lembretes [id=" + id + ", materia=" + materia + ", serie=" + serie + ", data=" + data + ", assunto="
				+ assunto + ", descricao=" + descricao + "]";
	}

}
