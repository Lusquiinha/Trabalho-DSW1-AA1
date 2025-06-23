package dsw.concessionaria;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.enums.UserRole;
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
			usuarioService.initAdminUser();
			System.out.println("Verificação de usuário Admin concluída.");

			// Criando dados de exemplo para teste
			if (clienteService.buscarPorCpf("111.111.111-11") == null) {
				Cliente cliente = new Cliente();
				cliente.setEmail("cliente@exemplo.com");
				cliente.setUsername("cliente1");
				cliente.setPassword("cliente123");
				cliente.setRole(UserRole.CLIENT);
				cliente.setNome("Cliente de Exemplo");
				cliente.setCpf("111.111.111-11");
				cliente.setTelefone("16999998888");
				cliente.setSexo("Outro");
				cliente.setDataNascimento(LocalDate.of(1995, 5, 20));
				clienteService.salvar(cliente);
				System.out.println("Cliente de exemplo criado.");
			}
		};
	}
}