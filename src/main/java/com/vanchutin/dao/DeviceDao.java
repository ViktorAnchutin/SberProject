package com.vanchutin.dao;

import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;

import javax.persistence.EntityManager;

public interface DeviceDao {
    void updateStatus(int deviceId, Status status);

    Status getStatus(int deviceId);

    Device getById(int deviceId);


}
