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
import com.romaflix.controladores.ControladorFavorito;
import com.romaflix.controladores.ControladorFilme;
import com.romaflix.entidades.ConteudoAssistido;
import com.romaflix.entidades.Favoritos;
import com.romaflix.entidades.Filme;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.utils.Utils;

public class TelaFilmes extends JInternalFrame {

	private static final long serialVersionUID = 7985572284548465342L;
	private List<Filme> filmes = new ArrayList<>();
	private IControlador<Filme> ctrlFilmes = new ControladorFilme();
	private JTable table;
	private JTextField textTitulo;
	private JTextField textCodigoCatalogo;
	private JTextField textResumo;
	private JTextField textClassificacaoIndicativa;
	private JButton btnEditar;
	private JButton btnRemover;
	private Integer idFilmeSelecionado;
	private Filme filmeSelecionado;
	private JButton btnVisto;
	private JButton btnFavorito;

	private MaskFormatter mascaraData;
	private JFormattedTextField f_textAnoLancamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFilmes frame = new TelaFilmes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void carregarFilmes() {
		this.filmes = this.ctrlFilmes.listar(new Filme());
		DefaultTableModel dfm = new DefaultTableModel();

		dfm.addColumn("T�tulo");
		dfm.addColumn("C�digo Cat�logo");
		dfm.addColumn("Resumo");
		dfm.addColumn("Classificacao Indicativa");
		dfm.addColumn("Ano Lan�amento");
		dfm.addColumn("Id");

		for (Filme f : this.filmes) {
			dfm.addRow(new String[] { f.getTitulo(), f.getCodigoCatalogo(), f.getResumo(),
					f.getClassificacaoIndicativa(), f.getAnoLancamento(), String.valueOf(f.getIdFilme()) });
		}

		this.table.setModel(dfm);
	}

	public void obterDadosLinha() {
		int linha = table.getSelectedRow();

		textTitulo.setText(table.getValueAt(linha, 0).toString());
		textCodigoCatalogo.setText(table.getValueAt(linha, 1).toString());
		textResumo.setText(table.getValueAt(linha, 2).toString());
		textClassificacaoIndicativa.setText(table.getValueAt(linha, 3).toString());
		f_textAnoLancamento.setText(table.getValueAt(linha, 4).toString());
		idFilmeSelecionado = Integer.parseInt(table.getValueAt(linha, 5).toString());
	}

	public void limparTela() {
		textTitulo.setText("");
		textCodigoCatalogo.setText("");
		textResumo.setText("");
		textClassificacaoIndicativa.setText("");
		f_textAnoLancamento.setText("");

		btnRemover.setEnabled(false);
		btnEditar.setEnabled(false);
		btnVisto.setEnabled(false);
		btnFavorito.setEnabled(false);

	}

	public void preencherEditar() {
		filmeSelecionado = new Filme();
		filmeSelecionado.setIdFilme(idFilmeSelecionado);
		filmeSelecionado.setTitulo(textTitulo.getText());
		filmeSelecionado.setCodigoCatalogo(textCodigoCatalogo.getText());
		filmeSelecionado.setResumo(textResumo.getText());
		filmeSelecionado.setClassificacaoIndicativa(textClassificacaoIndicativa.getText());
		filmeSelecionado.setAnoLancamento(f_textAnoLancamento.getText());
	}
	
	public boolean verificarCampoVazio(Filme filme) {
		
		if (filme.getTitulo().equals("") || filme.getCodigoCatalogo().equals("")
				|| filme.getResumo().equals("") || filme.getClassificacaoIndicativa().equals("")
				|| filme.getAnoLancamento().equals("")) {
			
			return true;
		}

		return false;
	}

	public TelaFilmes() {
		setIconifiable(true);
		getContentPane().setBackground(new Color(255, 240, 245));
		getContentPane().setForeground(Color.BLACK);
		setTitle("TELA FILME");
		setClosable(true);
		setBounds(100, 100, 840, 700);
		getContentPane().setLayout(null);

		JLabel lblRomafilmes = new JLabel("Roma Filmes");
		lblRomafilmes.setForeground(Color.MAGENTA);
		lblRomafilmes.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblRomafilmes.setBounds(317, 13, 133, 25);
		getContentPane().add(lblRomafilmes);

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
				Filme filme = new Filme();
				filme.setTitulo(textTitulo.getText());
				filme.setCodigoCatalogo(textCodigoCatalogo.getText());
				filme.setResumo(textResumo.getText());
				filme.setClassificacaoIndicativa(textClassificacaoIndicativa.getText());
				filme.setAnoLancamento(f_textAnoLancamento.getText());
				
				if (!verificarCampoVazio(filme)) {
					Resposta r = ctrlFilmes.cadastrar(filme);
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
					
					carregarFilmes();
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
				if (filmeSelecionado != null) {
					Resposta r = ctrlFilmes.atualizar(filmeSelecionado);
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
				}
				carregarFilmes();
				limparTela();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(673, 51, 118, 25);
		getContentPane().add(btnEditar);

		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resposta r = ctrlFilmes.remover(new Filme(), idFilmeSelecionado);
				JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
				carregarFilmes();
				limparTela();
			}
		});
		btnRemover.setEnabled(false);
		btnRemover.setBounds(673, 156, 118, 25);
		getContentPane().add(btnRemover);

		JButton btnRecarregar = new JButton("Recarregar");
		btnRecarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				carregarFilmes();
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

		btnVisto = new JButton("Visto");
		btnVisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				preencherEditar();
				if (filmeSelecionado != null) {
					IControlador<ConteudoAssistido> ctrlCAssistido = new ControladorConteudoAssistido();
					ConteudoAssistido cAssistido = new ConteudoAssistido();
					cAssistido.setIdControleContas(Utils.idContaSelecionada);
					cAssistido.setCodigoCatalogo(filmeSelecionado.getCodigoCatalogo());

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

				if (filmeSelecionado != null) {
					IControlador<Favoritos> ctrlFavorito = new ControladorFavorito();
					Favoritos favoritos = new Favoritos();

					favoritos.setIdControleConta(Utils.idContaSelecionada);
					favoritos.setCodigoCatologo(filmeSelecionado.getCodigoCatalogo());

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

		carregarFilmes();
	}
}
