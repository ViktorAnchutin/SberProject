package com.vanchutin.dao;

import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDao {


    private final String selectAllTemplate = "SELECT * FROM devices";

    private final String selectStatusTemplate =     "SELECT status FROM devices " +
                                                    "WHERE id = %d";

    private final String updateStatusTemplate =     "UPDATE devices " +
                                                    "SET status = '%s' " +
                                                    "WHERE id = %d";

    private final String selectByIdTemplate =       "SELECT * FROM devices " +
                                                    "WHERE id = %d";

    private RowMapper<Device> deviceRowMapper = (rs, rowNum) -> new Device(
                                                                        rs.getInt("id"),
                                                                        rs.getString("name"),
                                                                        Status.valueOf( rs.getString("status"))
                                                                ) ;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Device> getAll(){
        return jdbcTemplate.query( selectAllTemplate, deviceRowMapper);
    }

    public Status getStatus(int deviceId){
        return jdbcTemplate.queryForObject(String.format(selectStatusTemplate, deviceId), Status.class);
    }

    public void updateStatus(int deviceId, Status newStatus){
        jdbcTemplate.update(String.format(updateStatusTemplate, String.valueOf(newStatus), deviceId));
    }

    public Device getById(int deviceId){
        return jdbcTemplate.queryForObject(String.format(selectByIdTemplate, deviceId), deviceRowMapper);
    }



}
