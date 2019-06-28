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
@Table(name = "series", 
	uniqueConstraints = {
		@UniqueConstraint(
				columnNames = "codigo_catalogo", name = "uk__codigo_catalogo__series"
	)},
	indexes = {
		@Index(
				name = "idx__classificacao_indicativa__series", 
				columnList = "classificacao_indicativa", unique = false
	)}
)
public class Serie {

	@Id
	@Column(name = "id_serie", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSerie;

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

	@Column(name = "qtd_temporadas", nullable = false)
	private Integer qtdTemporadas;

	public Serie() {

	}

	public Serie(String _codigoCatalogo, String _classificacaoIndicativa, String _titulo, String _resumo,
			String _anoLancamento, Integer _qtdTemporadas) {
		this.setCodigoCatalogo(_codigoCatalogo);
		this.setClassificacaoIndicativa(_classificacaoIndicativa);
		this.setTitulo(_titulo);
		this.setResumo(_resumo);
		this.setAnoLancamento(_anoLancamento);
		this.setQtdTemporadas(_qtdTemporadas);
	}

	public Integer getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(Integer _idSerie) {
		this.idSerie = _idSerie;
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

	public Integer getQtdTemporadas() {
		return qtdTemporadas;
	}

	public void setQtdTemporadas(Integer _qtdTemporadas) {
		this.qtdTemporadas = _qtdTemporadas;
	}

}
