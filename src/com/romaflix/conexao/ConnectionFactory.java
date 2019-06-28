package com.romaflix.conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.romaflix.utils.Login;

public class ConnectionFactory {

	private static EntityManagerFactory EMF;
	private static Integer usuarioLogado = 0;

	public static EntityManager obterConexao() {
		EMF = Persistence.createEntityManagerFactory("chefinhoPU"); //chefinhoPU
		

		return EMF.createEntityManager();
	}

	public static EntityManager obterConexaoPrivada() {
		if (ConnectionFactory.usuarioLogado != 0) {
			EMF = Persistence.createEntityManagerFactory("chefaoPU");
			
			
			return EMF.createEntityManager();
		} else {
			if (Login.verificarLoginUsuario()) {
				EMF = Persistence.createEntityManagerFactory("chefaoPU");
				
				ConnectionFactory.usuarioLogado = 1;
				
				
				return EMF.createEntityManager();
			} 
		}
		
		return null;
	}

	public static void finalizarConexao() {
		EMF.close();
	}

}
