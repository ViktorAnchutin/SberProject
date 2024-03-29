package com.vanchutin.service.rest;

import com.vanchutin.dao.DeviceDao;
import com.vanchutin.dto.DeviceDto;
import com.vanchutin.exception.DeviceNotFoundException;
import com.vanchutin.model.Device;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DeviceService {

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    ModelMapper modelMapper;

    public Optional<DeviceDto> getById(long id){
        Device device = null;
        try{
           device = deviceDao.getById(id);
        } catch (DeviceNotFoundException e){
            log.error(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(modelMapper.map(device, DeviceDto.class));
    }

    public long createDevice(DeviceDto deviceDto){
        Device device = modelMapper.map(deviceDto, Device.class);
        long generatedId =  deviceDao.save(device);
        return generatedId;
    }

    public List<DeviceDto> getAllDevices(){
        List<Device> devices = deviceDao.getAll();
        List<DeviceDto> devicesDto = devices.stream()
                                            .map(device -> modelMapper.map(device, DeviceDto.class))
                                            .collect(Collectors.toList());
        return devicesDto;
    }

    public void updateDeviceStatus(DeviceDto deviceDto){
        deviceDao.updateStatus(deviceDto.getId(), deviceDto.getStatus());
    }

    public boolean deviceExists(DeviceDto deviceDto){
        if(deviceDao.exists(modelMapper.map(deviceDto, Device.class)))
            return true;
        return false;
    }
}
