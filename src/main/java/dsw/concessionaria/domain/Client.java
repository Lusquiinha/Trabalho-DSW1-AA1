package dsw.concessionaria.domain;

import dsw.concessionaria.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Client extends Usuario {

    @NotBlank
    @Column(nullable = false, length = 15, unique = true)
    private String cpf;

    @NotBlank
    @Column(nullable = false, length = 16)
    private String telefone;

    @NotBlank
    @Column(nullable = false, length = 10)
    private String sexo; // Masculino, Feminino, Outro

    @NotBlank
    @Column(nullable = false, length = 100)
    private String dataNascimento;

    public Client(@NotBlank String username, @NotBlank @Email String email, @NotBlank String password,
            @NotBlank String cpf, @NotBlank String telefone,
            @NotBlank String sexo, @NotBlank String dataNascimento) {
        super(username, email, password, UserRole.CLIENT);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Client() {
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Client [ " + super.toString() + " cpf=" + cpf + ", telefone=" + telefone + ", sexo=" + sexo + ", dataNascimento=" + dataNascimento
                + "]";
    }


}
