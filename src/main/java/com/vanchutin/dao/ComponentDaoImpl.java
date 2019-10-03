package com.vanchutin.dao;

import com.vanchutin.model.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComponentDaoImpl implements ComponentDao{

    EntityManagerFactory emf;

    public ComponentDaoImpl(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public void updateStatus(int id, boolean status) {

        EntityManager em = emf.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Component> criteria = builder.createCriteriaUpdate(Component.class);
        Root<Component> from = criteria.from(Component.class);
        criteria.set("status", status);
        criteria.where(builder.equal(from.get("id"), id));

        em.getTransaction().begin();

        em.createQuery(criteria).executeUpdate();

        em.getTransaction().commit();
        em.close();
    }


    public int countByStatus(int deviceId, boolean status) {

        EntityManager em = emf.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Component> from = criteria.from(Component.class);
        criteria.select(builder.count(from));
        Predicate[] predicates  = new Predicate[2];
        predicates[0] = builder.equal(from.get("device"), deviceId);
        predicates[1] = builder.equal(from.get("status"), status);
        criteria.where(predicates);

        em.getTransaction().begin();

        Long result = em.createQuery(criteria).getSingleResult();

        em.getTransaction().commit();
        em.close();

        return  result.intValue();
    }


    public int countAll(int deviceId) {

        EntityManager em = emf.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Component> from = criteria.from(Component.class);
        criteria.select(builder.count(from));
        criteria.where(builder.equal(from.get("device"), deviceId));

        em.getTransaction().begin();

        Long result = em.createQuery(criteria).getSingleResult();

        em.getTransaction().commit();
        em.close();
        return  result.intValue();
    }


}
