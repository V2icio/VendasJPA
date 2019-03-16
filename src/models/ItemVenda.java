package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda {

    @Id@GeneratedValue
    long id;

    @ManyToOne
    private Produto produto;
    private double quantidade;

    public ItemVenda(Produto produto, double quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
}
