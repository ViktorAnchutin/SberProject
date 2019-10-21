package com.vanchutin.service;


import com.vanchutin.dao.ComponentDao;
import com.vanchutin.dao.DeviceDao;
import com.vanchutin.exception.DeviceNotFoundException;
import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceUpdaterService {

    @Autowired  @Setter
    private DeviceDao deviceDao;

    @Autowired  @Setter
    private ComponentDao componentDao;


    public void updateStatus(int deviceId){

        int allComponents = componentDao.countAll(deviceId);
        int brokenComponents = componentDao.countBroken(deviceId);

        Status newStatus = computeStatus(allComponents, brokenComponents);
        Status currentStatus = deviceDao.getStatus(deviceId);

        if(currentStatus != newStatus) {
            deviceDao.updateStatus(deviceId, newStatus);
            Device device = null;
            try {
                device = deviceDao.getById(deviceId);
            } catch (DeviceNotFoundException e){
                log.error(e.getMessage());
                return;
            }
            printStatus(device);
        }
    }

    private Status computeStatus(int allComponents, int brokenComponents){
        if(allComponents/2 < brokenComponents)
            return Status.error;

        if (brokenComponents != 0)
            return Status.warning;

        return Status.normal;
    }


    private void printStatus(Device device){
        log.info("Устройство id = {}, name = {} изменило свое состояние на {} ",
                device.getId(), device.getName(), device.getStatus()) ;
    }
}
