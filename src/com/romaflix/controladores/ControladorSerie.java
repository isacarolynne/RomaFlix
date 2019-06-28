package com.romaflix.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.Serie;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorSerie implements IControlador<Serie> {
	private IDAO<Serie> dao;

	public ControladorSerie() {
		this.dao = new DAO<Serie>();
	}

	@Override
	public Resposta cadastrar(Serie serie) {
		if (this.dao.cadastrar(serie))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(Serie serie) {
		if (this.dao.atualizar(serie))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(Serie serie, Integer id) {
		if (this.dao.remover(serie, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public Serie buscarPor(Integer id) {
		if (id != null) {

			EntityManager em;
			
			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}
			
			try {
				return em.find(Serie.class, id);
			} catch (PersistenceException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			} finally {
				em.close();
			}
		}

		return null;
	}

	@Override
	public List<Serie> listar(Serie serie) {
		return this.dao.listar(serie);
	}
}
