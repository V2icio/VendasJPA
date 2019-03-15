package Others;

import models.Cliente;
import models.Venda;
import models.Vendedor;
import repository.ClienteRepository;
import repository.VendedorRepository;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


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

        date = new Date();
        Venda venda = new Venda(date, 200, "finalizada", vendedor, cliente);


    }
}