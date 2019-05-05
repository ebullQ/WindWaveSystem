package org.mkukharchuk.dao;

import org.mkukharchuk.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Component
@Transactional(readOnly = true)
public class ImageDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;


    public Image getImageById(int id) {
        TypedQuery<Image> q = entityManager.createQuery(
                "select i from Image i where i.id = :id",
                Image.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    public Image getLastImage(){
        Query query = entityManager.createQuery("select i from Image i order by id DESC");
        query.setMaxResults(1);
        return (Image) query.getSingleResult();
    }

    @Transactional
    public void saveUser(Image user) {
        entityManager.persist(user);
    }

}
