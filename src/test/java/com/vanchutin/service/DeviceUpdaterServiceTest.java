package com.vanchutin.service;

import com.vanchutin.dao.ComponentDao;
import com.vanchutin.dao.DeviceDao;
import com.vanchutin.exception.DeviceNotFoundException;
import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@Slf4j
public class DeviceUpdaterServiceTest {

    DeviceDao deviceDao = mock(DeviceDao.class);

    ComponentDao componentDao = mock(ComponentDao.class);

    DeviceUpdaterService updaterService = new DeviceUpdaterService();


    @Before
    public void init(){
        updaterService.setDeviceDao(deviceDao);
        updaterService.setComponentDao(componentDao);
    }


    @Test
    public void updateStatus() {

        final int deviceId = 3;
        final int brokenComponents = 4;
        final int allComponents = 6;

        Device device = new Device(deviceId, "Fake ATM", Status.normal);

        when(deviceDao.getStatus(deviceId)).thenReturn(Status.normal);
        try{
            when(deviceDao.getById(deviceId)).thenReturn(device);
        } catch (DeviceNotFoundException e){
            log.error(e.getMessage());
        }
        when(componentDao.countAll(deviceId)).thenReturn(allComponents);
        when(componentDao.countBroken(deviceId)).thenReturn(brokenComponents);
        doAnswer(invocationOnMock -> {
                            device.setStatus(invocationOnMock.getArgument(1));
                            return null;
        }).when(deviceDao).updateStatus(eq(deviceId), any(Status.class));

        //pass a device
        updaterService.updateStatus(deviceId);
        //check if device status is changed properly
        verify(deviceDao, times(1)).updateStatus(deviceId, Status.error);
    }
}