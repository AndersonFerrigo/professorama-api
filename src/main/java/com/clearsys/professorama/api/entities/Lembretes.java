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

	private static final long serialVersionUID = 1L;

	private Long id;
	private String materia;
	private String serie;
	private String dataLembrete;
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

	@Column(name = "data_lembrete", nullable = false)
	public String getDataLembrete() {
		return dataLembrete;
	}

	public void setDataLembrete(String dataLembrete) {
		this.dataLembrete = dataLembrete;
	}

	@Column(name = "assunto", nullable = false)
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
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
		return "Lembretes [id=" + id + ", materia=" + materia + ", serie=" + serie + ", dataLembrete=" + dataLembrete
				+ ", assunto=" + assunto + ", descricao=" + descricao + "]";
	}

}
