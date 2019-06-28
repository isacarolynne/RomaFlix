package com.romaflix.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conteudos_assistidos")
public class ConteudoAssistido {
	@Id
	@Column(name = "id_conteudo_assistido", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConteudoAssistido;
	
//	@ManyToOne(cascade = { CascadeType.REFRESH })
//	@JoinColumn(name = "id_controle_conta", foreignKey = @ForeignKey(name = "fk__id_controle_conta__conteudos_assistidos"))
	@Column(name = "id_controle_conta")
	private Integer idControleContas;
	
	@Column(name = "codigo_catalogo", length = 8, nullable = false)
	private String codigoCatalogo;

	
	
	public ConteudoAssistido() {
	}

	public ConteudoAssistido(Integer _idControleContas, String _codigoCatalogo) {
		this.setIdControleContas(_idControleContas);
		this.setCodigoCatalogo(_codigoCatalogo);
	}

	public Integer getIdConteudoAssistido() {
		return idConteudoAssistido;
	}

	public void setIdConteudoAssistido(Integer idConteudoAssistido) {
		this.idConteudoAssistido = idConteudoAssistido;
	}

	public Integer getIdControleContas() {
		return idControleContas;
	}

	public void setIdControleContas(Integer idControleContas) {
		this.idControleContas = idControleContas;
	}

	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}

	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}
	
	
}
