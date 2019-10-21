package com.vanchutin.controller;


import com.vanchutin.dto.DeviceDto;
import com.vanchutin.model.utils.Status;
import com.vanchutin.service.rest.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("devices")
@Slf4j
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(){
        List<DeviceDto> devices = deviceService.getAllDevices();
        return ResponseEntity.ok().body(devices);
    }

    @PostMapping
    public ResponseEntity createDevice(@RequestParam(name = "name") String name, @RequestParam(name = "status") Status status){
        if(deviceService.deviceExists(new DeviceDto(name)))
            return ResponseEntity.badRequest()
                                    .header(MediaType.TEXT_PLAIN_VALUE)
                                    .body(String.format("Device with name '%s' already exist", name));
        DeviceDto deviceDto = new DeviceDto(name, status);
        long deviceId = deviceService.createDevice(deviceDto);
        deviceDto.setId(deviceId);
        return ResponseEntity.ok().header(MediaType.APPLICATION_JSON_VALUE).body(deviceDto);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity getDevice(@PathVariable int id) {
        Optional<DeviceDto> deviceOptional = null;
        if ((deviceOptional = deviceService.getById(id)).isPresent()) {
            return ResponseEntity.ok().header(MediaType.APPLICATION_JSON_VALUE).body(deviceOptional.get());
        }
        return ResponseEntity.badRequest()
                .header(MediaType.TEXT_PLAIN_VALUE)
                .body(String.format("Device with id '%d' does not exist", id));
    }


    @PutMapping(value = "{id}")
    public ResponseEntity updateStatus(@PathVariable int id, @RequestParam(name = "status") Status status){
        if(!deviceService.deviceExists(new DeviceDto(id))) {
            return ResponseEntity.badRequest()
                    .header(MediaType.TEXT_PLAIN_VALUE)
                    .body(String.format("Device with id '%d' does not exist", id));
        }

        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(id);
        deviceDto.setStatus(status);
        deviceService.updateDeviceStatus(deviceDto);
        return ResponseEntity.ok().header(MediaType.APPLICATION_JSON_VALUE).body(deviceDto);
    }
}
