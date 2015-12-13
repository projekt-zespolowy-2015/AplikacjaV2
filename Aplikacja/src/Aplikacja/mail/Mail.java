package Aplikacja.mail;

  import java.awt.EventQueue;
    import javax.mail.MessagingException;

        public class Mail{
            @SuppressWarnings("resource")
                public Mail(){
                    EventQueue.invokeLater(new Runnable(){
                        @Override
                        public void run(){
                        new Okno();
                        }
        });
    }
    }