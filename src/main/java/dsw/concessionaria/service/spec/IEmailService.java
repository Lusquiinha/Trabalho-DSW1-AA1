package dsw.concessionaria.service.spec;

public interface IEmailService {
    void enviarEmail(String nodeDoRemetente, String destinatario, String assunto, String corpo);
}