package dsw.concessionaria.domain;

import dsw.concessionaria.enums.StatusProposta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Proposta extends AbstractEntity<Long> {

    @NotNull(message = "{NotNull.proposta.valor}")
    @Positive(message = "{Positive.proposta.valor}")
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal valor;

    @NotBlank(message = "{NotBlank.proposta.condicoesPagamento}")
    @Column(nullable = false)
    private String condicoesPagamento;

    // A anotação @NotNull foi removida daqui, pois a data é definida pelo servidor
    @Column(nullable = false)
    private LocalDateTime dataProposta;

    // A anotação @NotNull foi removida daqui, pois o status é definido pelo servidor
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProposta status;

    // A anotação @NotNull foi removida daqui, pois o cliente é definido pelo servidor
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // O veículo vem de um campo oculto no formulário, então mantemos a validação.
    @NotNull
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    // Construtor vazio
    public Proposta() {
    }

    // Getters e Setters (sem alteração)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCondicoesPagamento() {
        return condicoesPagamento;
    }

    public void setCondicoesPagamento(String condicoesPagamento) {
        this.condicoesPagamento = condicoesPagamento;
    }

    public LocalDateTime getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(LocalDateTime dataProposta) {
        this.dataProposta = dataProposta;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}