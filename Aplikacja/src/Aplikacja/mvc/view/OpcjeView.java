package Aplikacja.mvc.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Aplikacja.mvc.controller.AnkietaController;
import Aplikacja.mvc.controller.OpcjeController;
import Aplikacja.mvc.controller.PytanieController;
import Aplikacja.mvc.model.AnkietaBaza;
import Aplikacja.mvc.model.Opcje;
import Aplikacja.mvc.model.PytanieBaza;

public class OpcjeView extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Object[] COLUMN_NAMES = {"ID_OPCJE", "Nazwa"};

	private OpcjeController controller;

	private JLabel lNazwa;
	private JTextField tfNazwa;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public OpcjeView() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1500, 800);

	    tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
	    table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 26));
        table.setRowHeight(table.getRowHeight() + 16);
	    table.setAutoCreateRowSorter(true);

	    JButton dodajOpcje = new JButton("Dodaj opcje");
	    dodajOpcje.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){
	    		Opcje opcje = new Opcje(
	    				tfNazwa.getText());
	    		controller.insertOpcje(opcje);
	    	}
	    });
	    
	    JButton edytujOpcje = new JButton("Edytuj opcje");
	    edytujOpcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer opcjeId = (Integer) tableModel.getValueAt(selectedRow, 0);
					Opcje pytanie = new Opcje(
							opcjeId,
		    				tfNazwa.getText());
					controller.updateOpcje(pytanie);
				}
			}
	    });

	    JButton usunOpcje = new JButton("Usuń opcje");
	    usunOpcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer opcjeId = (Integer) tableModel.getValueAt(selectedRow, 0);
					controller.deleteOpcje(opcjeId);
				}
			}
	    });
	    ///
	    JButton pytaniaPowrot = new JButton("Pytania");
	    pytaniaPowrot.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {     
	    		PytanieBaza pytanieModel = new PytanieBaza();
	    		PytanieView pytanieView = new PytanieView();
	    		new PytanieController(pytanieModel, pytanieView);
	    		pytanieView.setVisible(true);
			}
	    });
	    ///	    
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
    				tfNazwa.setText((String) tableModel.getValueAt(selectedRow, 1));
				}
			}
	    });
	    
	    JScrollPane scrollPane = new JScrollPane(table);

	    lNazwa = new JLabel("Treść");
	    tfNazwa = new JTextField(50);
	    
	    JPanel inputPanel = new JPanel();
	    inputPanel.add(lNazwa);
	    inputPanel.add(tfNazwa);
	    inputPanel.add(dodajOpcje);
	    inputPanel.add(usunOpcje);
	    inputPanel.add(edytujOpcje);
	    inputPanel.add(pytaniaPowrot);
	    
	    this.add(scrollPane, BorderLayout.CENTER);
	    this.add(inputPanel, BorderLayout.SOUTH);
	}
	
	public void setController(OpcjeController controller) {
		this.controller = controller;
	}
	
	public void refreshOpcjeList(List<Opcje> opcjeList) {
		tableModel.getDataVector().clear();
		for (Opcje opcje : opcjeList) {
			tableModel.addRow(new Object[] {opcje.getIdOpcje(), opcje.getNazwaOpcji()});
		}
	}
}

