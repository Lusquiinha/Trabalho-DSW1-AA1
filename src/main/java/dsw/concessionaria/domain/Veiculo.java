package dsw.concessionaria.domain;

import dsw.concessionaria.validation.UniquePlaca;
import dsw.concessionaria.validation.UniqueChassi;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Veiculo extends AbstractEntity<Long> {

    @UniquePlaca 
    @NotBlank(message = "{NotBlank.veiculo.placa}")
    @Column(nullable = false, length = 8, unique = true)
    private String placa; // Requisito: placa 

    @NotBlank(message = "{NotBlank.veiculo.modelo}")
    @Column(nullable = false)
    private String modelo; // Requisito: modelo 

    @UniqueChassi
    @NotBlank(message = "{NotBlank.veiculo.chassi}")
    @Column(nullable = false, length = 17, unique = true)
    private String chassi; // Requisito: chassi 

    @NotNull(message = "{NotNull.veiculo.ano}")
    @Column(nullable = false)
    private Integer ano; // Requisito: ano 

    @NotNull(message = "{NotNull.veiculo.quilometragem}")
    @PositiveOrZero(message = "{PositiveOrZero.veiculo.quilometragem}")
    @Column(nullable = false)
    private Integer quilometragem; // Requisito: quilometragem 

    @NotBlank(message = "{NotBlank.veiculo.descricao}")
    @Column(nullable = false, length = 256)
    private String descricao; // Requisito: descrição 

    @NotNull(message = "{NotNull.veiculo.valor}")
    @Positive(message = "{Positive.veiculo.valor}")
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT 0.0")
    private BigDecimal valor; // Requisito: valor 

    // @NotNull(message = "{NotNull.veiculo.loja}")
    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja; // Requisito: CNPJ da loja, implementado como uma relação 

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> fotos; // Requisito: fotos (no máximo 10) 

    // Construtor vazio
    public Veiculo() {
    }

    // Getters e Setters para todos os campos (sem alteração)
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public List<Imagem> getFotos() {
        return fotos;
    }

    public void setFotos(List<Imagem> fotos) {
        this.fotos = fotos;
    }
}