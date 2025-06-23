// /src/main/java/dsw/concessionaria/runner/DataLoader.java

package dsw.concessionaria.runner;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Usuario;
import dsw.concessionaria.enums.UserRole;
import dsw.concessionaria.service.spec.IClienteService;
import dsw.concessionaria.service.spec.ILojaService;
import dsw.concessionaria.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ILojaService lojaService;

    @Override
    public void run(String... args) throws Exception {
        
        usuarioService.initAdminUser();

        criarClienteDeExemplo();
        criarLojaDeExemplo();
    }
    
    private void criarClienteDeExemplo() {
        if (clienteService.buscarPorCpf("111.111.111-11") == null) {
            Cliente cliente = new Cliente();
            cliente.setEmail("cliente@exemplo.com");
            cliente.setUsername("cliente1");
            cliente.setPassword("cliente123"); // Senha em texto plano
            cliente.setRole(UserRole.CLIENT);
            cliente.setNome("Cliente de Exemplo");
            cliente.setCpf("111.111.111-11");
            cliente.setTelefone("16999998888");
            cliente.setSexo("Outro");
            cliente.setDataNascimento(LocalDate.of(1995, 5, 20));

            // O serviço 'salvar' será responsável por criptografar a senha
            clienteService.salvar(cliente);
            System.out.println("Cliente de exemplo criado.");
        }
    }

    private void criarLojaDeExemplo() {
        if (lojaService.buscarPorCnpj("11.111.111/0001-11") == null) {
            Loja loja = new Loja();
            loja.setEmail("loja@exemplo.com");
            loja.setUsername("11.111.111/0001-11"); // Usando CNPJ como username
            loja.setPassword("loja123"); // Senha em texto plano
            loja.setRole(UserRole.STORE);
            loja.setNome("Loja de Exemplo");
            loja.setCnpj("11.111.111/0001-11");
            loja.setDescricao("Uma loja para vender carros incríveis.");

            // A lógica para salvar e criptografar a senha da loja precisaria ser adicionada no LojaService
            // Por simplicidade, vamos assumir que existe um método salvar que faz isso.
            lojaService.salvar(loja);
            System.out.println("Loja de exemplo criada.");
        }
    }
}