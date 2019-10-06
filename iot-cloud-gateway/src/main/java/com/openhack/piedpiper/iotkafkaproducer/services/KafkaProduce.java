package com.openhack.piedpiper.iotkafkaproducer.services;

import com.openhack.piedpiper.iotkafkaproducer.model.IoTData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
/**
 * @author praveen.nair
 */
@Service
public class KafkaProduce {
    private static final Logger log = LoggerFactory.getLogger(KafkaProduce.class);

    @Autowired
    private KafkaTemplate<String, IoTData> kafkaTemplate;

    @Value("${com.app.kafka.topic}")
    String kafkaTopic;

    public void send(IoTData data) {
        log.info("sending data='{}'", data.toString());
        kafkaTemplate.send(kafkaTopic,"iot-data-key", data);
    }
}
