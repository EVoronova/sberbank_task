package com.Sberbank.task.dao;

import com.Sberbank.task.entities.DocType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DocTypeDAOImpl implements DocTypeDAO {
    public void createDocType(DocType docType) {
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("examplePU");
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(docType);
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }
}
