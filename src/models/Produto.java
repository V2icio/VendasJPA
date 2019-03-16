package models;

import javax.jdo.annotations.PrimaryKey;
import javax.persistence.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue
    @PrimaryKey
    private
    long id;

    private String descricao;
    private String embalagem;
    private double estoqueMinimo;
    private String unidadeMedida;
    private double precoVenda;


    public Produto(String descricao, String embalagem, double estoqueMinimo, String unidadeMedida, double precoVenda) {
        this.descricao = descricao;
        this.embalagem = embalagem;
        this.estoqueMinimo = estoqueMinimo;
        this.unidadeMedida = unidadeMedida;
        this.precoVenda = precoVenda;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }

    public double getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(double estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
}
