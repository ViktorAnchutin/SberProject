package com.vanchutin.dao;

import com.vanchutin.model.Component;
import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

public class DeviceDaoImpl implements DeviceDao {

    EntityManagerFactory emf;

    public DeviceDaoImpl(EntityManagerFactory emf){
        this.emf = emf;
    }


    public void updateStatus(int deviceId, Status status) {

        EntityManager em = emf.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Device> criteria = builder.createCriteriaUpdate(Device.class);
        Root<Device> from = criteria.from(Device.class);
        criteria.set("status", status);
        criteria.where(builder.equal(from.get("id"), deviceId));

        em.getTransaction().begin();

        em.createQuery(criteria).executeUpdate();

        em.getTransaction().commit();
        em.close();
    }


    public Status getStatus(int deviceId) {

        EntityManager em = emf.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
        Root<Device> from = criteria.from(Device.class);
        criteria.select(from.<Status>get("status"));
        criteria.where(builder.equal(from.get("id"), deviceId));

        em.getTransaction().begin();

        Status deviceStatus = em.createQuery(criteria).getSingleResult();

        em.getTransaction().commit();
        em.close();
        return deviceStatus;
    }

    public Device getById(int deviceId) {
        EntityManager em = emf.createEntityManager();
        Device device = em.find(Device.class, deviceId);
        em.close();
        return device;
    }



}
