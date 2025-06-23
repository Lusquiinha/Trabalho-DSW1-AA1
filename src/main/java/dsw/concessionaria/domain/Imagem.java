package dsw.concessionaria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Imagem extends AbstractEntity<Long> {

    @NotBlank
    @Column(nullable = false)
    private String nomeArquivo; 

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    public Imagem() {
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}