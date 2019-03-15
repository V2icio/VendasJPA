package repository;

import Others.Connection;
import models.Vendedor;

import javax.persistence.*;
import java.util.List;

public class VendedorRepository {
    EntityManagerFactory emf;
    EntityManager em;


    public VendedorRepository(Connection conexao) {
        this.emf = conexao.getEmf();
        this.em = conexao.getEm();
    }

    //Create
    public void insereVendedor(Vendedor vendedor)
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(vendedor);
        tx.commit();
    }

    //Read
    public void mostraVendedor()
    {
        TypedQuery<Vendedor> query = em.createQuery("SELECT p FROM Cliente p", Vendedor.class);
        List<Vendedor> results = query.getResultList();
        for (Vendedor p : results)
            System.out.println(p);
    }

    //Update
    //Delete

}
