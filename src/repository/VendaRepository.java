package repository;

import models.Venda;

import javax.persistence.*;
import java.util.List;

public class VendaRepository {
    EntityManagerFactory emf;
    EntityManager em;

    public VendaRepository(EntityManagerFactory emf, EntityManager em) {
        this.emf = emf;
        this.em = em;
    }

    //Create
    public void insereVenda(Venda venda)
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(venda);
        tx.commit();
    }

    //Read
    public void mostraVenda()
    {
        TypedQuery<Venda> query = em.createQuery("SELECT p FROM Venda p", Venda.class);
        List<Venda> results = query.getResultList();
        for (Venda p : results)
            System.out.println(p);
    }

    //Update
    //Delete
}