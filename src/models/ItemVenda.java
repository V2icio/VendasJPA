package models;

import javax.persistence.*;

@Entity
public class ItemVenda {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    long itemVendaId;

    @ManyToOne
    private Produto produto;
    private double quantidade;

    public ItemVenda(Produto produto, double quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public long getId() {
        return itemVendaId;
    }
}
