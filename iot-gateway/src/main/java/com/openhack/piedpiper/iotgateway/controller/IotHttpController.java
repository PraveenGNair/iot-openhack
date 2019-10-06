package com.openhack.piedpiper.iotgateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.ringbuffer.Ringbuffer;
import com.openhack.piedpiper.iotgateway.model.IotData;
import com.openhack.piedpiper.iotgateway.model.IotFilterData;
import com.openhack.piedpiper.iotgateway.service.IotCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by praveen.nair on 25/2/18.
 */
@RestController
@RequestMapping("/rest/gateway")
public class IotHttpController {

    private static Logger log = LoggerFactory.getLogger(IotHttpController.class);
    @Autowired
    HazelcastInstance hazelcastInstance;

    @Autowired
    RestTemplate restTemplate;

    @Value("${com.iot-cloud-gateway.url}")
    String cloudGatewayURL;

    @Autowired
    IotCacheService iotCacheService;

    @PostMapping
    public void handleHttpRest(@RequestBody IotData iotData) throws InterruptedException, ExecutionException, IOException {
        log.info("received "+iotData.toString());
        Ringbuffer<IotData> ringbuffer = hazelcastInstance.getRingbuffer("iot-ring");
        long sequence = ringbuffer.add(iotData);
        IotData iotData1= ringbuffer.readOne(sequence);
        postDataToCloudGateway(filterData(iotData1));
    }

    private IotFilterData filterData(IotData iotData) throws IOException {

        ObjectMapper mapper;
        mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("error");
        FilterProvider filters = new SimpleFilterProvider().addFilter("errorFilter", theFilter);
        String dtoAsString = mapper.writer(filters).writeValueAsString(iotData);
        return  mapper.readValue(dtoAsString, IotFilterData.class);
    }

    private void postDataToCloudGateway(IotFilterData iotFilterData) throws InterruptedException, ExecutionException
    {
        restTemplate.postForEntity( cloudGatewayURL+"/rest/cloudGateway/putKafka", iotFilterData , IotFilterData.class );
    }


}
