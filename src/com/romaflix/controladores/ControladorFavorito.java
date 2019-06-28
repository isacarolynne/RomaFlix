package com.romaflix.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.Favoritos;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorFavorito implements IControlador<Favoritos> {

	private IDAO<Favoritos> dao;

	public ControladorFavorito() {
		this.dao = new DAO<Favoritos>();
	}

	@Override
	public Resposta cadastrar(Favoritos favorito) {
		if (this.dao.cadastrar(favorito))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(Favoritos favorito) {
		if (this.dao.atualizar(favorito))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(Favoritos favorito, Integer id) {
		if (this.dao.remover(favorito, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public Favoritos buscarPor(Integer id) {
		if (id != null) {
			
			EntityManager em;

			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}

			try {
				return em.find(Favoritos.class, id);
			} catch (PersistenceException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			} finally {
				em.close();
			}
		}

		return null;
	}

	
	@SuppressWarnings("unchecked")
	public List<Favoritos> listarPorConta(Integer idControleConta) {

		EntityManager em = ConnectionFactory.obterConexaoPrivada();

		try {
			Query query = em.createQuery("SELECT f FROM Favoritos f WHERE id_controle_conta = :idControleConta");
			query.setParameter("idControleConta", idControleConta);

			return (List<Favoritos>) query.getResultList();
		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		} finally {
			em.close();
		}

		return new ArrayList<Favoritos>();
	}

	@Override
	public List<Favoritos> listar(Favoritos favorito) {
		return this.dao.listar(favorito);
	}
}
