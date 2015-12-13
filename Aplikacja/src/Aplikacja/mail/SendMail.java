package Aplikacja.mail;

 import java.util.Properties;
    import javax.mail.Message;
    import javax.mail.MessagingException;
    import javax.mail.Session;
    import javax.mail.Transport;
    import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeMessage;
    public class SendMail {

        private static final String HOST = "smtp.gmail.com";
        private static final int PORT = 465;
        // Adres email osby która wysy³a maila
        private static final String FROM = "wybierznazwe.natalia@gmail.com"; 
        // Has³o do konta osoby która wysy³a maila podajemy w konsoli
        // Adres email osoby do której wysy³any jest mail
        private static final String TO = ("nataliaaaxd@wp.pl");
        // Temat wiadomoœci
        private static final String SUBJECT = "Java Witaj:)";
        // Treœæ wiadomoœci
        private static final String CONTENT = "Witaj œwiecie, dzia³a to czy nie ?:)";
        String password = Okno.getPassword(); //pobranie password z klasy Okno
        @SuppressWarnings("resource")
        public void send() throws MessagingException {
             Properties props = new Properties();
             props.put("mail.transport.protocol", "smtps");
             props.put("mail.smtps.auth", "true");
            // Inicjalizacja sesji
            Session mailSession = Session.getDefaultInstance(props);
             // ustawienie debagowania, jeœli nie chcesz ogl¹daæ logów to usuñ
            // linijkê poni¿ej lub zmieñ wartoœæ na false
             mailSession.setDebug(true);

             // Tworzenie wiadomoœci email
             MimeMessage message = new MimeMessage(mailSession);
             message.setSubject(SUBJECT);
            message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

            Transport transport = mailSession.getTransport();
             transport.connect(HOST, PORT, FROM, password);

             // wys³anie wiadomoœci
             transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
             transport.close();
            }
        }