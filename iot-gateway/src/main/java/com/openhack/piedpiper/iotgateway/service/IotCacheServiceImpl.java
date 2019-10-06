package com.openhack.piedpiper.iotgateway.service;

import com.hazelcast.core.HazelcastInstance;
import com.openhack.piedpiper.iotgateway.model.IotData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by praveen.nair on 25/2/18.
 */

@Service
public class IotCacheServiceImpl implements IotCacheService {

    private static Logger log = LoggerFactory.getLogger(IotCacheServiceImpl.class);

    @Autowired
    HazelcastInstance hazelcastInstance;


    @Override
    public IotData cacheIotData(IotData iotData) {
        hazelcastInstance.getMap("iot-data").put("ioTHazel", iotData);
        log.info("Executing: " + this.getClass().getSimpleName() + ".store(\"" + iotData + "\");");
        return iotData;
    }



}
