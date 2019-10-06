package com.vanchutin.service;

import com.vanchutin.dao.ComponentDao;
import com.vanchutin.dao.DeviceDao;
import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class DeviceUpdaterServiceTest {

    //create Mock for daoclasses
    // set behavior
    // check or verify actions


    final int deviceId = 3;

    Device device = new Device(deviceId, "Fake ATM", Status.normal);

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
        when(deviceDao.getStatus(deviceId)).thenReturn(Status.normal);
        when(deviceDao.getById(deviceId)).thenReturn(device);
        when(componentDao.countAll(deviceId)).thenReturn(6);
        when(componentDao.countBroken(deviceId)).thenReturn(4);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Status newStatus = invocationOnMock.getArgument(1);
                device.setStatus(newStatus);
                return null;
            }
        }).when(deviceDao).updateStatus(eq(deviceId), any(Status.class));

        updaterService.updateStatus(deviceId);
        assertEquals(Status.error, device.getStatus());


    }
}