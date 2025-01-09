package org.ifmo.laba3;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
@ApplicationScoped
@Named("connectionDB")
public class ConnectionDB implements Serializable {
    private static final Logger logger = Logger.getLogger(ConnectionDB.class.getName());

    @PersistenceContext(unitName = "records")
    private EntityManager entityManager;
 //   private final String GET_ALL_RECORDS = "SELECT r FROM TableRow r";
    private final String REMOVE_RECORDS = "DELETE FROM TableRow r";

    @PostConstruct
    public void init() {
        logger.info("ConnectionDB init, Entity manager: " + entityManager);
    }

    @Transactional
    public void saveTableRow(TableRow tableRow) {
        entityManager.persist(tableRow);
    }

    @Transactional
    public List<TableRow> getAllRecords() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<TableRow> query = cb.createQuery(TableRow.class);
            Root<TableRow> root = query.from(TableRow.class);
            query.select(root);
            return entityManager.createQuery(query).getResultList();
        } catch (PersistenceException e) {
            logger.severe("Error retrieving records: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Transactional
    public void removeAllRecords() {
        entityManager.createQuery(REMOVE_RECORDS).executeUpdate();
    }
}
