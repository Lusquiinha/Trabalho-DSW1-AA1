package dsw.concessionaria;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.service.spec.IClienteService;
import dsw.concessionaria.service.spec.ILojaService;
import dsw.concessionaria.service.spec.IUsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ConcessionariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcessionariaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IUsuarioService usuarioService, IClienteService clienteService, ILojaService lojaService) {
        return (args) -> {

            // 1. Cria o usuário ADMIN
            usuarioService.initAdminUser();
            System.out.println("Verificação de usuário Admin concluída.");

            // 2. Cria um usuário CLIENTE de exemplo
            if (clienteService.buscarPorCpf("111.111.111-11") == null) {
                Cliente cliente = new Cliente();
                cliente.setEmail("cliente@exemplo.com");
                cliente.setUsername("cliente");
                cliente.setPassword("cliente");
                // O role CLIENT já é definido no construtor do Cliente
                cliente.setNome("Cliente de Exemplo");
                cliente.setCpf("111.111.111-11");
                cliente.setTelefone("16999998888");
                cliente.setSexo("Outro");
                cliente.setDataNascimento(LocalDate.of(1995, 5, 20));
                
                clienteService.salvar(cliente);
                System.out.println("Cliente de exemplo criado.");
            }
            
            // 3. Cria um usuário LOJA de exemplo
            if (lojaService.buscarPorCnpj("22.222.222/0001-22") == null) {
                Loja loja = new Loja();
                loja.setEmail("loja@exemplo.com");
                loja.setUsername("22.222.222/0001-22");
                loja.setPassword("loja"); 
                // O role STORE já é definido no construtor da Loja
                loja.setNome("Loja de Teste");
                loja.setCnpj("22.222.222/0001-22");
                loja.setDescricao("A melhor loja de veículos de teste da região.");

                lojaService.salvar(loja);
                System.out.println("Loja de exemplo criada.");
            }
        };
    }
}