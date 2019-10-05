package com.vanchutin.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComponentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String updateStatusTemplate =     "UPDATE device_component " +
                                                    "SET component_status = %s " +
                                                    "WHERE device_id = %d AND component_id = %d";

    private final String countAllTemplate =     "SELECT COUNT(*) FROM device_component " +
                                                "WHERE device_id = %d";

    private final String countBrokenTemplate =  "SELECT COUNT(*) FROM device_component " +
                                                "WHERE device_id = %d and component_status = '%s'";

    public void  setStatusTrue(int deviceId, int componentId){
        setStatus(deviceId, componentId, true);
    }

    public void setStatusFalse(int deviceId, int componentId){
        setStatus(deviceId, componentId, false);
    }

    public int countAll(int deviceId){
        return jdbcTemplate.queryForObject(String.format(countAllTemplate, deviceId), Integer.class);
    }

    public int countBroken(int deviceId){
        return jdbcTemplate.queryForObject(String.format(countBrokenTemplate, deviceId, String.valueOf(false)), Integer.class);
    }

    private void setStatus(int deviceId, int componentId, boolean status){
        jdbcTemplate.update(String.format(updateStatusTemplate, status, deviceId, componentId));
    }


}
