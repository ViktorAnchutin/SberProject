package com.vanchutin.model.mapper;

import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DeviceMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        return new Device(
                rs.getInt("id"),
                rs.getString("name"),
                Status.valueOf( rs.getString("status")));
    }
}
