package org.mkukharchuk.dao.impl;

import org.mkukharchuk.dao.ImageDAO;
import org.mkukharchuk.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class ImageDAOImpl implements ImageDAO {

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

    public void saveImage(Image image) {
        entityManager.persist(image);
    }

}
