package Excel;
import javax.mail.internet.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import javax.mail.*;
import javax.activation.*;
public class mailSendAttachment {

	public static void main(String args[]){
		Long start=System.currentTimeMillis();
		
		List listRecipient=new ArrayList<String>();
		listRecipient.add("abhishek2491996@gmail.com");
		
		String recipients=String.join(",", listRecipient);
		String to="abhishek2491996@gmail.com";
		String user="ashu2491996@gmail.com";
		String password="9050480957a";
		
		Properties properties=System.getProperties();
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port","465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port","465");
		
		Session session=Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
				return new javax.mail.PasswordAuthentication(user, password);
			}
		});
		
		try{
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
			message.setSubject("Testing mail");
			
			BodyPart messageBodyPart1=new MimeBodyPart();
			messageBodyPart1.setText("This is a e=message body");
			
			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
		
			MimeBodyPart messageBodyPart2=new MimeBodyPart();
			String filename="ABC_MODULE_1552610871744.xlsx";
			DataSource source=new FileDataSource("D:\\Development\\Development\\"+filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			
			multipart.addBodyPart(messageBodyPart2);
			
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Message sent");
			
			Long end=System.currentTimeMillis();
			
			System.out.println("Time taken for send mail " +(end-start)/1000);
		}
		catch(Exception e){
			System.out.println("Exception occured "+e);
		}
	}
}
