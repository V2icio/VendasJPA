package Others;

import models.*;
import repository.ClienteRepository;
import repository.VendaRepository;
import repository.VendedorRepository;


import java.util.*;


public class Main {

    public static void main(String[] args) {
        Connection conexao = new Connection();
        conexao.create();

        Date date = new Date();
        Cliente cliente = new Cliente("João", "Rua das pedras 43", "Pedregulho", "123456789",
                "Virmonde", date, "34331234");

        ClienteRepository clienteRepository = new ClienteRepository(conexao);
        clienteRepository.insereCliente(cliente);

        date = new Date();

        Calendar calendar = new GregorianCalendar(2001, 10,30);


        Date dataNasc = calendar.getTime();

        Vendedor vendedor = new Vendedor("Valdisnei", dataNasc, "Rua das borboletas 24", "Das palmeiras",
                "987654321", "Caminho das Antas", "12345678", date);

        VendedorRepository vendedorRepository = new VendedorRepository(conexao);
        vendedorRepository.insereVendedor(vendedor);

        long id = 16;

        vendedor = new Vendedor(null, null ,null, null, null, null, null, null);
        vendedor.setId(id);



        List<ItemVenda> lista = new LinkedList<>();

        Produto produto = new Produto("Bola de futebol", "caixa", 2,"UND", 35);
        ItemVenda itemVenda = new ItemVenda(produto, 2);
        lista.add(itemVenda);
        produto = new Produto("vassora","nao tem", 12,"UND", 12);

        itemVenda = new ItemVenda(produto, 4);

        lista.add(itemVenda);



        date = new Date();
        Venda venda = new Venda(date, 200, "finalizada", vendedor, cliente, lista);
        VendaRepository vendaRepository = new VendaRepository(conexao);

        vendaRepository.insereVenda(venda);
    }
}