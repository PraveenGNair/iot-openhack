package com.openhack.piedpiper.iotkafkaproducer.producer;

import com.openhack.piedpiper.iotkafkaproducer.services.KafkaProduce;
import com.openhack.piedpiper.iotkafkaproducer.model.IoTData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author praveen.nair
 */
@RestController
@RequestMapping(value="/rest")
public class IoTDataProducerRest {

    @Autowired
    KafkaProduce producer;

    @PostMapping(value="/cloudGateway/putKafka")
    public void producer(@RequestBody IoTData data) throws InterruptedException {
        producer.send(data);

    }

}

