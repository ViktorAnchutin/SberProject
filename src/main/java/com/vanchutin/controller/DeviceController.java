package com.vanchutin.controller;


import com.vanchutin.dto.DeviceDto;
import com.vanchutin.model.Device;
import com.vanchutin.model.utils.Status;
import com.vanchutin.service.rest.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sun.security.provider.certpath.OCSPResponse;

import java.util.List;

@RestController
@RequestMapping("devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping(produces = "application/json")
    public List<DeviceDto> getAll(){
        return deviceService.getAllDevices();
    }

    @PostMapping(produces = "application/json")
    public DeviceDto createDevice(@RequestParam(name = "name") String name, @RequestParam(name = "status") Status status){
        if(deviceService.deviceExists(new DeviceDto(name)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "device already exist");
        DeviceDto deviceDto = new DeviceDto(name, status);
        long deviceId = deviceService.createDevice(deviceDto);
        deviceDto.setId(deviceId);
        return deviceDto;
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public DeviceDto getDevice(@PathVariable int id){
         if(deviceService.deviceExists(new DeviceDto(id)))
            return deviceService.getById(id);
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found");
    }

    @PutMapping(value = "{id}", produces = "application/json")
    public void updateStatus(@PathVariable int id, @RequestParam(name = "status") Status status){
        if(!deviceService.deviceExists(new DeviceDto(id)))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found");

        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(id);
        deviceDto.setStatus(status);
        deviceService.updateDeviceStatus(deviceDto);
    }





}
