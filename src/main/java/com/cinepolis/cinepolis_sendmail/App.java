package com.cinepolis.cinepolis_sendmail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App {
       public static void main(String[] args) {
    	   
    	   System.out.println("LISTAGEM: ");
    	   
    	   String nomeAplicacao="";
    	   
           for (String arg : args) {
               System.out.println("Argumento: " + arg);
           }

           if (args.length > 0) {
               System.out.println("Primeiro argumento: " + args[0]);
               nomeAplicacao = args[0];
               if (args.length > 1) {
                   System.out.println("Segundo argumento: " + args[1]);
               }
           }
            
           
           String destinatario = "cinepolisautomatic@gmail.com";
           String assunto = "[Cinepolis] Result of installation APP: " +nomeAplicacao;
           String corpo = "Hello! The application "+nomeAplicacao+" was installed with successfully datetime: " + dataHora();
           sendEmail(destinatario, assunto, corpo);
       }
       
       
       
       public static void sendEmail(String destinatario, String assunto, String corpo) {
    	   
    	// Configurações do servidor SMTP
           String remetente = "cinepolisautomatic@gmail.com";
           String senha = "zfxb bcky cafk niaf";

           // Configurações de propriedades
           Properties props = new Properties();
           props.put("mail.smtp.host", "smtp.gmail.com");
           props.put("mail.smtp.port", "587");
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.starttls.enable", "true");
           props.put("mail.smtp.ssl.protocols", "TLSv1.2");

           // Autenticação
           Session session = Session.getInstance(props, new Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(remetente, senha);
               }
           });

           try {
               // Criação da mensagem
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress(remetente));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
               message.setSubject(assunto);
               message.setText(corpo);

               // Envio da mensagem
               Transport.send(message);
               System.out.println("E-mail enviado com sucesso!");
           } catch (MessagingException e) {
               System.out.println("Erro ao enviar e-mail: " + e.getMessage());
           }
       }
       
       public static String dataHora() {   
    	   LocalDateTime agora = LocalDateTime.now();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
           String dataHora = agora.format(formatter);
           System.out.println("Data e Hora: " + dataHora);  
           return dataHora;
       }
   }