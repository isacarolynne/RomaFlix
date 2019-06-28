package com.romaflix.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.romaflix.excecoes.CampoInvalido;
import com.romaflix.utils.Login;
import java.awt.Color;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1303705174045083796L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 658);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN ROMAFLIX");
		lblLogin.setBackground(new Color(255, 240, 245));
		lblLogin.setForeground(Color.MAGENTA);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblLogin.setBounds(244, 86, 234, 57);
		contentPane.add(lblLogin);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(229, 189, 56, 16);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setBounds(229, 218, 273, 43);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String email = txtEmail.getText(), senha = String.valueOf(txtSenha.getPassword());
				
				Login.login = email;
				Login.senha = senha;
				
				if((Login.login.trim().equals("") || Login.login == null) ||
						(Login.senha.equals("") || Login.senha == null)) {
					try {
						throw new CampoInvalido("Campo Inválido");
					} catch (CampoInvalido e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				
				if (Login.verificarLoginUsuario()) {
					TelaLogin.this.dispose();
					TelaControleContas telaControleContas = new TelaControleContas();
					telaControleContas.setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(contentPane, "Usuário ou Senha inválidos");
				}

			}
		});
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(229, 283, 56, 16);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSenha.setBounds(229, 312, 273, 43);
		contentPane.add(txtSenha);
		btnLogin.setBounds(306, 406, 123, 43);
		contentPane.add(btnLogin);
	}
}
