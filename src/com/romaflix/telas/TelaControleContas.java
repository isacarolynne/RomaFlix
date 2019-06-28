package com.romaflix.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.romaflix.controladores.ControladorControleContas;
import com.romaflix.entidades.ControleContas;
import com.romaflix.interfaces.IControlador;
import com.romaflix.utils.Login;
import com.romaflix.utils.Utils;
import java.awt.Color;

public class TelaControleContas extends JFrame {

	private static final long serialVersionUID = -7425767832089060543L;
	
	private List<ControleContas> contas = new ArrayList<>();
	private IControlador<ControleContas> ctrlContas = new ControladorControleContas();
	private JPanel contentPane;
	private JTable table;
	private JPasswordField textSenha;
	private String nomeContaSelecionada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaControleContas frame = new TelaControleContas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void obterDadosLinha() {
		int linha = table.getSelectedRow();
		
		Utils.idContaSelecionada = Integer.parseInt(table.getValueAt(linha, 0).toString());
		nomeContaSelecionada = table.getValueAt(linha, 1).toString();
		Utils.ehContaConvidado = Integer.parseInt(table.getValueAt(linha, 2).toString());
	}
	
	public void carregarContas() {
		this.contas = this.ctrlContas.listar(new ControleContas());
		DefaultTableModel dfm = new DefaultTableModel();
		
		dfm.addColumn("Id");
		dfm.addColumn("Nome Conta");
		dfm.addColumn("Conta Convidado?");
		
		for (ControleContas c : this.contas) {
			dfm.addRow(new String[] { String.valueOf(c.getIdControleConta()), c.getNomeConta(), String.valueOf(c.getEhContaConvidado()) });
		}
		
		this.table.setModel(dfm);
	}

	public TelaControleContas() {
		setTitle("Controle de Contas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 627);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaControleContas.this.dispose();
				
				TelaLogin telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
			}
		});
		btnVoltar.setBounds(306, 487, 144, 34);
		contentPane.add(btnVoltar);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (Utils.ehContaConvidado != 0) {
					TelaControleContas.this.dispose();
					
					TelaPrincipal telaPrincipal = new TelaPrincipal();
					telaPrincipal.setVisible(true);
					
				} else {
					String senha = String.valueOf(textSenha.getPassword());
					
					Login.login = nomeContaSelecionada;
					Login.senha = senha;
					
					if (Login.verificarLoginConta()) {
						TelaControleContas.this.dispose();
						
						TelaPrincipal telaPrincipal = new TelaPrincipal();
						telaPrincipal.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Nome da Conta ou Senha inválidos");
					}
				}
			}
		});
		btnEntrar.setBounds(333, 394, 87, 23);
		contentPane.add(btnEntrar);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(240, 323, 120, 14);
		contentPane.add(lblSenha);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				obterDadosLinha();
				
				if (Utils.ehContaConvidado != 0) {
					lblSenha.setVisible(false);
					textSenha.setVisible(false);
				} else {
					lblSenha.setVisible(true);
					textSenha.setVisible(true);
				}
			}
		});
		table.setBounds(133, 98, 490, 167);
		contentPane.add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(133, 98, 490, 167);
		contentPane.add(scrollBar);
		
		textSenha = new JPasswordField();
		textSenha.setBounds(240, 347, 287, 34);
		contentPane.add(textSenha);
		
		JLabel lblContas = new JLabel("Selecione uma conta");
		lblContas.setHorizontalAlignment(SwingConstants.LEFT);
		lblContas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContas.setBounds(133, 67, 206, 25);
		contentPane.add(lblContas);
		
		carregarContas();
	}
}
