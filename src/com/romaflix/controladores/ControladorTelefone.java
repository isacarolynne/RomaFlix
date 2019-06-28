package com.romaflix.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.Telefone;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorTelefone implements IControlador<Telefone> {
	private IDAO<Telefone> dao;

	public ControladorTelefone() {
		this.dao = new DAO<Telefone>();
	}

	@Override
	public Resposta cadastrar(Telefone telefone) {
		if (this.dao.cadastrar(telefone))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(Telefone telefone) {
		if (this.dao.atualizar(telefone))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(Telefone telefone, Integer id) {
		if (this.dao.remover(telefone, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public Telefone buscarPor(Integer id) {
		if (id != null) {

			EntityManager em;

			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}

			try {
				return em.find(Telefone.class, id);
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
	public List<Telefone> listar(Telefone telefone) {
		return this.dao.listar(telefone);
	}
}
