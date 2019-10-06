package com.openhack.piedpiper.iotdataproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author praveen.nair
 */
@RestController
@RequestMapping(value="/rest")
public class DataGeneratorRest {

    @Autowired
    IoTDataGenerator ioTDataGenerator;

    @GetMapping(value="/producer/start")
    public void producer() throws InterruptedException {
        ioTDataGenerator.generateIoTEvent();

    }
    
    @GetMapping(value="/pingStatus")
    public String ping() {
        return "Status ok .200";

    }


}

