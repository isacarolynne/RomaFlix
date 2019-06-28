package com.romaflix.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	
	public static Integer idContaSelecionada;
	public static Integer ehContaConvidado = 0;
	
	public static String criptografarSenha(String senha)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String senhaCriptografada = null;
		if (!(senha.trim().equals("") || senha.trim().equals(null))) {
			
			MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
			byte messageDigestSenha[] = algoritmo.digest(senha.getBytes("UTF-8"));
			StringBuilder hexStringSenha = new StringBuilder();
			for (byte b : messageDigestSenha) {
				hexStringSenha.append(String.format("0%2X", 0xFF & b));
			}
			senhaCriptografada = hexStringSenha.toString();
		}
		
		return senhaCriptografada;
	}

}
