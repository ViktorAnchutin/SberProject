package com.vanchutin.dao;

import com.vanchutin.annotation.ResourceSql;
import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DeviceDao {

    @ResourceSql("SelectAllDevices.sql")
    private String selectAllTemplate;

    @ResourceSql("SelectDeviceStatus.sql")
    private String selectStatusTemplate;

    @ResourceSql("UpdateDeviceStatus.sql")
    private String updateStatusTemplate;

    @ResourceSql("SelectDeviceById.sql")
    private String selectByIdTemplate;

    @Autowired
    private RowMapper<Device> deviceRowMapper;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Device> getAll(){
        return namedParameterJdbcTemplate.query( selectAllTemplate, deviceRowMapper);
    }

    public Status getStatus(int deviceId){
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("id", deviceId);
        return namedParameterJdbcTemplate.queryForObject(selectStatusTemplate, parameters, Status.class);
    }

    public void updateStatus(int deviceId, Status newStatus){
        MapSqlParameterSource parameters  = new MapSqlParameterSource();
        parameters.addValue("status", String.valueOf(newStatus));
        parameters.addValue("id", deviceId);
        namedParameterJdbcTemplate.update(updateStatusTemplate, parameters);
    }

    public Device getById(int deviceId){
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("id", deviceId);
        return namedParameterJdbcTemplate.queryForObject(selectByIdTemplate, parameters, deviceRowMapper);
    }



}
