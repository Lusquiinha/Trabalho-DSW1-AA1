package dsw.concessionaria.domain;

import dsw.concessionaria.enums.UserRole; 
import dsw.concessionaria.validation.UniqueCPF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
public class Cliente extends Usuario {

    @NotBlank(message = "{NotBlank.client.nome}")
    @Column(nullable = false)
    private String nome;

    @UniqueCPF
    @NotBlank(message = "{NotBlank.client.cpf}")
    @Column(nullable = false, length = 15, unique = true)
    private String cpf;

    @NotBlank(message = "{NotBlank.client.telefone}")
    @Column(nullable = false, length = 16)
    private String telefone;

    @NotBlank(message = "{NotBlank.client.sexo}")
    @Column(nullable = false, length = 10)
    private String sexo;

    @NotNull(message = "{NotNull.client.dataNascimento}")
    @Past(message = "{Past.client.dataNascimento}")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    // Construtor vazio para JPA
    public Cliente() {
        // ADICIONADO: Garante que todo novo cliente tenha o papel correto.
        super.setRole(UserRole.CLIENT);
    }
    
    // Getters e Setters (sem alteração)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}