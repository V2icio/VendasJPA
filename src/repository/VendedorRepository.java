package repository;

import others.Connection;
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

    public boolean existeVendedor()//retorna true se existe pelo menos um vendedor no banco
    {
        TypedQuery<Vendedor> query = em.createQuery("SELECT p FROM Vendedor p", Vendedor.class);
        List<Vendedor> results = query.getResultList();

        if(results.isEmpty())
        {
            return false;
        }
        else{
            return true;
        }
    }

    public Vendedor retornaVendedor(int id)
    {
        //metodo que retorne um unico vendedor, pode ser uma copia
        TypedQuery<Vendedor> query2 = em.createQuery("SELECT p FROM Vendedor p WHERE p.vendedorId = :id", Vendedor.class);
        query2.setParameter("id", id);

        return query2.getSingleResult();
    }

    //Update
    public void atualizaVendedor(int id, Vendedor vendedor)//atualizar vendedor pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Vendedor> query2 = em.createQuery("SELECT p FROM Vendedor p WHERE p.vendedorId = :id", Vendedor.class);
        query2.setParameter("id", id);
        for (Vendedor p : query2.getResultList())
        {
            p.setBairro(vendedor.getBairro());
            p.setCep(vendedor.getCep());
            p.setCidade(vendedor.getCidade());
            p.setDataNascimento(vendedor.getDataNascimento());
            p.setEndereco(vendedor.getEndereco());
            p.setNome(vendedor.getNome());
            p.setTelefone(vendedor.getTelefone());
        }
        tx.commit();
    }

    //Delete
    public void excluirVendedor(int id)//excluir vendedor pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Vendedor> query2 = em.createQuery("SELECT p FROM Vendedor p WHERE p.vendedorId = :id", Vendedor.class);
        query2.setParameter("id", id);
        for (Vendedor p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }

    public void excluirTodosVendedor()//exclui todos os vendedores do banco
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Vendedor> query2 = em.createQuery("SELECT p FROM Vendedor p", Vendedor.class);
        for (Vendedor p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }
}
