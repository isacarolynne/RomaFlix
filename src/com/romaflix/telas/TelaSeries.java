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
import com.romaflix.controladores.ControladorSerie;
import com.romaflix.entidades.ConteudoAssistido;
import com.romaflix.entidades.Favoritos;
import com.romaflix.entidades.Serie;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.utils.Utils;

public class TelaSeries extends JInternalFrame {

	private static final long serialVersionUID = -3731876946846580019L;
	private List<Serie> series = new ArrayList<>();
	private IControlador<Serie> ctrlSeries = new ControladorSerie();
	private JTable table;
	private JTextField textTitulo;
	private JTextField textCodigoCatalogo;
	private JTextField textResumo;
	private JTextField textClassificacaoIndicativa;
	private JTextField textQuantidadeTemporada;
	private JButton btnEditar;
	private JButton btnRemover;
	private Integer idSerieSelecionado;
	private Serie serieSelecionada;
	private JButton btnVisto;
	private JButton btnFavorito;
	
	private MaskFormatter mascaraData;
	private JFormattedTextField f_textAnoLancamento;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSeries frame = new TelaSeries();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void carregarSeries() {
		this.series = this.ctrlSeries.listar(new Serie());
		DefaultTableModel dsm = new DefaultTableModel();

		dsm.addColumn("T�tulo");
		dsm.addColumn("C�digo Cat�logo");
		dsm.addColumn("Resumo");
		dsm.addColumn("Classificacao Indicativa");
		dsm.addColumn("Ano Lan�amento");
		dsm.addColumn("Quatidade Temporadas");
		dsm.addColumn("Id");

		for (Serie s : this.series) {
			dsm.addRow(new String[] { s.getTitulo(), s.getCodigoCatalogo(), s.getResumo(),
					s.getClassificacaoIndicativa(), s.getAnoLancamento(), String.valueOf(s.getQtdTemporadas()),
					String.valueOf(s.getIdSerie()) });
		}

		this.table.setModel(dsm);
	}

	public void obterDadosLinha() {
		int linha = table.getSelectedRow();

		textTitulo.setText(table.getValueAt(linha, 0).toString());
		textCodigoCatalogo.setText(table.getValueAt(linha, 1).toString());
		textResumo.setText(table.getValueAt(linha, 2).toString());
		textClassificacaoIndicativa.setText(table.getValueAt(linha, 3).toString());
		f_textAnoLancamento.setText(table.getValueAt(linha, 4).toString());
		textQuantidadeTemporada.setText(table.getValueAt(linha, 5).toString());
		idSerieSelecionado = Integer.parseInt(table.getValueAt(linha, 6).toString());
	}

	public void limparTela() {
		textTitulo.setText("");
		textCodigoCatalogo.setText("");
		textResumo.setText("");
		textClassificacaoIndicativa.setText("");
		f_textAnoLancamento.setText("");
		textQuantidadeTemporada.setText("");

		btnRemover.setEnabled(false);
		btnEditar.setEnabled(false);
		btnVisto.setEnabled(false);
		btnFavorito.setEnabled(false);

	}

	public void preencherEditar() {
		serieSelecionada = new Serie();
		serieSelecionada.setIdSerie(idSerieSelecionado);
		serieSelecionada.setTitulo(textTitulo.getText());
		serieSelecionada.setCodigoCatalogo(textCodigoCatalogo.getText());
		serieSelecionada.setResumo(textResumo.getText());
		serieSelecionada.setClassificacaoIndicativa(textClassificacaoIndicativa.getText());
		serieSelecionada.setAnoLancamento(f_textAnoLancamento.getText());
		serieSelecionada.setQtdTemporadas(Integer.parseInt(textQuantidadeTemporada.getText()));
	}
	
	public boolean verificarCampoVazio(Serie serie) {
		
		if (serie.getTitulo().equals("") || serie.getCodigoCatalogo().equals("")
				|| serie.getResumo().equals("") || serie.getClassificacaoIndicativa().equals("")
				|| serie.getAnoLancamento().equals("") || String.valueOf(serie.getQtdTemporadas()).equals("")) {
			
			return true;
		}

		return false;
	}

	public TelaSeries() {
		getContentPane().setBackground(new Color(255, 240, 245));
		getContentPane().setForeground(Color.BLACK);
		setTitle("TELA SERIE");
		setClosable(true);
		setBounds(100, 100, 840, 700);
		getContentPane().setLayout(null);

		JLabel lblRomaseries = new JLabel("Roma Series");
		lblRomaseries.setForeground(Color.MAGENTA);
		lblRomaseries.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblRomaseries.setBounds(317, 13, 133, 25);
		getContentPane().add(lblRomaseries);

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
				Serie serie = new Serie();
				
				serie.setTitulo(textTitulo.getText());
				serie.setCodigoCatalogo(textCodigoCatalogo.getText());
				serie.setResumo(textResumo.getText());
				serie.setClassificacaoIndicativa(textClassificacaoIndicativa.getText());
				serie.setAnoLancamento(f_textAnoLancamento.getText());
				if (!textQuantidadeTemporada.getText().equals("")) {
					serie.setQtdTemporadas(Integer.parseInt(textQuantidadeTemporada.getText()));
				}
				
				if (!verificarCampoVazio(serie)) {
					Resposta r = ctrlSeries.cadastrar(serie);
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
					
					carregarSeries();
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
				if (serieSelecionada != null) {
					Resposta r = ctrlSeries.atualizar(serieSelecionada);
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
				}
				carregarSeries();
				limparTela();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(673, 51, 118, 25);
		getContentPane().add(btnEditar);

		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resposta r = ctrlSeries.remover(new Serie(), idSerieSelecionado);
				JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
				carregarSeries();
				limparTela();
			}
		});
		btnRemover.setEnabled(false);
		btnRemover.setBounds(673, 156, 118, 25);
		getContentPane().add(btnRemover);

		JButton btnRecarregar = new JButton("Recarregar");
		btnRecarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				carregarSeries();
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

		JLabel lblQuantidadeTemporada = new JLabel("Quantidade de Temporada");
		lblQuantidadeTemporada.setBounds(301, 185, 160, 16);
		getContentPane().add(lblQuantidadeTemporada);

		textQuantidadeTemporada = new JTextField();
		textQuantidadeTemporada.setBounds(473, 182, 50, 22);
		getContentPane().add(textQuantidadeTemporada);
		textQuantidadeTemporada.setColumns(10);

		btnVisto = new JButton("Visto");
		btnVisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencherEditar();
				if (serieSelecionada != null) {
					IControlador<ConteudoAssistido> ctrlCAssistido = new ControladorConteudoAssistido();
					ConteudoAssistido cAssistido = new ConteudoAssistido();
					cAssistido.setIdControleContas(1);
					cAssistido.setCodigoCatalogo(serieSelecionada.getCodigoCatalogo());

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

				if (serieSelecionada != null) {
					IControlador<Favoritos> ctrlFavorito = new ControladorFavorito();
					Favoritos favoritos = new Favoritos();

					favoritos.setIdControleConta(Utils.idContaSelecionada);
					favoritos.setCodigoCatologo(serieSelecionada.getCodigoCatalogo());

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


		carregarSeries();
	}
}