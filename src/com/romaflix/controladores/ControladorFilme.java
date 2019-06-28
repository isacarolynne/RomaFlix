package com.romaflix.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.Filme;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorFilme implements IControlador<Filme> {
	private IDAO<Filme> dao;

	public ControladorFilme() {
		this.dao = new DAO<Filme>();
	}

	@Override
	public Resposta cadastrar(Filme filme) {
		if (this.dao.cadastrar(filme))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(Filme filme) {
		if (this.dao.atualizar(filme))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(Filme filme, Integer id) {
		if (this.dao.remover(filme, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public Filme buscarPor(Integer id) {
		if (id != null) {
			
			EntityManager em;

			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}
			
			try {
				return em.find(Filme.class, id);
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
	public List<Filme> listar(Filme filme) {
		return this.dao.listar(filme);
	}
}
