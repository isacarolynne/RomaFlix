package com.romaflix.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.romaflix.controladores.ControladorConteudoAssistido;
import com.romaflix.controladores.ControladorDesenho;
import com.romaflix.controladores.ControladorFavorito;
import com.romaflix.entidades.ConteudoAssistido;
import com.romaflix.entidades.Desenho;
import com.romaflix.entidades.Favoritos;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.utils.Utils;

public class TelaDesenhos extends JInternalFrame {

	private static final long serialVersionUID = 1427154932517998249L;
	private List<Desenho> desenhos = new ArrayList<>();
	private IControlador<Desenho> ctrlDesenhos = new ControladorDesenho();
	private JTable table;
	private JTextField textTitulo;
	private JTextField textCodigoCatalogo;
	private JTextField textResumo;
	private JTextField textClassificacaoIndicativa;
	private JTextField textQuantidadeEpisodios;
	private JButton btnEditar;
	private JButton btnRemover;
	private Integer idDesenhoSelecionado;
	private Desenho desenhoSelecionado;
	private JButton btnVisto;
	private JButton btnFavorito;

	private MaskFormatter mascaraData;
	private JFormattedTextField f_textAnoLancamento;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDesenhos frame = new TelaDesenhos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void carregarDesenhos() {
		this.desenhos = this.ctrlDesenhos.listar(new Desenho());
		DefaultTableModel ddm = new DefaultTableModel();

		ddm.addColumn("T�tulo");
		ddm.addColumn("C�digo Cat�logo");
		ddm.addColumn("Resumo");
		ddm.addColumn("Classificacao Indicativa");
		ddm.addColumn("Ano Lan�amento");
		ddm.addColumn("Quatidade Episodios");
		ddm.addColumn("Id");

		for (Desenho d : this.desenhos) {
			ddm.addRow(new String[] { d.getTitulo(), d.getCodigoCatalogo(), d.getResumo(),
					d.getClassificacaoIndicativa(), d.getAnoLancamento(), String.valueOf(d.getQtdEpisodios()),
					String.valueOf(d.getIdDesenho()) });
		}

		this.table.setModel(ddm);
	}

	public void obterDadosLinha() {
		int linha = table.getSelectedRow();

		textTitulo.setText(table.getValueAt(linha, 0).toString());
		textCodigoCatalogo.setText(table.getValueAt(linha, 1).toString());
		textResumo.setText(table.getValueAt(linha, 2).toString());
		textClassificacaoIndicativa.setText(table.getValueAt(linha, 3).toString());
		f_textAnoLancamento.setText(table.getValueAt(linha, 4).toString());
		textQuantidadeEpisodios.setText(table.getValueAt(linha, 5).toString());
		idDesenhoSelecionado = Integer.parseInt(table.getValueAt(linha, 6).toString());
	}

	public void limparTela() {
		textTitulo.setText("");
		textCodigoCatalogo.setText("");
		textResumo.setText("");
		textClassificacaoIndicativa.setText("");
		f_textAnoLancamento.setText("");
		textQuantidadeEpisodios.setText("");

		btnRemover.setEnabled(false);
		btnEditar.setEnabled(false);
		btnVisto.setEnabled(false);
		btnFavorito.setEnabled(false);
	}

	public void preencherEditar() {
		desenhoSelecionado = new Desenho();
		desenhoSelecionado.setIdDesenho(idDesenhoSelecionado);
		desenhoSelecionado.setTitulo(textTitulo.getText());
		desenhoSelecionado.setCodigoCatalogo(textCodigoCatalogo.getText());
		desenhoSelecionado.setResumo(textResumo.getText());
		desenhoSelecionado.setClassificacaoIndicativa(textClassificacaoIndicativa.getText());
		desenhoSelecionado.setAnoLancamento(f_textAnoLancamento.getText());
		desenhoSelecionado.setQtdEpisodios(Integer.parseInt(textQuantidadeEpisodios.getText()));
	}
	
	public boolean verificarCampoVazio(Desenho desenho) {
		
		if (desenho.getTitulo().equals("") || desenho.getCodigoCatalogo().equals("")
				|| desenho.getResumo().equals("") || desenho.getClassificacaoIndicativa().equals("")
				|| desenho.getAnoLancamento().equals("") || String.valueOf(desenho.getQtdEpisodios()).equals("")) {
			
			return true;
		}

		return false;
	}

	public TelaDesenhos() {
		setIconifiable(true);
		getContentPane().setBackground(new Color(255, 240, 245));
		getContentPane().setForeground(Color.BLACK);
		setTitle("TELA DESENHO");
		setClosable(true);
		setBounds(100, 100, 840, 700);
		getContentPane().setLayout(null);

		JLabel lblRomadesenhos = new JLabel("Roma Desenhos");
		lblRomadesenhos.setForeground(Color.MAGENTA);
		lblRomadesenhos.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblRomadesenhos.setBounds(317, 13, 163, 25);
		getContentPane().add(lblRomadesenhos);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				obterDadosLinha();
				btnEditar.setEnabled(true);
				btnRemover.setEnabled(true);
				btnVisto.setEnabled(true);
				btnFavorito.setEnabled(true);
			}
		});
		table.setBounds(31, 266, 760, 347);
		getContentPane().add(table);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(31, 266, 760, 347);
		getContentPane().add(scrollBar);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desenho desenho = new Desenho();
				desenho.setTitulo(textTitulo.getText());
				desenho.setCodigoCatalogo(textCodigoCatalogo.getText());
				desenho.setResumo(textResumo.getText());
				desenho.setClassificacaoIndicativa(textClassificacaoIndicativa.getText());
				desenho.setAnoLancamento(f_textAnoLancamento.getText());
				if (!textQuantidadeEpisodios.getText().equals("")) {
					desenho.setQtdEpisodios(Integer.parseInt(textQuantidadeEpisodios.getText()));
				}
				
				if (!verificarCampoVazio(desenho)) {
					Resposta r = ctrlDesenhos.cadastrar(desenho);
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
					carregarDesenhos();
					limparTela();
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Preencher campos obrigatórios");
				}
				
			}
		});
		btnCadastrar.setBounds(673, 100, 118, 25);
		getContentPane().add(btnCadastrar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencherEditar();
				if (desenhoSelecionado != null) {
					Resposta r = ctrlDesenhos.atualizar(desenhoSelecionado);
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
				}
				carregarDesenhos();
				limparTela();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(673, 51, 118, 25);
		getContentPane().add(btnEditar);

		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resposta r = ctrlDesenhos.remover(new Desenho(), idDesenhoSelecionado);
				JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
				carregarDesenhos();
				limparTela();
			}
		});
		btnRemover.setEnabled(false);
		btnRemover.setBounds(673, 156, 118, 25);
		getContentPane().add(btnRemover);

		JButton btnRecarregar = new JButton("Recarregar");
		btnRecarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carregarDesenhos();
				limparTela();
			}
		});
		btnRecarregar.setBounds(673, 207, 118, 25);
		getContentPane().add(btnRecarregar);

		textTitulo = new JTextField();
		textTitulo.setBounds(90, 118, 522, 22);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);

		JLabel lblNewLabel = new JLabel("T\u00EDtulo");
		lblNewLabel.setBounds(41, 121, 37, 16);
		getContentPane().add(lblNewLabel);

		textCodigoCatalogo = new JTextField();
		textCodigoCatalogo.setBounds(144, 83, 167, 22);
		getContentPane().add(textCodigoCatalogo);
		textCodigoCatalogo.setColumns(10);

		JLabel lblCodigoCatalogo = new JLabel("Codigo Catalogo");
		lblCodigoCatalogo.setBounds(41, 86, 97, 16);
		getContentPane().add(lblCodigoCatalogo);

		JLabel lblResumo = new JLabel("Resumo");
		lblResumo.setBounds(40, 156, 56, 16);
		getContentPane().add(lblResumo);

		textResumo = new JTextField();
		textResumo.setBounds(100, 150, 522, 22);
		getContentPane().add(textResumo);
		textResumo.setColumns(10);

		JLabel lblClassificaoIndicativa = new JLabel("Classifica\u00E7\u00E3o Indicativa");
		lblClassificaoIndicativa.setBounds(341, 86, 139, 16);
		getContentPane().add(lblClassificaoIndicativa);

		textClassificacaoIndicativa = new JTextField();
		textClassificacaoIndicativa.setBounds(492, 83, 50, 22);
		getContentPane().add(textClassificacaoIndicativa);
		textClassificacaoIndicativa.setColumns(10);

		JLabel lblAnoLanamento = new JLabel("Ano Lan\u00E7amento");
		lblAnoLanamento.setBounds(41, 185, 97, 16);
		getContentPane().add(lblAnoLanamento);

		JLabel lblQuantidadeEpisodios = new JLabel("Quantidade de Episodios");
		lblQuantidadeEpisodios.setBounds(301, 185, 160, 16);
		getContentPane().add(lblQuantidadeEpisodios);

		textQuantidadeEpisodios = new JTextField();
		textQuantidadeEpisodios.setBounds(473, 182, 50, 22);
		getContentPane().add(textQuantidadeEpisodios);
		textQuantidadeEpisodios.setColumns(10);

		btnVisto = new JButton("Visto");
		btnVisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencherEditar();
				if (desenhoSelecionado != null) {
					IControlador<ConteudoAssistido> ctrlCAssistido = new ControladorConteudoAssistido();
					ConteudoAssistido cAssistido = new ConteudoAssistido();
					cAssistido.setIdControleContas(Utils.idContaSelecionada);
					cAssistido.setCodigoCatalogo(desenhoSelecionado.getCodigoCatalogo());

					Resposta r = ctrlCAssistido.cadastrar(cAssistido);
					JOptionPane.showMessageDialog(getDesktopPane(), r.getMensagem());
				}
			}
		});
		btnVisto.setEnabled(false);
		btnVisto.setBounds(694, 626, 97, 25);
		getContentPane().add(btnVisto);

		btnFavorito = new JButton("Favorito");
		btnFavorito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencherEditar();

				if (desenhoSelecionado != null) {
					IControlador<Favoritos> ctrlFavorito = new ControladorFavorito();
					Favoritos favoritos = new Favoritos();

					favoritos.setIdControleConta(Utils.idContaSelecionada);
					favoritos.setCodigoCatologo(desenhoSelecionado.getCodigoCatalogo());

					Resposta r = ctrlFavorito.cadastrar(favoritos);
					JOptionPane.showMessageDialog(getDesktopPane(), r.getMensagem());
				}
			}
		});
		btnFavorito.setEnabled(false);
		btnFavorito.setBounds(41, 626, 97, 25);
		getContentPane().add(btnFavorito);

		try {
			mascaraData = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}

		f_textAnoLancamento = new JFormattedTextField(mascaraData);
		f_textAnoLancamento.setBounds(139, 183, 126, 18);
		getContentPane().add(f_textAnoLancamento);

		carregarDesenhos();
	}
}