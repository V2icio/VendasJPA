package Others;

import javax.persistence.*;

public class Connection {
    EntityManagerFactory emf;
    EntityManager em;

    public void create()
    {
        emf = Persistence.createEntityManagerFactory("$objectdb/db/Vendas.odb");
        em = emf.createEntityManager();
    }

    public void close()
    {
        em.close();
        emf.close();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager getEm() {
        return em;
    }
}
