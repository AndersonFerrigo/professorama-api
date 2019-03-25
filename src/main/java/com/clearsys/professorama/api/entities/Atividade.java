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
	private String data_inicio;
	private String data_entrega;
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
	
	@Column(name = "atividade_materia", nullable = false)
	public String getMateria() {
		return materia;
	}
	
	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	@Column(name = "atividade_ano_destino")
	public String getAnoDestinado() {
		return anoDestinado;
	}
	
	public void setAnoDestinado(String anoDestinado) {
		this.anoDestinado = anoDestinado;
	}
	
	@Column(name = "atividade_nivel_escolar", nullable = false)
	public String getNivelEscolar() {
		return nivelEscolar;
	}
	
	public void setNivelEscolar(String nivelEscolar) {
		this.nivelEscolar = nivelEscolar;
	}
	
	@Column(name = "atividade_data_inicio", nullable = false)
	public String getData_inicio() {
		return data_inicio;
	}
	
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	
	@Column(name = "atividade_data_entrega", nullable = false)
	public String getData_entrega() {
		return data_entrega;
	}
	
	public void setData_entrega(String data_entrega) {
		this.data_entrega = data_entrega;
	}
	
	@Column(name = "atividade_descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "atividade_professor", nullable = false)
	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", materia=" + materia + ", anoDestinado=" + anoDestinado + ", nivelEscolar="
				+ nivelEscolar + ", data_inicio=" + data_inicio + ", data_entrega=" + data_entrega + ", descricao="
				+ descricao + ", professor=" + professor + "]";
	}
	
	
}
