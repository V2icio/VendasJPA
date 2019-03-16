package repository;

import others.Connection;
import models.Produto;

import javax.persistence.*;
import java.util.List;

public class ProdutoRepository {
    EntityManagerFactory emf;
    EntityManager em;

    public ProdutoRepository(Connection connection) {
        this.emf = connection.getEmf();
        this.em = connection.getEm();
    }

    //Create
    public void insereProduto(Produto produto)
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(produto);
        tx.commit();
    }

    //Read
    public void mostraProduto()
    {
        TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
        List<Produto> results = query.getResultList();
        for (Produto p : results)
            System.out.println(p);
    }

    public boolean existeProduto()//retorna true se existe pelo menos um vendedor no banco
    {
        TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
        List<Produto> results = query.getResultList();

        if(results.isEmpty())
        {
            return false;
        }
        else{
            return true;
        }
    }

    public Produto retornaProduto(int id)
    {
        //metodo que retorne um unico produto, pode ser uma copia
        TypedQuery<Produto> query2 = em.createQuery("SELECT p FROM Produto p WHERE p.produtoId = :id", Produto.class);
        query2.setParameter("id", id);

        return query2.getSingleResult();
    }

    //Update
    public void atualizaProduto(int id, Produto produto)//atualizar produtos pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Produto> query2 = em.createQuery("SELECT p FROM Produto p WHERE p.produtoId = :id", Produto.class);
        query2.setParameter("id", id);
        for (Produto p : query2.getResultList())
        {
            p.setDescricao(p.getDescricao());
            p.setEmbalagem(p.getEmbalagem());
            p.setEstoqueMinimo(p.getEstoqueMinimo());
            p.setPrecoVenda(p.getPrecoVenda());
            p.setUnidadeMedida(p.getUnidadeMedida());
        }
        tx.commit();
    }

    //Delete
    public void excluirProduto(int id)//excluir produto pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Produto> query2 = em.createQuery("SELECT p FROM Produto p WHERE p.produtoId = :id", Produto.class);
        query2.setParameter("id", id);
        for (Produto p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }

    public void excluirTodosProduto()//exclui todos os produtos do banco
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Produto> query2 = em.createQuery("SELECT p FROM Cliente p", Produto.class);
        for (Produto p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }
}