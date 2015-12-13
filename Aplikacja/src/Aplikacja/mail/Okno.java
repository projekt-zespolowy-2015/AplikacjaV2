package Aplikacja.mail;

import javax.mail.MessagingException;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    import javax.swing.JPasswordField;
    import java.awt.BorderLayout;
    import java.awt.FlowLayout;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class Okno extends JFrame{
        public static String getPassword(){ //pobranie hasla z pola passField i zapisanie go w passsowrd.
            String password ="";
            char[] pass = passField.getPassword();
            for (int i=0; i<pass.length;  i++){
                password += pass[i];
            }
            return password;
        }

        private static JPasswordField passField;
        public Okno(){
            super("Wyœlij E-Mail");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            setLayout(new FlowLayout());
            panel.setLayout(new GridLayout(3,2));
            setSize(400,300);
            JLabel opis;
            JButton wyslij = new JButton("wyslij");
            opis = new JLabel ("Podaj haslo, aby wyslaæ e-mail");
            passField = new JPasswordField();
            panel.add(opis);
            panel.add(passField);
            panel.add(wyslij);
            wyslij.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent le) {
                      try {
                           new SendMail().send(); 
                           System.out.printf("Wys³ano e-mail!");
                          } catch (MessagingException e) {
                              System.out.printf("HASLO:"+getPassword());
                              System.out.printf("Nie wys³ano e-mail!");
                           e.printStackTrace();
                          }
                        }
                      });
            panel.setVisible(true);
            this.add(panel, BorderLayout.CENTER);
            setVisible(true);
        }
    }