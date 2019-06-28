package com.romaflix.telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.romaflix.conexao.ConnectionFactory;
import java.awt.Color;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = -2924136632759564946L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JDesktopPane desktopPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1117, 834);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmFilmes = new JMenuItem("Filmes");
		mntmFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaFilmes telaFilmes = new TelaFilmes();
				desktopPane.add(telaFilmes);
				telaFilmes.show();
			}
		});
		mnMenu.add(mntmFilmes);
		
		JMenuItem mntmSries = new JMenuItem("Series");
		mntmSries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSeries telaSeries = new TelaSeries();
				desktopPane.add(telaSeries);
				telaSeries.show();
			}
		});
		mnMenu.add(mntmSries);
		
		JMenuItem mntmDesenhos = new JMenuItem("Desenhos");
		mntmDesenhos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaDesenhos telaDesenhos = new TelaDesenhos();
				desktopPane.add(telaDesenhos);
				telaDesenhos.show();
				
			}
		});
		mnMenu.add(mntmDesenhos);
		
		JMenuItem mntmUsurios = new JMenuItem("Usuarios");
		mntmUsurios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaUsuarios telaUsuarios = new TelaUsuarios();
				desktopPane.add(telaUsuarios);
				telaUsuarios.show();
				
			}
		});
		mnMenu.add(mntmUsurios);
		
		JMenuItem mntmFavoritos = new JMenuItem("Favoritos");
		mntmFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaFavoritos telaFavoritos = new TelaFavoritos();
				desktopPane.add(telaFavoritos);
				telaFavoritos.show();
				
			}
		});
		mnMenu.add(mntmFavoritos);
		
		JMenuItem mntmAssistidos = new JMenuItem("Assistidos");
		mntmAssistidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaConteudoAssistido telaConteudoAssistido = new TelaConteudoAssistido();
				desktopPane.add(telaConteudoAssistido);
				telaConteudoAssistido.show();
			}
		});
		mnMenu.add(mntmAssistidos);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionFactory.finalizarConexao();
				
				TelaPrincipal.this.dispose();
				
				TelaControleContas telaControleContas = new TelaControleContas();
				telaControleContas.setVisible(true);
			}
		});
		menuBar.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(12, 13, 1075, 735);
		contentPane.add(desktopPane);
		
	}
}
