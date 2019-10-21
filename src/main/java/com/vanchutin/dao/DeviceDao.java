package com.vanchutin.dao;

import com.vanchutin.annotation.ResourceSql;
import com.vanchutin.exception.DeviceNotFoundException;
import com.vanchutin.model.Device;
import com.vanchutin.model.mapper.DeviceDaoMapper;
import com.vanchutin.model.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    @ResourceSql("InsertDevice.sql")
    private String insertTemplate;

    @ResourceSql("DeviceExists.sql")
    private String existsTemplate;

    @Autowired
    private DeviceDaoMapper deviceRowMapper;

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

    public void updateStatus(long deviceId, Status newStatus){
        MapSqlParameterSource parameters  = new MapSqlParameterSource();
        parameters.addValue("status", String.valueOf(newStatus));
        parameters.addValue("id", deviceId);
        namedParameterJdbcTemplate.update(updateStatusTemplate, parameters);
    }

    public Device getById(long deviceId) throws DeviceNotFoundException{
        Map<String, Long> parameters = new HashMap<String, Long>();
        parameters.put("id", deviceId);
        Device device =null;
        try{
            device = namedParameterJdbcTemplate.queryForObject(selectByIdTemplate, parameters, deviceRowMapper);
        } catch (EmptyResultDataAccessException e){
            throw new DeviceNotFoundException(String.format("Device with id '%d' was not found", deviceId));
        }
        return device;
    }

    public long save(Device device){
        MapSqlParameterSource parameters  = new MapSqlParameterSource();
        parameters.addValue("status", String.valueOf(device.getStatus()));
        parameters.addValue("name", device.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertTemplate, parameters, keyHolder);
        return (long)keyHolder.getKeys().get("id");
    }

    public boolean exists(Device device){
        MapSqlParameterSource parameters  = new MapSqlParameterSource();
        parameters.addValue("id", device.getId());
        parameters.addValue("name", device.getName());
        return namedParameterJdbcTemplate.queryForObject(existsTemplate, parameters, Boolean.class);
    }

}
