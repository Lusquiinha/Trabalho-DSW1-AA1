package dsw.concessionaria.service.impl;

import dsw.concessionaria.service.spec.IEmailService;
import org.springframework.stereotype.Service;

import java.io.File;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;


@Service
public class EmailServiceImpl implements IEmailService {
	@Autowired
	JavaMailSender emailSender;

    @Async
	@Override
    public void enviarEmail(String nodeDoRemetente, String destinatario, String assunto, String corpo) {
        System.out.println("=====================================================");
        System.out.println("SIMULAÇÃO DE ENVIO DE E-MAIL");
        System.out.println("Para: " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Corpo: \n" + corpo);
        System.out.println("=====================================================");
        try {
            send(
                new InternetAddress("lucas.alves@estudante.ufscar.br", nodeDoRemetente),
                new InternetAddress(destinatario, destinatario),
                assunto,
                corpo
            );

        } catch (Exception e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
            e.printStackTrace();
        }
    }

	public void send(InternetAddress from, InternetAddress to, String subject, String body, File file) {

		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);

			if (file != null) {
				helper.addAttachment(file.getName(), file);
			}

			emailSender.send(message);
		

			System.out.println("Mensagem enviada com sucesso!");

		} catch (MessagingException e) {
			System.out.println("Mensagem não enviada!");
			e.printStackTrace();
		}
	}

	public void send(InternetAddress from, InternetAddress to, String subject, String body) {
		send(from, to, subject, body, null);
	}
}