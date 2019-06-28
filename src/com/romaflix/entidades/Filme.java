package com.romaflix.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "filmes", 
	uniqueConstraints = {
		@UniqueConstraint(
				columnNames = "codigo_catalogo", name = "uk__codigo_catalogo__filmes"
	)},
	indexes = {
		@Index(
				name = "idx__classificacao_indicativa__filmes", 
				columnList = "classificacao_indicativa", unique = false
	)}
)
public class Filme {
	
	@Id
	@Column(name = "id_filme", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFilme;
	
	@Column(name = "codigo_catalogo", length = 8, nullable = false)
	private String codigoCatalogo;

	@Column(name = "classificacao_indicativa", length = 2, nullable = false)
	private String classificacaoIndicativa;

	@Column(length = 50, nullable = false)
	private String titulo;

	@Column(length = 140, nullable = false)
	private String resumo;

	@Column(name = "ano_lancamento", length = 12, nullable = false)
	private String anoLancamento;

	public Filme() {

	}

	public Filme(String _codigoCatalogo, String _classificacaoIndicativa, String _titulo, String _resumo,
			String _anoLancamento) {
		this.setCodigoCatalogo(_codigoCatalogo);
		this.setClassificacaoIndicativa(_classificacaoIndicativa);
		this.setTitulo(_titulo);
		this.setResumo(_resumo);
		this.setAnoLancamento(_anoLancamento);
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}

	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}

	public void setCodigoCatalogo(String _codigoCatalogo) {
		this.codigoCatalogo = _codigoCatalogo;
	}

	public String getClassificacaoIndicativa() {
		return classificacaoIndicativa;
	}

	public void setClassificacaoIndicativa(String _classificacaoIndicativa) {
		this.classificacaoIndicativa = _classificacaoIndicativa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String _titulo) {
		this.titulo = _titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String _resumo) {
		this.resumo = _resumo;
	}

	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String _anoLancamento) {
		this.anoLancamento = _anoLancamento;
	}

}
