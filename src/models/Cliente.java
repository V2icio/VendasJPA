package models;

import java.util.Date;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;
import javax.persistence.*;

@Entity
public class Cliente {
    private String nome;
    private String endereco;
    private String bairro;
    private String cep;
    private String cidade;
    private Date dataCadastro;
    private String telefone;


    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    long clienteId;

    private static final long serialVersionUID = 1L;

    public Long getId()
    {
        return serialVersionUID;
    }

    public Cliente(String nome, String endereco, String bairro, String cep, String cidade, Date dataCadastro, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.dataCadastro = dataCadastro;
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
