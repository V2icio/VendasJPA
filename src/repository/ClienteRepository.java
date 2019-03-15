package repository;

import Others.Connection;
import models.Cliente;

import javax.persistence.*;
import java.util.List;

public class ClienteRepository {
    EntityManagerFactory emf;
    EntityManager em;


    public ClienteRepository(Connection conexao) {
        this.emf = conexao.getEmf();
        this.em = conexao.getEm();
    }


    //Create
    public void insereCliente(Cliente cliente)
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(cliente);
        tx.commit();
    }

    //Read
    public void mostraClientes()
    {
        TypedQuery<Cliente> query = em.createQuery("SELECT p FROM Cliente p", Cliente.class);
        List<Cliente> results = query.getResultList();
        for (Cliente p : results)
            System.out.println(p);
    }

    //Update


    //Delete

}
