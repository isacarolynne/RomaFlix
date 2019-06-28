package com.romaflix.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.entidades.ControleContas;
import com.romaflix.entidades.Usuario;

public class Login {
	public static String login;
	public static String senha;

	public Login(String _login, String _senha) {
		Login.login = _login;
		Login.senha = _senha;
	}

	public static boolean verificarLoginUsuario() {
		EntityManager em = ConnectionFactory.obterConexao();

		Usuario usuario = null;
		
		try {
			Query query = em.createQuery("SELECT p FROM Usuario p WHERE email = :email");
			query.setParameter("email", Login.login);

			usuario = (Usuario) query.getSingleResult();

		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		} finally {
			em.close();
		}

		if (usuario != null) {
			if (usuario.getEmail().equals(Login.login) && usuario.getSenha().equals(Login.senha)) {

				return true;
			}
		}

		return false;
	}
	
	public static boolean verificarLoginConta() {
		EntityManager em = ConnectionFactory.obterConexaoPrivada();

		ControleContas conta = null;
		
		try {
			Query query = em.createQuery("SELECT c FROM ControleContas c WHERE nome_conta = :nomeConta");
			query.setParameter("nomeConta", Login.login);

			conta = (ControleContas) query.getSingleResult();

		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		} finally {
			em.close();
		}

		if (conta != null) {
			if (conta.getNomeConta().equals(Login.login) && conta.getSenhaConta().equals(Login.senha)) {

				return true;
			}
		}

		return false;
	}
}
