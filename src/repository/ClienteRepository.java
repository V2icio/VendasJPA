package repository;

import others.Connection;
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

    public boolean existeCliente()//retorna true se existe cliente no banco
    {
        TypedQuery<Cliente> query = em.createQuery("SELECT p FROM Cliente p", Cliente.class);
        List<Cliente> results = query.getResultList();

        if(results.isEmpty())
        {
            return false;
        }
        else{
            return true;
        }
    }

    public Cliente retornaCliente(int id)
    {
        //metodo que retorne um unico cliente, pode ser uma copia
        TypedQuery<Cliente> query2 = em.createQuery("SELECT p FROM Cliente p WHERE p.clienteId = :id", Cliente.class);
        query2.setParameter("id", id);

        return query2.getSingleResult();
    }

    //Update
    public void atualizaCliente(int id, Cliente cliente)//atualizar clientes pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Cliente> query2 = em.createQuery("SELECT p FROM Cliente p WHERE p.clienteId = :id", Cliente.class);
        query2.setParameter("id", id);
        for (Cliente p : query2.getResultList())
        {
            p.setCep(cliente.getCep());
            p.setBairro(cliente.getBairro());
            p.setCidade(cliente.getCidade());
            p.setEndereco(cliente.getEndereco());
            p.setTelefone(cliente.getTelefone());
        }
        tx.commit();
    }

    //Delete
    public void excluirCliente(int id)//excluir cliente pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Cliente> query2 = em.createQuery("SELECT p FROM Cliente p WHERE p.clienteId = :id", Cliente.class);
        query2.setParameter("id", id);
        for (Cliente p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }

    public void excluirTodosCliente()//exclui todos os clientes do banco
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Cliente> query2 = em.createQuery("SELECT p FROM Cliente p", Cliente.class);
        for (Cliente p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }
}