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

import com.romaflix.controladores.ControladorConteudoAssistido;
import com.romaflix.entidades.ConteudoAssistido;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.utils.Utils;

public class TelaConteudoAssistido extends JInternalFrame {
	
	private static final long serialVersionUID = -8387445373856432647L;
	private JTable table;
	private List<ConteudoAssistido> conteudosAssistido = new ArrayList<>();
	private IControlador<ConteudoAssistido> ctrlConteudosAssitido = new ControladorConteudoAssistido();
	private ConteudoAssistido conteudoAssistidoSelecionado;
	private JButton btnRemover;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConteudoAssistido frame = new TelaConteudoAssistido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void carregarConteudosAssistidos() {
		conteudosAssistido = ((ControladorConteudoAssistido)ctrlConteudosAssitido).listarPorConta(Utils.idContaSelecionada);
		DefaultTableModel dfm = new DefaultTableModel();

		dfm.addColumn("Id Conteudo Assistido");
		dfm.addColumn("Id Controle Conta");
		dfm.addColumn("Codigo Catalogo");

		for (ConteudoAssistido ca : this.conteudosAssistido) {
			dfm.addRow(new String[] { String.valueOf(ca.getIdConteudoAssistido()), String.valueOf(ca.getIdControleContas()), ca.getCodigoCatalogo() });
		}

		this.table.setModel(dfm);
	}
	
	public void obterDadosLinha() {
		int linha = table.getSelectedRow();
		
		conteudoAssistidoSelecionado = new ConteudoAssistido();
		conteudoAssistidoSelecionado.setIdConteudoAssistido(Integer.parseInt(table.getValueAt(linha, 0).toString()));
	}

	public TelaConteudoAssistido() {
		getContentPane().setBackground(new Color(255, 240, 245));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 614, 518);
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				obterDadosLinha();
				btnRemover.setEnabled(true);
				
			}
		});
		table.setBounds(10, 158, 578, 215);
		getContentPane().add(table);
		
		btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (conteudoAssistidoSelecionado != null) {
					Resposta r = ctrlConteudosAssitido.remover(new ConteudoAssistido(), conteudoAssistidoSelecionado.getIdConteudoAssistido());
					JOptionPane.showMessageDialog(getContentPane(), r.getMensagem());
					
					carregarConteudosAssistidos();
					
					btnRemover.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Selecionar um filme assistido para remover");
				}
			}
		});
		btnRemover.setBounds(253, 384, 89, 23);
		getContentPane().add(btnRemover);
		
		JLabel lblRomaAssistidos = new JLabel("ROMA ASSISTIDOS");
		lblRomaAssistidos.setForeground(Color.MAGENTA);
		lblRomaAssistidos.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblRomaAssistidos.setBounds(189, 68, 215, 42);
		getContentPane().add(lblRomaAssistidos);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(10, 158, 578, 215);
		getContentPane().add(scrollBar);
		
		carregarConteudosAssistidos();
	}

}
