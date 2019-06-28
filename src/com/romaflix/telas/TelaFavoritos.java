package com.romaflix.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.romaflix.controladores.ControladorFavorito;
import com.romaflix.entidades.Favoritos;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.utils.Utils;

public class TelaFavoritos extends JInternalFrame {

	private static final long serialVersionUID = 7561318497585282075L;
	private JTable table;
	private List<Favoritos> favoritos = new ArrayList<>();
	private IControlador<Favoritos> ctrlFavoritos = new ControladorFavorito();
	private JButton btnRemover;
	private Favoritos favoritoSelecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFavoritos frame = new TelaFavoritos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void carregarFavoritos() {
		favoritos = ((ControladorFavorito)ctrlFavoritos).listarPorConta(Utils.idContaSelecionada);
		DefaultTableModel dfm = new DefaultTableModel();

		dfm.addColumn("Id Favorito");
		dfm.addColumn("Id Controle Conta");
		dfm.addColumn("Codigo Catalogo");

		for (Favoritos f : this.favoritos) {
			dfm.addRow(new String[] { String.valueOf(f.getIdFavorito()), String.valueOf(f.getIdControleConta()), f.getCodigoCatologo() });
		}

		this.table.setModel(dfm);
	}
	
	public void obterDadosLinha() {
		int linha = table.getSelectedRow();
		
		favoritoSelecionado = new Favoritos();
		favoritoSelecionado.setIdFavorito(Integer.parseInt(table.getValueAt(linha, 0).toString()));
		favoritoSelecionado.setIdControleConta(Utils.idContaSelecionada);
		favoritoSelecionado.setCodigoCatologo(table.getValueAt(linha, 2).toString());
	}
	
	public TelaFavoritos() {
		getContentPane().setBackground(new Color(255, 240, 245));
		setIconifiable(true);
		setClosable(true);
		setTitle("Tela de Favoritos");
		setBounds(100, 100, 552, 437);
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				obterDadosLinha();
				btnRemover.setEnabled(true);
				
			}
		});
		table.setBounds(10, 101, 516, 205);
		getContentPane().add(table);
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (favoritoSelecionado != null) {
					Resposta r = ctrlFavoritos.remover(new Favoritos(), favoritoSelecionado.getIdFavorito());
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
					
					carregarFavoritos();
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Selecionar um favorito para remover");
				}
			}
		});
		btnRemover.setEnabled(false);
		btnRemover.setBounds(225, 317, 89, 23);
		getContentPane().add(btnRemover);
		
		JLabel lblRomaFavoritos = new JLabel("ROMA FAVORITOS");
		lblRomaFavoritos.setForeground(Color.MAGENTA);
		lblRomaFavoritos.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblRomaFavoritos.setBounds(159, 32, 215, 42);
		getContentPane().add(lblRomaFavoritos);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(10, 101, 516, 205);
		getContentPane().add(scrollBar);

		
		carregarFavoritos();
	}

}
