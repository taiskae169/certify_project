package certify.mail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSender {
	@Autowired
	protected JavaMailSender mailSender;
	
	public void SendEmail(Email email) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();
		System.out.println("메일센더 생성");
		
		try {
			msg.setSubject(email.getSubject());
			msg.setText(email.getContent());
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("messageingException");
			e.printStackTrace();
		}
		try {
			mailSender.send(msg);
		} catch (MailException e) {
			// TODO: handle exception
			System.out.println("MailException 발생");
			e.printStackTrace();
		}
	}

}
