package com.vanchutin.dao;


import com.vanchutin.annotation.ResourceSql;
import com.vanchutin.model.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ComponentDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @ResourceSql("UpdateComponentStatus.sql")
    private String updateStatusTemplate;

    @ResourceSql("CountAllComponents.sql")
    private String countAllTemplate;

    @ResourceSql("CountBrokenComponents.sql")
    private String countBrokenTemplate;

    public void  setStatusTrue(int deviceId, int componentId){
        setStatus(deviceId, componentId, true);
    }

    public void setStatusFalse(int deviceId, int componentId){
        setStatus(deviceId, componentId, false);
    }

    public int countAll(int deviceId){
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("id", deviceId);
        return namedParameterJdbcTemplate.queryForObject(countAllTemplate, parameters, Integer.class);
    }

    public int countBroken(int deviceId){
        MapSqlParameterSource parameters  = new MapSqlParameterSource();
        parameters.addValue("status", false);
        parameters.addValue("id", deviceId);
        return namedParameterJdbcTemplate.queryForObject(countBrokenTemplate, parameters, Integer.class);
    }

    private void setStatus(int deviceId, int componentId, boolean status){
        MapSqlParameterSource parameters  = new MapSqlParameterSource();
        parameters.addValue("status", status);
        parameters.addValue("device_id", deviceId);
        parameters.addValue("component_id", componentId);
        namedParameterJdbcTemplate.update(updateStatusTemplate, parameters);
    }
}
