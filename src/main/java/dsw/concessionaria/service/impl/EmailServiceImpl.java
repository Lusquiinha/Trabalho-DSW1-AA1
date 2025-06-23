package dsw.concessionaria.service.impl;

import dsw.concessionaria.service.spec.IEmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

    @Override
    public void enviarEmail(String destinatario, String assunto, String corpo) {
        System.out.println("=====================================================");
        System.out.println("SIMULAÇÃO DE ENVIO DE E-MAIL");
        System.out.println("Para: " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Corpo: \n" + corpo);
        System.out.println("=====================================================");
    }
}