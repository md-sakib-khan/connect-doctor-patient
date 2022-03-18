package com.sakib.connectdoctorpatient.dao;

import com.sakib.connectdoctorpatient.model.Persistent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
@Repository
public abstract class ParentDao<T extends Persistent> {

    @PersistenceContext(name = "persistent")
    protected EntityManager em;

    @Transactional
    public T saveOrUpdate(T obj) {
        if (obj.isNew()) {
            em.persist(obj);
        } else {
            obj = em.merge(obj);
        }
        em.flush();

        return obj;
    }

    @Transactional
    public void remove(T obj) {
        em.remove(em.getReference(obj.getClass(), obj.getId()));
    }
}
