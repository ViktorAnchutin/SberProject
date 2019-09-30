package com.vanchutin.dao;

import com.vanchutin.model.Component;

public interface ComponentDao {

    void updateStatus(int id, boolean status);

    int countByStatus(int deviceId, boolean status);

    int countAll(int deviceId);
}
