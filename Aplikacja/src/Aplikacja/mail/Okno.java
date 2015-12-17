package Aplikacja.mail;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Scanner;
import java.util.Vector;

import Aplikacja.TestMenu;

public class Okno extends JFrame implements ItemListener {
	private static final Component TestMenu = null;
	private final JFrame panel;
	JComboBox poczta1 = new JComboBox();
	Vector dane_poczta= new Vector();
	JComboBox poczta = new JComboBox(dane_poczta);
	public static String getPassword() { // pobranie hasla z pola passField i
											// zapisanie go w passsowrd.
		String password = "";
		char[] pass = passField.getPassword();
		for (int i = 0; i < pass.length; i++) {
			password += pass[i];
		}
		return password;
	}
	public static String getTekst(){
		return textField.getText();
	}
	public Okno(JFrame panel) {
		this.panel = panel;
	}
	private static JPasswordField passField;
	private static JTextField textField;
	public Okno() {
		super("Wyślij E-Mail");
		JLabel haslo;
		JLabel content;
		JButton wyslij = new JButton("wyslij KRKR");
		haslo = new JLabel("Podaj haslo, aby wyslać e-mail");
		passField = new JPasswordField(20);
		content = new JLabel ("Podaj tresc wiadomosci");
		textField = new JTextField(40);
		JLabel akcja = new JLabel("Wyslano!");
	JLabel etykieta_poczta = new JLabel ("Do kogo: ");
	    panel = new JFrame();
		//setLayout(new FlowLayout());
		panel.setLayout(new GridLayout(8,8));
		panel.setSize(new Dimension(600, 400));
		
		panel.add(etykieta_poczta);
		panel.add(poczta);
		for (int i=0; i<20; i++)
			dane_poczta.add("Poczta"+i);
		poczta.addItemListener(this);
		//panel.add(poczta1);
		panel.add(haslo);
		panel.add(passField);
		panel.add(content);
		panel.add(textField);
		panel.add(wyslij);
		panel.setVisible(true);
		wyslij.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent le) {
				try {
					new SendMail().send();
					System.out.printf("Wysłano e-mail!");
					panel.add(akcja);
					//panel.setVisible(false);
				} catch (MessagingException e) {
					System.out.printf("HASLO:" + getPassword());
					System.out.printf("Nie wysłano e-mail!");
					e.printStackTrace();
				}
			}
		});
		
		this.add(panel, BorderLayout.CENTER);
		panel.getContentPane().removeAll();
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(false);
		panel.add(TestMenu);
		// dodajemy panel html i odświeżamy widok

	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
