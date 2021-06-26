package com.diploma.kulBook;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Mail {
    public static void sendMail(String address, String login, String newPassword) throws MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtps.host", "smtp.mailgun.org");
        props.put("mail.smtps.auth", "true");

        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("postmaster@sandbox3fc3f876e15d40469e3f59a99be1b892.mailgun.org"));

        InternetAddress[] addrs = InternetAddress.parse(address, false);
        msg.setRecipients(Message.RecipientType.TO, addrs);

        msg.setSubject("New Password for " + login);
        msg.setText("Hello, you change password for login " + login + ". New password: " + newPassword + ".");
        msg.setSentDate(new Date());

        SMTPTransport t =
                (SMTPTransport) session.getTransport("smtps");
        t.connect("smtp.mailgun.org", "postmaster@sandbox3fc3f876e15d40469e3f59a99be1b892.mailgun.org", "975e3915dd3d05aa426c88cc8d5fa575-d32d817f-eadbabc7");
        t.sendMessage(msg, msg.getAllRecipients());

        System.out.println("Response: " + t.getLastServerResponse());

        t.close();
    }
}
