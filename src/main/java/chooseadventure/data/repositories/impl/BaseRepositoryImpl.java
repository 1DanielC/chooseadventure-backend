package chooseadventure.data.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

