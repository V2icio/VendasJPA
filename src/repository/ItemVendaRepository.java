package repository;

import models.ItemVenda;
import others.Connection;

import javax.persistence.*;
import java.util.List;

public class ItemVendaRepository {
    EntityManagerFactory emf;
    EntityManager em;
    Connection connection;

    public ItemVendaRepository(Connection conexao) {
        this.emf = conexao.getEmf();
        this.em = conexao.getEm();
        connection = conexao;
    }

    //Create
    public void insereItemVenda(ItemVenda itemVenda)
    {
        //Para criar um item venda precisa existir  um produto pelo menos
        ProdutoRepository produtoRepository = new ProdutoRepository(connection);
        if(!produtoRepository.existeProduto())
        {
            System.out.println("ERRO! Não existe produto cadastrado!");
            return;
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(itemVenda);
        tx.commit();
    }

    //Read
    public void mostraItemVenda()
    {
        TypedQuery<ItemVenda> query = em.createQuery("SELECT p FROM Venda p", ItemVenda.class);
        List<ItemVenda> results = query.getResultList();
        for (ItemVenda p : results)
            System.out.println(p);
    }



    //Update
    public void atualizaItemVenda(int id, ItemVenda itemVenda)//atualizar itemVenda pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<ItemVenda> query2 = em.createQuery("SELECT p FROM ItemVenda p WHERE p.itemVendaId = :id", ItemVenda.class);
        query2.setParameter("id", id);
        for (ItemVenda p : query2.getResultList())
        {
            p.setProduto(itemVenda.getProduto());
            p.setQuantidade(itemVenda.getQuantidade());
        }
        tx.commit();
    }

    //Delete
    public void excluirItemVenda(int id)//excluir itemVenda pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<ItemVenda> query2 = em.createQuery("SELECT p FROM ItemVenda p WHERE p.itemVendaId = :id", ItemVenda.class);
        query2.setParameter("id", id);
        for (ItemVenda p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }
}
