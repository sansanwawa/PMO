/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sandy
 */
public class Email {

    Properties prop = new Properties();
    private String from;
    private String recipients[];
    private String subject;
    private String message;
    boolean debug;
    private static String SMTP_AUTH_USER = "myUser";
    private static String SMTP_AUTH_PWD  = "myPassword";
    private static String SMTP_HOST_NAME = "localhost";

    public Email() throws AddressException {
        this.prop.put("mail.smtp.host", SMTP_HOST_NAME);
        this.prop.put("mail.smtp.auth", true);
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void send() throws AddressException, MessagingException {
        // create some properties and get the default Session
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(this.prop, auth);
        session.setDebug(this.debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(this.from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];

        for (int i = 0; i < this.recipients.length; i++) {
            addressTo[i] = new InternetAddress(this.recipients[i]);
        }

        msg.setRecipients(Message.RecipientType.TO, addressTo);
        //msg.addHeader("MyHeaderName", "myHeaderValue");

        msg.setSubject(this.subject);
        msg.setContent(this.message, "text/plain");
        Transport.send(msg);

    }

    private class SMTPAuthenticator extends Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
}
