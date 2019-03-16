package repository;

import Others.Connection;

import javax.persistence.*;

public class ProdutoRepository {
    EntityManagerFactory emf;
    EntityManager em;

    public ProdutoRepository(Connection connection) {
        this.emf = connection.getEmf();
        this.em = connection.getEm();
    }
}
