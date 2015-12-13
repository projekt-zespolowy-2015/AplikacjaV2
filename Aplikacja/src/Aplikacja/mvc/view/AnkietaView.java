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
import Aplikacja.mvc.controller.PytanieController;
import Aplikacja.mvc.model.Ankieta;
import Aplikacja.mvc.model.PytanieBaza;

public class AnkietaView extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Object[] COLUMN_NAMES = {"ID_ANKIETA", "Opis", "Data Od", "Data Do"};

	private AnkietaController controller;

	private JLabel lOpis, lDataOd, lDataDo;
	private JTextField tfOpis, tfDataOd, tfDataDo;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public AnkietaView() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(1500, 800);

	    tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
	    table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 26));
        table.setRowHeight(table.getRowHeight() + 16);
	    table.setAutoCreateRowSorter(true);

	    JButton dodajAnkieta = new JButton("Dodaj ankietę");
	    dodajAnkieta.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){
	    		Ankieta ankieta = new Ankieta(
	    				tfOpis.getText(),
	    				tfDataOd.getText(),
	    				tfDataDo.getText());
	    		controller.insertAnkieta(ankieta);
	    	}
	    });
	    
	    JButton edytujAnkieta = new JButton("Edytuj ankietę");
	    edytujAnkieta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer ankietaId = (Integer) tableModel.getValueAt(selectedRow, 0);
					Ankieta ankieta = new Ankieta(
							ankietaId,
		    				tfOpis.getText(),
		    				tfDataOd.getText(),
		    				tfDataDo.getText());
					controller.updateAnkieta(ankieta);
				}
			}
	    });

	    JButton usunAnkieta = new JButton("Usuń ankietę");
	    usunAnkieta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer ankietaId = (Integer) tableModel.getValueAt(selectedRow, 0);
					controller.deleteAnkieta(ankietaId);
				}
			}
	    });
	    ///
	    JButton dodajPytanie = new JButton("Dodaj pytanie");
	    dodajPytanie.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {     
				PytanieBaza pytanieModel = new PytanieBaza();
				PytanieView pytanieView = new PytanieView();
				new PytanieController(pytanieModel, pytanieView);
				pytanieView.setVisible(true);
			}
	    });
	    ///
	    JButton powrot = new JButton("Powrót");
	    powrot.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {   
	    		//
			}
	    });
	    ///
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
    				tfOpis.setText((String) tableModel.getValueAt(selectedRow, 1));
    				tfDataOd.setText((String) tableModel.getValueAt(selectedRow, 2));
    				tfDataDo.setText((String) tableModel.getValueAt(selectedRow, 3));
				}
			}
	    });
	    
	    JScrollPane scrollPane = new JScrollPane(table);

	    lOpis = new JLabel("Opis");
	    lDataOd = new JLabel("Data Od");
	    lDataDo = new JLabel("Data Do");
	    tfOpis = new JTextField(30);
	    tfDataOd = new JTextField(10);
	    tfDataDo = new JTextField(10);
	    
	    JPanel inputPanel = new JPanel();
	    inputPanel.add(lOpis);
	    inputPanel.add(tfOpis);
	    inputPanel.add(lDataOd);
	    inputPanel.add(tfDataOd);
	    inputPanel.add(lDataDo);
	    inputPanel.add(tfDataDo);
	    inputPanel.add(dodajAnkieta);
	    inputPanel.add(usunAnkieta);
	    inputPanel.add(edytujAnkieta);
	    inputPanel.add(dodajPytanie);
	    inputPanel.add(powrot);
	    
	    this.add(scrollPane, BorderLayout.CENTER);
	    this.add(inputPanel, BorderLayout.SOUTH);
	}
	
	public void setController(AnkietaController controller) {
		this.controller = controller;
	}
	
	public void refreshAnkiety(List<Ankieta> ankiety) {
		tableModel.getDataVector().clear();
		for (Ankieta ankieta : ankiety) {
			tableModel.addRow(new Object[] {ankieta.getIdAnkieta(), ankieta.getOpis(), ankieta.getDataOd(),
					ankieta.getDataDo()});
		}
	}
}

