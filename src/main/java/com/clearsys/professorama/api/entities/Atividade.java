package com.clearsys.professorama.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atividade")
public class Atividade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String materia;
	private String anoDestinado;
	private String nivelEscolar;
	private String dataInicio;
	private String dataEntrega;
	private String descricao;
	private String professor;
	
	public Atividade() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "materia", nullable = false)
	public String getMateria() {
		return materia;
	}
	
	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	@Column(name = "ano_destino")
	public String getAnoDestinado() {
		return anoDestinado;
	}
	
	public void setAnoDestinado(String anoDestinado) {
		this.anoDestinado = anoDestinado;
	}
	
	@Column(name = "nivel_escolar", nullable = false)
	public String getNivelEscolar() {
		return nivelEscolar;
	}
	
	public void setNivelEscolar(String nivelEscolar) {
		this.nivelEscolar = nivelEscolar;
	}
	
	@Column(name = "data_inicio", nullable = false)
	public String getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@Column(name = "data_entrega", nullable = false)
	public String getDataEntrega() {
		return dataEntrega;
	}
	
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	@Column(name = "descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "professor", nullable = false)
	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", materia=" + materia + ", anoDestinado=" + anoDestinado + ", nivelEscolar="
				+ nivelEscolar + ", data_inicio=" + dataInicio + ", data_entrega=" + dataEntrega + ", descricao="
				+ descricao + ", professor=" + professor + "]";
	}
	
	
}
