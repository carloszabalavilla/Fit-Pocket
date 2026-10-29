package edu.fpdual.webapplication.utilities.emailsender;

import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

public class Sender {

    @Setter
    @Getter
    Properties mailProp = new Properties();
    @Setter
    @Getter
    Properties credentialProp = new Properties();

    /**
     * Build the sender class loading the properties from mail and credentials files.
     */
    public Sender() {
        try {
            mailProp.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));
            credentialProp.load(getClass().getClassLoader().getResourceAsStream("credentials.properties"));
            credentialProp.setProperty(CredentialsConstants.USER, System.getenv().getOrDefault("MAIL_USERNAME", "demo@example.invalid"));
            credentialProp.setProperty(CredentialsConstants.PASSWD, System.getenv().getOrDefault("MAIL_PASSWORD", ""));
            mailProp.setProperty("mail.smtp.host", System.getenv().getOrDefault("MAIL_HOST", "localhost"));
            mailProp.setProperty("mail.smtp.port", System.getenv().getOrDefault("MAIL_PORT", "1025"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a simple email with from and recipient address, subject and a simple HTML format content.
     *
     * @param to      recipient email address
     * @param subject email subject
     * @param content email content in html format
     * @return a {@link boolean} indicating if the email was sent or not.
     */
    public boolean send(String to, String subject, String content) {
        boolean result;
        Session session = createSession();

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(credentialProp.getProperty(CredentialsConstants.USER)));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);

            message.setContent(content, "text/html");

            System.out.println("sending...");
            String localDirectory = System.getenv("LOCAL_MAIL_DIR");
            if (localDirectory != null && !localDirectory.isBlank()) {
                java.nio.file.Path directory = java.nio.file.Path.of(localDirectory);
                java.nio.file.Files.createDirectories(directory);
                try (OutputStream file = java.nio.file.Files.newOutputStream(directory.resolve(java.util.UUID.randomUUID() + ".eml"))) {
                    message.writeTo(file);
                }
            } else {
                Transport.send(message);
            }
            System.out.println("Sent message successfully....");
            result = true;
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
            result = false;
        }

        return result;
    }

    private Session createSession() {
        Session session = Session.getInstance(mailProp, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(credentialProp.getProperty(CredentialsConstants.USER),
                        credentialProp.getProperty(CredentialsConstants.PASSWD));
            }
        });
        session.setDebug(false);
        return session;
    }
}
