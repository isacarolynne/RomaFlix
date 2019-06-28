package com.romaflix.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.Usuario;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorUsuario implements IControlador<Usuario> {
	private IDAO<Usuario> dao;

	public ControladorUsuario() {
		this.dao = new DAO<Usuario>();
	}

	@Override
	public Resposta cadastrar(Usuario usuario) {
		if (this.dao.cadastrar(usuario))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(Usuario usuario) {
		if (this.dao.atualizar(usuario))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(Usuario usuario, Integer id) {
		if (this.dao.remover(usuario, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public Usuario buscarPor(Integer id) {
		if (id != null) {

			EntityManager em;
			
			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}

			try {
				return em.find(Usuario.class, id);
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
	public List<Usuario> listar(Usuario usuario) {
		return this.dao.listar(usuario);
	}
}
