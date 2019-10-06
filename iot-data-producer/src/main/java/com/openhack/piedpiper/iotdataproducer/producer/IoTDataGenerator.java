package com.openhack.piedpiper.iotdataproducer.producer;


import com.openhack.piedpiper.iotdataproducer.model.Asset;
import com.openhack.piedpiper.iotdataproducer.model.IoTData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author praveen.nair
 */
@Component
public class IoTDataGenerator {

    private static final Logger logger = LoggerFactory.getLogger(IoTDataGenerator.class);

    @Value("${com.iot-gateway.url}")
    String gatewayURL;

    /**
     * Method runs in while loop and generates random IoT data in JSON with below format.
     *
     * {"vehicleId":"52f08f03-cd14-411a-8aef-ba87c9a99997","vehicleType":"Public Transport","routeId":"route-43","latitude":",-85.583435","longitude":"38.892395","timestamp":1465471124373,"speed":80.0,"fuelLevel":28.0}
     *
     * @throws InterruptedException
     *
     *
     */
    public  void generateIoTEvent() throws InterruptedException {
        List<String> routeList = Arrays.asList(new String[]{"Route-37", "Route-43", "Route-82", "Route-56", "Route-32"});
        List<String> vehicleTypeList = Arrays.asList(new String[]{"Log Carrier Truck", "Tank Truck", "Refrigerator Truck", "Mini Truck", "Truck"});
        List<String> vehicleIdList = Arrays.asList(new String[]{"v1", "v2", "v3", "v4", "v5"});
        List<String> vehicleStartLocation=Arrays.asList(new String[]{"Pune","Chennai","Delhi","Chandigarh","Raipur"});
        List<String> vehicleEndLocation=Arrays.asList(new String[]{"Mumbai","Hyderabad","Goa","Jaipur","Indore"});
        Random rand = new Random();
        logger.info("Sending events");
// generate event in loop
        while (true) {
            List<IoTData> eventList = new ArrayList<IoTData>();
            for (int i = 0; i < 5; i++) {// create 10 vehicles
                String vehicleId = vehicleIdList.get(i);
                String vehicleType = vehicleTypeList.get(i);
                String startLocation=vehicleStartLocation.get(i);
                String endLocation=vehicleEndLocation.get(i);

                String routeId = routeList.get(i);
                Date timestamp = new Date();
                double speed = rand.nextInt(100 - 20) + 20;// random speed between 20 to 100
                double fuelLevel = 50 - rand.nextInt(40 - 10)/10;
                long assetCount=3;
                Asset asset1=new Asset(UUID.randomUUID().toString(),rand.nextInt(10),rand.nextInt(10),rand.nextInt(10),
                        rand.nextInt(30-20)+20,"location-" );
                Asset asset2=new Asset(UUID.randomUUID().toString(),rand.nextInt(10),rand.nextInt(10),rand.nextInt(10),
                        rand.nextInt(30-20)+20,"location-" );
                Asset asset3=new Asset(UUID.randomUUID().toString(),rand.nextInt(10),rand.nextInt(10),rand.nextInt(10),
                        rand.nextInt(30-20)+20,"location-" );

                for (int j = 0; j < 5; j++) {// Add 5 events for each vehicle
                    fuelLevel-= rand.nextInt(2);
                    String coords = getCoordinates(routeId);
                    double temperature=rand.nextInt(50-20)+20;
                    String latitude = coords.substring(0, coords.indexOf(","));
                    String longitude = coords.substring(coords.indexOf(",") + 1, coords.length());
                    IoTData event = new IoTData(vehicleId, vehicleType, routeId, latitude, longitude,temperature,assetCount,assetCount==3?Arrays.asList(asset1,asset2,asset3):assetCount==2?Arrays.asList(asset1,asset2):Arrays.asList(asset1),
                            timestamp, speed,fuelLevel,startLocation,endLocation);
                    if(j==4||j==5){
                        event.setError("error to be filtered by Hazelcast Engine");
                    }
                    eventList.add(event);
                }
            }
            //Collections.shuffle(eventList);// shuffle for random events
            for (IoTData event : eventList) {
                //KeyedMessage<String, IoTData> data = new KeyedMessage<String, IoTData>(topic, event);
                // producer.send(event);
                RestTemplate restTemplate=new RestTemplate();
                logger.info(event.toString());
                restTemplate.postForEntity( gatewayURL+"/rest/gateway", event , IoTData.class );
                Thread.sleep(rand.nextInt(3000 - 1000) + 1000);//random delay of 1 to 3 seconds
            }
        }
    }

    //Method to generate random latitude and longitude for routes
    private String  getCoordinates(String routeId) {
        Random rand = new Random();
        int latPrefix = 0;
        int longPrefix = -0;
        if (routeId.equals("Route-37")) {
            latPrefix = 33;
            longPrefix = -96;
        } else if (routeId.equals("Route-82")) {
            latPrefix = 34;
            longPrefix = -97;
        } else if (routeId.equals("Route-43")) {
            latPrefix = 35;
            longPrefix = -98;
        }
        Float lati = latPrefix + rand.nextFloat();
        Float longi = longPrefix + rand.nextFloat();
        return lati + "," + longi;
    }
}
