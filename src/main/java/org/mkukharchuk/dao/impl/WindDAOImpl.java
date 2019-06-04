package org.mkukharchuk.dao.impl;

import org.mkukharchuk.dao.WindDAO;
import org.mkukharchuk.model.Wind;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class WindDAOImpl implements WindDAO {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    public Wind getLastWind(){
        Query query = entityManager.createQuery("select i from Wind i order by id DESC");
        query.setMaxResults(1);
        return (Wind) query.getSingleResult();
    }

    @Transactional
    public void saveWind(Wind wind) {
        entityManager.persist(wind);
    }
}
