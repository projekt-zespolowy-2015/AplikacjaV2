package Aplikacja.mvc;
import javax.swing.*;

import Aplikacja.mvc.controller.AnkietaController;
import Aplikacja.mvc.model.AnkietaBaza;
import Aplikacja.mvc.view.AnkietaView;
import java.awt.event.*;

public class MenuAnkieta {
	public static JFrame JFRAME;
	public MenuAnkieta (){    
		JFrame frameMenu = new JFrame("Menu Ankieta");
		frameMenu.setVisible(true);
		frameMenu.setSize(500,200);
		frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frameMenu.add(panel);
		JButton nowaAnkieta = new JButton("Dodaj, edytuj lub usuñ ankietê");
		panel.add(nowaAnkieta);
		nowaAnkieta.addActionListener (new dodajNowaAnkieta());
	}
	
	static class dodajNowaAnkieta implements ActionListener {        
		public void actionPerformed (ActionEvent e) {     
			AnkietaBaza ankietaModel = new AnkietaBaza();
			AnkietaView ankietaView = new AnkietaView();
			new AnkietaController(ankietaModel, ankietaView);
			ankietaView.setVisible(true);
		}
	}   
}