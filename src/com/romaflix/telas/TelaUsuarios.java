package com.romaflix.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.romaflix.controladores.ControladorEndereco;
import com.romaflix.controladores.ControladorTelefone;
import com.romaflix.controladores.ControladorUsuario;
import com.romaflix.entidades.Endereco;
import com.romaflix.entidades.Telefone;
import com.romaflix.entidades.Usuario;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;

public class TelaUsuarios extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1912954040066711479L;

	private List<Usuario> usuario = new ArrayList<>();
	private IControlador<Usuario> ctrlUsuario = new ControladorUsuario();

	private List<Endereco> endereco = new ArrayList<>();
	private IControlador<Endereco> ctrlEndereco = new ControladorEndereco();

	private List<Telefone> telefone = new ArrayList<>();
	private IControlador<Telefone> ctrlTelefone = new ControladorTelefone();

	private JTable table;
	private JTable tableUsuario;
	private JTextField textEmail;
	private JTable tableEndereco;
	private JTextField textCep;
	private JTextField textCidade;
	private JTextField textNumero;
	private JTextField textComplemento;
	private JTextField textEstado;
	private JTextField textBairro;
	private JTextField textRua;
	private JTable tableTelefone;
	private JTextField textTelefone;
	private Integer idUsuarioSelecionado;
	private Integer idEnderecoSelecionado;
	private Integer idTelefoneSelecionado;
	private Usuario usuarioSelecionado;
	private Endereco enderecoSelecionado;
	private Telefone telefoneSelecionado;
	private JButton btnAtualizarUsuario;
	private JLabel lblEmail;
	private JLabel lblUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuarios frame = new TelaUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void carregarUsuario() {
		this.usuario = this.ctrlUsuario.listar(new Usuario());
		DefaultTableModel dum = new DefaultTableModel();

		dum.addColumn("Email");
		dum.addColumn("Id");

		for (Usuario u : this.usuario) {
			dum.addRow(new String[] { u.getEmail(), u.getSenha(), String.valueOf(u.getIdUsuario()) });
		}

		this.table.setModel(dum);
	}

	public void carregarEndereco() {
		this.endereco = this.ctrlEndereco.listar(new Endereco());
		DefaultTableModel dem = new DefaultTableModel();

		dem.addColumn("Numero");
		dem.addColumn("Rua");
		dem.addColumn("Bairro");
		dem.addColumn("CEP");
		dem.addColumn("Cidade");
		dem.addColumn("Complemento");
		dem.addColumn("Estado");
		dem.addColumn("Id");

		for (Endereco e : this.endereco) {
			dem.addRow(new String[] { e.getNumero(), e.getRua(), e.getBairro(), e.getCep(), e.getCidade(),
					e.getComplemento(), e.getEstado(), String.valueOf(e.getIdEndereco()) });
		}

		this.table.setModel(dem);
	}

	public void carregarTelefone() {

		this.telefone = this.ctrlTelefone.listar(new Telefone());
		DefaultTableModel dtm = new DefaultTableModel();

		dtm.addColumn("Telefone");
		dtm.addColumn("Id");

		for (Telefone t : this.telefone) {
			dtm.addRow(new String[] { t.getNumero(), String.valueOf(t.getIdTelefone()) });
		}

		this.table.setModel(dtm);
	}

	/**
	 * 
	 * Create the frame.
	 */
	public void obterDadosLinha() {
		int linha = table.getSelectedRow();

		textEmail.setText(table.getValueAt(linha, 0).toString());
		idUsuarioSelecionado = Integer.parseInt(table.getValueAt(linha, 1).toString());

//		textNumero.setText(table.getValueAt(linha, 0).toString());
//		textRua.setText(table.getValueAt(linha, 1).toString());
//		textBairro.setText(table.getValueAt(linha, 2).toString());
//		textCep.setText(table.getValueAt(linha, 3).toString());
//		textCidade.setText(table.getValueAt(linha, 4).toString());
//		textComplemento.setText(table.getValueAt(linha, 5).toString());
//		textEstado.setText(table.getValueAt(linha, 6).toString());
//		idEnderecoSelecionado = Integer.parseInt(table.getValueAt(linha, 7).toString());
//
//		textTelefone.setText(table.getValueAt(linha, 0).toString());
//		idTelefoneSelecionado = Integer.parseInt(table.getValueAt(linha, 1).toString());

	}

	public void limparTela() {
		textEmail.setText("");
		textNumero.setText("");
		textRua.setText("");
		textBairro.setText("");
		textCep.setText("");
		textCidade.setText("");
		textComplemento.setText("");
		textEstado.setText("");
		textTelefone.setText("");
		
		
	}
	
	public void preencherEditarUsuario() {
		usuarioSelecionado = new Usuario();
		usuarioSelecionado.setIdUsuario(idUsuarioSelecionado);;
		usuarioSelecionado.setEmail(textEmail.getText());
		
	}
	public void preencherEditarEndereco() {
		enderecoSelecionado = new Endereco();
		enderecoSelecionado.setIdEndereco(idEnderecoSelecionado);
		enderecoSelecionado.setNumero(textNumero.getText());
		enderecoSelecionado.setRua(textRua.getText());
		enderecoSelecionado.setBairro(textBairro.getText());
		enderecoSelecionado.setCep(textCep.getText());
		enderecoSelecionado.setCidade(textCidade.getText());
		enderecoSelecionado.setComplemento(textComplemento.getText());
		enderecoSelecionado.setEstado(textEstado.getText());
		
			}
	public void preencherEditarTelefone() {
		telefoneSelecionado.setIdTelefone(idTelefoneSelecionado);
		telefoneSelecionado.setNumero(textNumero.getText());
	}
	
public boolean verificarCampoVazio(Endereco endereco) {
		
		if (endereco.getNumero().equals("") || endereco.getRua().equals("") 
				|| endereco.getBairro().equals("") || endereco.getCidade().equals("")
				|| endereco.getEstado().equals("")) {
			
			return true;
		}

		return false;
	}

	public TelaUsuarios() {
		setClosable(true);
		getContentPane().setBackground(new Color(255, 240, 245));
		getContentPane().setLayout(null);

		tableUsuario = new JTable();
		tableUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				obterDadosLinha();

			}
		});
		tableUsuario.setBounds(10, 28, 295, 114);
		getContentPane().add(tableUsuario);

		textEmail = new JTextField();
		textEmail.setBounds(393, 67, 189, 20);
		getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		

		btnAtualizarUsuario = new JButton("Atualizar");
		btnAtualizarUsuario.setBounds(648, 66, 89, 23);
		btnAtualizarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencherEditarUsuario();
				Usuario usuario = new Usuario();
				usuario.setEmail(textEmail.getText());
				if (usuarioSelecionado != null) {
					Resposta r = ctrlUsuario.atualizar(usuarioSelecionado);
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
				}
				carregarUsuario();
				limparTela();
			}
		});
		getContentPane().add(btnAtualizarUsuario);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(343, 70, 46, 14);
		getContentPane().add(lblEmail);

		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(10, 3, 56, 14);
		getContentPane().add(lblUsuarios);

		tableEndereco = new JTable();
		tableEndereco.setBounds(10, 361, 632, 157);
		getContentPane().add(tableEndereco);

		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setBounds(10, 336, 66, 14);
		getContentPane().add(lblEndereco);

		JButton btnAtualizarEndereco = new JButton("Atualizar");
		btnAtualizarEndereco.setBounds(648, 422, 89, 23);
		getContentPane().add(btnAtualizarEndereco);

		JButton btnCadastrarEndereco = new JButton("Cadastrar");
		btnCadastrarEndereco.setBounds(648, 465, 89, 23);
		getContentPane().add(btnCadastrarEndereco);

		textCep = new JTextField();
		textCep.setBounds(393, 567, 97, 22);
		getContentPane().add(textCep);
		textCep.setColumns(10);

		textCidade = new JTextField();
		textCidade.setBounds(68, 568, 86, 20);
		getContentPane().add(textCidade);
		textCidade.setColumns(10);

		textNumero = new JTextField();
		textNumero.setBounds(74, 534, 66, 20);
		getContentPane().add(textNumero);
		textNumero.setColumns(10);

		textComplemento = new JTextField();
		textComplemento.setBounds(258, 568, 100, 20);
		getContentPane().add(textComplemento);
		textComplemento.setColumns(10);

		textEstado = new JTextField();
		textEstado.setBounds(561, 568, 56, 20);
		getContentPane().add(textEstado);
		textEstado.setColumns(10);

		textBairro = new JTextField();
		textBairro.setBounds(411, 532, 224, 20);
		getContentPane().add(textBairro);
		textBairro.setColumns(10);

		textRua = new JTextField();
		textRua.setBounds(173, 534, 185, 20);
		getContentPane().add(textRua);
		textRua.setColumns(10);

		tableTelefone = new JTable();
		tableTelefone.setBounds(10, 180, 295, 114);
		getContentPane().add(tableTelefone);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(20, 534, 56, 16);
		getContentPane().add(lblNumero);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(367, 570, 22, 16);
		getContentPane().add(lblCep);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(370, 534, 34, 16);
		getContentPane().add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(20, 570, 46, 16);
		getContentPane().add(lblCidade);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(162, 570, 86, 16);
		getContentPane().add(lblComplemento);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(502, 570, 47, 16);
		getContentPane().add(lblEstado);

		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(145, 534, 34, 16);
		getContentPane().add(lblRua);

		JLabel lblTelefones = new JLabel("Telefones");
		lblTelefones.setBounds(10, 151, 56, 16);
		getContentPane().add(lblTelefones);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(343, 216, 56, 16);
		getContentPane().add(lblTelefone);

		textTelefone = new JTextField();
		textTelefone.setBounds(404, 213, 178, 22);
		getContentPane().add(textTelefone);
		textTelefone.setColumns(10);

		JButton btntualizarTelefone = new JButton("Atualizar");
		btntualizarTelefone.setBounds(640, 200, 97, 25);
		getContentPane().add(btntualizarTelefone);

		JButton btnCadastrarTelefone = new JButton("Cadastrar");
		btnCadastrarTelefone.setBounds(640, 238, 97, 25);
		getContentPane().add(btnCadastrarTelefone);
		setTitle("Tela de Usuarios");
		setBounds(100, 100, 765, 662);

	}
}
