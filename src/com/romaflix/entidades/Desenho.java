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
@Table(name = "desenhos", 
	uniqueConstraints = {
		@UniqueConstraint(
				columnNames = "codigo_catalogo", name = "uk__codigo_catalogo__desenhos"
	)},
	indexes = {
		@Index(
				name = "idx__classificacao_indicativa__desenhos", 
				columnList = "classificacao_indicativa", unique = false
	)}
)
public class Desenho {

	@Id
	@Column(name = "id_desenho", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDesenho;

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

	@Column(name = "qtd_episodios", length = 5, nullable = false)
	private Integer qtdEpisodios;

	public Desenho() {

	}

	public Desenho(String _codigoCatalogo, String _classificacaoIndicativa, String _titulo, String _resumo,
			String _anoLancamento, Integer _qtdEpisodios) {
		this.setCodigoCatalogo(_codigoCatalogo);
		this.setClassificacaoIndicativa(_classificacaoIndicativa);
		this.setTitulo(_titulo);
		this.setResumo(_resumo);
		this.setAnoLancamento(_anoLancamento);
		this.setQtdEpisodios(_qtdEpisodios);
	}

	public Integer getIdDesenho() {
		return idDesenho;
	}

	public void setIdDesenho(Integer idDesenho) {
		this.idDesenho = idDesenho;
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

	public void setQtdEpisodios(Integer _qtdEpisodios) {
		this.qtdEpisodios = _qtdEpisodios;
	}

	public Integer getQtdEpisodios() {
		return qtdEpisodios;
	}

	
	
	
}
