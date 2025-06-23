package dsw.concessionaria.domain;

import dsw.concessionaria.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
public class Cliente extends Usuario {

    @NotBlank(message = "{NotBlank.client.nome}") // Adicionado
    @Column(nullable = true) // Adicionado
    private String nome; // Adicionado

    @NotBlank(message = "{NotBlank.client.cpf}")
    @Column(nullable = true, length = 15, unique = true)
    private String cpf;

    @NotBlank(message = "{NotBlank.client.telefone}")
    @Column(nullable = true, length = 16)
    private String telefone;

    @NotBlank(message = "{NotBlank.client.sexo}")
    @Column(nullable = true, length = 10)
    private String sexo; // Masculino, Feminino, Outro

    @NotNull(message = "{NotNull.client.dataNascimento}") // Alterado de @NotBlank
    @Past(message = "{Past.client.dataNascimento}") // Adicionada validação de data
    @Column(nullable = true)
    private LocalDate dataNascimento; // Tipo alterado para LocalDate

    // Construtor vazio para JPA
    public Cliente() {
    }
    
    // Getters e Setters para o novo campo 'nome'
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // Getters e Setters para os outros campos (ajuste o tipo de dataNascimento)
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