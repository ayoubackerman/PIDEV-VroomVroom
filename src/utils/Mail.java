///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package utils;
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class Mail {
//
//    public static void main(String[] args) {
//
//        String to = "recipient@example.com"; // recipient email address
//        String from = "sender@example.com"; // sender email address
//        String password = "password123"; // sender email password
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true"); // Enable authentication
//        props.put("mail.smtp.starttls.enable", "true"); // Set TLS encryption enabled
//        props.put("mail.smtp.host", "smtp.gmail.com"); // Set SMTP host
//        props.put("mail.smtp.port", "587"); // Set smtp port
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//            message.setSubject("Test email");
//            message.setText("This is a test email sent using JavaMail API.");
//
//            Transport.send(message);
//            System.out.println("Email sent successfully.");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
