package repository;

import others.Connection;
import models.Venda;

import javax.persistence.*;
import java.util.List;

public class VendaRepository {
    EntityManagerFactory emf;
    EntityManager em;

    Connection conexao;

    public VendaRepository(Connection conexao) {
        this.emf = conexao.getEmf();
        this.em = conexao.getEm();
        this.conexao = conexao;
    }

    //Create
    public void insereVenda(Venda venda)
    {
        //para criar uma venda é preciso existir um cliente e um vendedor
        ClienteRepository clienteRepository = new ClienteRepository(conexao);
        VendedorRepository vendedorRepository = new VendedorRepository(conexao);

        if(!clienteRepository.existeCliente() ||  !vendedorRepository.existeVendedor())
        {
            System.out.println("ERRO! Não existe Cliente ou Vendedor");
            return;
        }

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

    public Venda retornaVenda(int id)
    {
        //metodo que retorne uma unica venda, pode ser uma copia
        TypedQuery<Venda> query2 = em.createQuery("SELECT p FROM Venda p WHERE p.vendaId = :id", Venda.class);
        query2.setParameter("id", id);

        return query2.getSingleResult();
    }


    //Update
    public void atualizaVenda(int id, Venda venda)//atualizar vendas pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Venda> query2 = em.createQuery("SELECT p FROM Venda p WHERE p.vendaId = :id", Venda.class);
        query2.setParameter("id", id);
        for (Venda p : query2.getResultList())
        {
            p.setCliente(venda.getCliente());
            p.setStatus(venda.getStatus());
            p.setValorTotal(venda.getValorTotal());
            p.setVendedor(venda.getVendedor());
            p.setListaProdutos(venda.getListaProdutos());
        }
        tx.commit();
    }

    //Delete
    public void excluirVenda(int id)//excluir venda pelo ID
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Venda> query2 = em.createQuery("SELECT p FROM Venda p WHERE p.vendaId = :id", Venda.class);
        query2.setParameter("id", id);
        for (Venda p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }

    public void excluirTodasVendas()//exclui todos as vendas do banco
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Venda> query2 = em.createQuery("SELECT p FROM Venda p", Venda.class);
        for (Venda p : query2.getResultList())
        {
            em.remove(p);
        }
        tx.commit();
    }
}