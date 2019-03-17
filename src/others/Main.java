package others;

import models.*;
import repository.*;


import java.util.*;


public class Main {

    public static void main(String[] args) {
        Connection conexao = new Connection();
        conexao.create();

        //Inserindo um cliente
        Date date = new Date();
        Cliente cliente = new Cliente("João", "Rua das pedras 43", "Pedregulho", "123456789",
                "Virmonde", date, "34331234");

        ClienteRepository clienteRepository = new ClienteRepository(conexao);
        clienteRepository.insereCliente(cliente);

        //Inserindo um vendedor
        date = new Date();
        Calendar calendar = new GregorianCalendar(2001, 10,30);
        Date dataNasc = calendar.getTime();
        Vendedor vendedor = new Vendedor("Valdisnei", dataNasc, "Rua das borboletas 24", "Das palmeiras",
                "987654321", "Caminho das Antas", "12345678", date);

        VendedorRepository vendedorRepository = new VendedorRepository(conexao);
        vendedorRepository.insereVendedor(vendedor);

        //Inserindo produtos
        Produto produto = new Produto("Bola de futebol", "caixa", 2,"UND", 35);
        ProdutoRepository produtoRepository = new ProdutoRepository(conexao);
        produtoRepository.insereProduto(produto);

        produto = new Produto("vassora","nao tem", 12,"UND", 12);
        produtoRepository.insereProduto(produto);

        produto = new Produto("mochila","plastico", 10,"UND", 100);
        produtoRepository.insereProduto(produto);

        //Inserindo os itens na venda
        List<ItemVenda> lista = new LinkedList<>();
        ItemVendaRepository itemVendaRepository = new ItemVendaRepository(conexao);

        ItemVenda itemVenda = new ItemVenda(produtoRepository.retornaProduto(1), 2);
        itemVendaRepository.insereItemVenda(itemVenda);
        lista.add(itemVenda);

        itemVenda = new ItemVenda(produtoRepository.retornaProduto(2), 4);
        itemVendaRepository.insereItemVenda(itemVenda);
        lista.add(itemVenda);

        //Inserindo uma venda
        date = new Date();
        Venda venda = new Venda(date, 200, "finalizada", vendedor, cliente, lista);
        VendaRepository vendaRepository = new VendaRepository(conexao);

        vendaRepository.insereVenda(venda);



        //Editar um vendedor
        vendedor = vendedorRepository.retornaVendedor(1);
        vendedor.setNome("Marcos");
        vendedorRepository.atualizaVendedor(1, vendedor);

        //Editar uma venda
        venda = vendaRepository.retornaVenda(1);
        lista = venda.getListaProdutos();

        itemVenda = lista.get(1);
        lista.remove(1);

        itemVenda.setQuantidade(30);

        //itemVendaRepository.atualizaItemVenda((int) itemVenda.getId(),itemVenda);
        //Não precisa, o banco altera mesmo assim.


        lista.add(itemVenda);

        itemVenda = new ItemVenda(produtoRepository.retornaProduto(3),2);
        itemVendaRepository.insereItemVenda(itemVenda);
        lista.add(itemVenda);

        venda.setListaProdutos(lista);
        venda.setValorTotal(350);
        vendaRepository.atualizaVenda(1,venda);

        //Editar um produto
        produto = produtoRepository.retornaProduto(1);
        produto.setDescricao("Bola de basquete");
        produtoRepository.atualizaProduto(1, produto);

    }
}