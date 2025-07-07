package dsw.concessionaria;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.domain.Proposta;
import dsw.concessionaria.service.spec.IClienteService;
import dsw.concessionaria.service.spec.ILojaService;
import dsw.concessionaria.service.spec.IUsuarioService;
import dsw.concessionaria.service.spec.IPropostaService;
import dsw.concessionaria.service.spec.IVeiculoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ConcessionariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcessionariaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IUsuarioService usuarioService, IClienteService clienteService, ILojaService lojaService, IVeiculoService veiculoService, IPropostaService propostaService) {
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

            // 4. Cria outro usuário CLIENTE de exemplo
            if (clienteService.buscarPorCpf("222.222.222-22") == null) {
                Cliente cliente2 = new Cliente();
                cliente2.setEmail("cliente2@exemplo.com");
                cliente2.setUsername("cliente2");
                cliente2.setPassword("cliente2");
                cliente2.setNome("Outro Cliente de Exemplo");
                cliente2.setCpf("222.222.222-22");
                cliente2.setTelefone("16999997777");
                cliente2.setSexo("Masculino");
                cliente2.setDataNascimento(LocalDate.of(1990, 10, 15));

                clienteService.salvar(cliente2);
                System.out.println("Outro cliente de exemplo criado.");
            }

            // 5. Cria um veículo de exemplo
            if (veiculoService.buscarPorModelo("Modelo Exemplo") == null || veiculoService.buscarPorModelo("Modelo Exemplo").isEmpty()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca("ABC-1234");
                veiculo.setModelo("Modelo Exemplo");
                veiculo.setChassi("1HGBH41JXMN109186");
                veiculo.setAno(2020);
                veiculo.setQuilometragem(50000);
                veiculo.setDescricao("Veículo de exemplo para testes.");
                veiculo.setValor(new BigDecimal("50000.00"));
                veiculo.setLoja(lojaService.buscarPorCnpj("22.222.222/0001-22"));

                veiculoService.salvar(veiculo);
                System.out.println("Veículo de exemplo criado.");
            }

            // 6. Cria duas propostas, uma para cada cliente
            Veiculo veiculo = veiculoService.buscarPorModelo("Modelo Exemplo").get(0);
            Cliente cliente1 = clienteService.buscarPorCpf("111.111.111-11");
            Cliente cliente2 = clienteService.buscarPorCpf("222.222.222-22");

            if (propostaService.buscarTodosPorCliente(cliente1).isEmpty()) {
                Proposta proposta1 = new Proposta();
                proposta1.setValor(new BigDecimal("45000.00"));
                proposta1.setCondicoesPagamento("À vista");
                proposta1.setCliente(cliente1);
                proposta1.setVeiculo(veiculo);

                propostaService.salvar(proposta1);
                System.out.println("Proposta de exemplo criada para o cliente 1.");
            }

            if (propostaService.buscarTodosPorCliente(cliente2).isEmpty()) {
                Proposta proposta2 = new Proposta();
                proposta2.setValor(new BigDecimal("46000.00"));
                proposta2.setCondicoesPagamento("Parcelado em 12x");
                proposta2.setCliente(cliente2);
                proposta2.setVeiculo(veiculo);

                propostaService.salvar(proposta2);
                System.out.println("Proposta de exemplo criada para o cliente 2.");
            }
        };
    }
}