package dsw.concessionaria.service.spec;

public interface IEmailService {
    void enviarEmail(String destinatario, String assunto, String corpo);
}