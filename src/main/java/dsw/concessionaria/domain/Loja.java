package dsw.concessionaria.domain;

import dsw.concessionaria.enums.UserRole; 
import dsw.concessionaria.validation.UniqueCNPJ;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Loja extends Usuario {

    @UniqueCNPJ
    @NotBlank(message = "{NotBlank.loja.cnpj}")
    @Column(nullable = true, length = 18, unique = true) 
    private String cnpj;

    @NotBlank(message = "{NotBlank.loja.nome}")
    @Column(nullable = true) 
    private String nome;

    @NotBlank(message = "{NotBlank.loja.descricao}")
    @Column(nullable = true, length = 256) 
    private String descricao;

    public Loja() {
        // ADICIONADO: Garante que toda nova loja tenha o papel correto.
        super.setRole(UserRole.STORE);
    }
    
    // Getters e Setters (sem alteração)
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}