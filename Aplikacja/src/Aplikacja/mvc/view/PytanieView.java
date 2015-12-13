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
import Aplikacja.mvc.model.OpcjeBaza;
import Aplikacja.mvc.model.Pytanie;
import Aplikacja.mvc.model.PytanieBaza;

public class PytanieView extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Object[] COLUMN_NAMES = {"ID_PYTANIE", "Treść"};

	private PytanieController controller;

	private JLabel lTresc;
	private JTextField tfTresc;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public PytanieView() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1500, 800);

	    tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
	    table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 26));
        table.setRowHeight(table.getRowHeight() + 16);
	    table.setAutoCreateRowSorter(true);

	    JButton dodajPytanie = new JButton("Dodaj pytanie");
	    dodajPytanie.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){
	    		Pytanie pytanie = new Pytanie(
	    				tfTresc.getText());
	    		controller.insertPytanie(pytanie);
	    	}
	    });
	    
	    JButton edytujPytanie = new JButton("Edytuj pytanie");
	    edytujPytanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer pytanieId = (Integer) tableModel.getValueAt(selectedRow, 0);
					Pytanie pytanie = new Pytanie(
							pytanieId,
		    				tfTresc.getText());
					controller.updatePytanie(pytanie);
				}
			}
	    });

	    JButton usunPytanie = new JButton("Usuń pytanie");
	    usunPytanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer pytanieId = (Integer) tableModel.getValueAt(selectedRow, 0);
					controller.deletePytanie(pytanieId);
				}
			}
	    });
	    ///
	    JButton dodajOpcje = new JButton("Dodaj opcje");
	    dodajOpcje.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {     
	    		OpcjeBaza opcjeModel = new OpcjeBaza();
	    		OpcjeView opcjeView = new OpcjeView();
	    		new OpcjeController(opcjeModel, opcjeView);
	    		opcjeView.setVisible(true);
			}
	    });
	    ///
	    JButton ankietaPowrot = new JButton("Ankiety");
	    ankietaPowrot.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {     
	    		AnkietaBaza ankietaModel = new AnkietaBaza();
	    		AnkietaView ankietaView = new AnkietaView();
	    		new AnkietaController(ankietaModel, ankietaView);
	    		ankietaView.setVisible(true);
			}
	    });
	    ///
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
    				tfTresc.setText((String) tableModel.getValueAt(selectedRow, 1));
				}
			}
	    });
	    
	    JScrollPane scrollPane = new JScrollPane(table);

	    lTresc = new JLabel("Treść");
	    tfTresc = new JTextField(50);
	    
	    JPanel inputPanel = new JPanel();
	    inputPanel.add(lTresc);
	    inputPanel.add(tfTresc);
	    inputPanel.add(dodajPytanie);
	    inputPanel.add(usunPytanie);
	    inputPanel.add(edytujPytanie);
	    inputPanel.add(dodajOpcje);
	    inputPanel.add(ankietaPowrot);
	    
	    this.add(scrollPane, BorderLayout.CENTER);
	    this.add(inputPanel, BorderLayout.SOUTH);
	}
	
	public void setController(PytanieController controller) {
		this.controller = controller;
	}
	
	public void refreshPytania(List<Pytanie> pytania) {
		tableModel.getDataVector().clear();
		for (Pytanie pytanie : pytania) {
			tableModel.addRow(new Object[] {pytanie.getIdPytanie(), pytanie.getTrescPytania()});
		}
	}
}

