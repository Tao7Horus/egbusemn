// 
// Decompiled by Procyon v0.5.30
// 

package Mail;

import java.util.Hashtable;
import javax.mail.Transport;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Properties;

public class SendMail
{
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_HOST_PORT = "465";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String SMTP_AUTH_USER = "muasamcong@gmail.com";
    private static final String SMTP_AUTH_PWD = "mpi123456";
    
    public static void main(final String[] array) throws Exception {
        final String s = "trongnvt@gmail.com";
        final String s2 = "muasamcong@gmail.com";
        final String s3 = "Hello from Java";
        final String s4 = "Đây là mail test";
        new SendMail();
        sendMail(s, s2, s3, s4, "muasamcong@gmail.com", "mpi123456");
    }
    
    public static void sendMail(final String s, final String s2, final String subject, final String text, final String s3, final String s4) throws Exception {
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("mail.smtp.host", "smtp.gmail.com");
        ((Hashtable<String, String>)properties).put("mail.smtp.auth", "true");
        ((Hashtable<String, String>)properties).put("mail.debug", "true");
        ((Hashtable<String, String>)properties).put("mail.smtp.port", "465");
        ((Hashtable<String, String>)properties).put("mail.smtp.socketFactory.port", "465");
        ((Hashtable<String, String>)properties).put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        ((Hashtable<String, String>)properties).put("mail.smtp.socketFactory.fallback", "false");
        final Session instance = Session.getInstance(properties, (Authenticator)new SendMail.SendMail$1(s3, s4));
        instance.setDebug(true);
        final MimeMessage mimeMessage = new MimeMessage(instance);
        ((Message)mimeMessage).setFrom((Address)new InternetAddress(s2));
        ((Message)mimeMessage).setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse(s, false));
        ((Message)mimeMessage).setHeader("Content-Type", "text/html; charset=UTF-8");
        ((Message)mimeMessage).setSubject(subject);
        ((Message)mimeMessage).setText(text);
        ((Message)mimeMessage).setFrom((Address)new InternetAddress(s2));
        ((Message)mimeMessage).setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse(s, false));
        ((Message)mimeMessage).setSentDate(new Date());
        ((Message)mimeMessage).saveChanges();
        Transport.send((Message)mimeMessage);
    }
}
