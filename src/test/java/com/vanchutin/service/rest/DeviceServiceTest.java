package com.vanchutin.service.rest;

import com.vanchutin.annotation.ResourceSql;
import com.vanchutin.annotation.ResourceSqlAnnotationBeanPostProcessor;
import com.vanchutin.dto.DeviceDto;
import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceTest {

   // @Autowired
   // private ResourceSqlAnnotationBeanPostProcessor resourceSqlAnnotationBeanPostProcessor;

    @Autowired
    DeviceService deviceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ResourceSql("createTestDevicesTable.sql")
    private static String createTestTableQuery;

    @ResourceSql("dropDevicesTable.sql")
    private static String dropDevicesTable;

    @Before
    public void createTable(){
      jdbcTemplate.execute(createTestTableQuery);

    }

    @After
    public void dropTable(){
        jdbcTemplate.execute(dropDevicesTable);
    }

    @Test
    public void getById() {
        String name = deviceService.getById(1).getName();
        assertEquals("ATM1", name);
    }

    @Test
    public void createDevice() {
        deviceService.createDevice(new DeviceDto("ATM create", Status.normal));
        long result = jdbcTemplate.queryForObject("select count(*) from devices where name = 'ATM create';", Long.class);
        assertEquals(1L, result);
    }

    @Test
    public void getAllDevices()
    {
        List<DeviceDto> devices = deviceService.getAllDevices();
        assertEquals(3, devices.size());
    }

    @Test
    public void updateDeviceStatus() {
        deviceService.updateDeviceStatus(new DeviceDto(1, Status.error));
        Status resultStatus = jdbcTemplate.queryForObject("select status from devices where id = 1", Status.class);
        assertEquals(Status.error, resultStatus);
    }

    @Test
    public void deviceExists() {
        boolean result = deviceService.deviceExists(new DeviceDto(1));
        assertEquals(true, result);
    }
}