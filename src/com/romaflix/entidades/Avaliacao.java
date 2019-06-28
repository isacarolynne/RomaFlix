package com.romaflix.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacoes", indexes = {
		@Index(
				name = "idx__codigo_catalogo__avaliacoes", 
				columnList = "codigo_catalogo", unique = false
	)})
public class Avaliacao {

	@Id
	@Column(name = "id_avaliacao", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAvaliacao;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "id_controle_conta", foreignKey = @ForeignKey(name = "fk__id_controle_contas__avaliacoes"))
	private ControleContas controleContas;

	@Column(name = "codigo_catalogo", length = 8, nullable = false)
	private String codigoCatalogo;

	@Column(nullable = true)
	private Integer avaliacao;
	
	public Avaliacao() {
		
	}

	public Avaliacao(ControleContas _controleContas, Integer _avaliacao) {
		this.setControleContas(_controleContas);
		this.setAvaliacao(_avaliacao);
	}

	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}
	
	public void setCodigoCatalogo(String _codigoCatalogo) {
		this.codigoCatalogo = _codigoCatalogo;
	}
	
	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Integer _idAvaliacao) {
		this.idAvaliacao = _idAvaliacao;
	}

	public ControleContas getControleContas() {
		return controleContas;
	}

	public void setControleContas(ControleContas _controleContas) {
		this.controleContas = _controleContas;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer _avaliacao) {
		this.avaliacao = _avaliacao;
	}
	

}
