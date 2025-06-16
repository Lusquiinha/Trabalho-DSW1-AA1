package dsw.concessionaria;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dsw.concessionaria.dao.ClientDAO;
import dsw.concessionaria.dao.UsuarioDAO;
import dsw.concessionaria.domain.Client;
import dsw.concessionaria.domain.Usuario;
import dsw.concessionaria.enums.UserRole;
import dsw.concessionaria.service.ClientService;
import dsw.concessionaria.service.UsuarioService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@SpringBootApplication
public class ConcessionariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcessionariaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsuarioService usuarioService, ClientService clientService) {
		return (args) -> {
			Usuario admin = new Usuario();
			admin.setUsername("admin");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			admin.setEmail("admin@admin.com");
			admin.setRole(UserRole.ADMIN);

			usuarioService.registerUsuarios(admin);

			Client client1 = new Client(
				"cliente", 
				"email@email.com",
				new BCryptPasswordEncoder().encode("cliente123"),
				"123.456.789-00",
				"11987654321",
				"Masculino",
				"01/01/1990"
			);

			clientService.registerClient(client1);

			List<Usuario> usuarios = usuarioService.getAllUsuarios();
			List<Client> clients = clientService.getAllClients();
			System.out.println("Usuários cadastrados:");
			for (Usuario usuario : usuarios) {
				System.out.println(usuario.toString());
			}
			System.out.println("Clientes cadastrados:");
			for (Client client : clients) {
				System.out.println(client.toString());
			}
		};
	}
}
