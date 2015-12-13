package Aplikacja;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 








import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
public class LoginListener implements ActionListener {
    //G³ówna ramka programu
    private final JFrame frame;
    //Panel logowania, potrzebny do pobrania loginu i has³a
    private LoginPanel loginPanel;
 
    public void setPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }
 
    public LoginListener(JFrame frame) {
        this.frame = frame;
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
        String login = loginPanel.getName();
        String haslo = loginPanel.getPassword();
        if (UserValidator.authenticate(login, haslo)) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // panel z edytorem html
                	RamkaMenu ramka = new RamkaMenu();
                	ramka.setVisible(true);
                    // usuwamy panel logowania
                    frame.getContentPane().removeAll();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(false);
                    // dodajemy panel html i odœwie¿amy widok
                    frame.add(ramka);
                    frame.validate();
                    
                }
            });
        }
    }
}