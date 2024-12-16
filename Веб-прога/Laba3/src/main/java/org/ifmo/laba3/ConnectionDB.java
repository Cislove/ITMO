package org.ifmo.laba3;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
public class ConnectionDB implements Serializable {
    private static final Logger logger = Logger.getLogger(ConnectionDB.class.getName());
    private EntityManager entityManager;
    private final String GET_ALL_RECORDS = "SELECT * FROM records";

    public ConnectionDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("points");
        this.entityManager = emf.createEntityManager();
    }

    @PostConstruct
    public void init() {
        logger.info("ConnectionDB init, Entity manager: " + entityManager);
    }


    public void saveTableRow(TableRow tableRow) {
        entityManager.getTransaction().begin();
        entityManager.persist(tableRow);
        entityManager.getTransaction().commit();
    }

    public List<TableRow> getAllRecords(){
        TypedQuery<TableRow> query = entityManager.createQuery(GET_ALL_RECORDS, TableRow.class);
        return query.getResultList();
    }
}
