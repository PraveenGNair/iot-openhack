package com.openhack.service;

import com.datastax.spark.connector.japi.CassandraJavaUtil;
import com.openhack.domain.IoTData;
import com.openhack.entity.VehicleDetails;
import com.openhack.utils.IoTDataDecoder;
import kafka.serializer.StringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import scala.Tuple2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.datastax.spark.connector.japi.CassandraStreamingJavaUtil.javaFunctions;

/**
 * Created by praveen.nair on 2/19/2018.
 */
@Service
@Slf4j
public class DataProcessor {

  @Autowired
  private static RestTemplate restTemplate;

  @Autowired
  @Qualifier("JavaStreamingBean")
  private JavaStreamingContext javaStreamingContext;

  @Value("${com.iot.app.kafka.topic}")
  private String kafkaTopic;

  @Value("${com.iot.app.kafka.zookeeper}")
  private String zookeeperConnect;

  @Value("${com.iot.app.kafka.brokerlist}")
  private String brokerList;

  @Value("${com.iot.app.cassandra.keyspace}")
  private String keyspace;

  @Value("${com.iot.app.cassandra.table}")
  private String tableName;

  @Value("${com.iot.app.ui.url}")
  private static String requestUrl;

  public void processIotData() {
    try{
      Map<String, String> kafkaParams = new HashMap<>();
      kafkaParams.put("zookeeper.connect", zookeeperConnect);
      kafkaParams.put("metadata.broker.list", brokerList);
      Set<String> topicsSet = new HashSet<>();

      topicsSet.add(kafkaTopic);

      JavaPairInputDStream<String, IoTData> directKafkaStream = KafkaUtils.createDirectStream(
              javaStreamingContext,
              String.class,
              IoTData.class,
              StringDecoder.class,
              IoTDataDecoder.class,
              kafkaParams,
              topicsSet
      );

      JavaDStream<IoTData> nonFilteredIotDataStream = directKafkaStream.map(tuple -> tuple._2());


      JavaPairDStream<String, IoTData> iotDataPairStream = nonFilteredIotDataStream.mapToPair
              (data -> new Tuple2<String, IoTData>(data.getVehicleId(), data)).reduceByKey((a,b)
              -> a);


      JavaDStream<VehicleDetails> vehicleDetails = iotDataPairStream.map(vehicleDetailsfunc);

        Map<String, String> columnNameMappings = new HashMap<String, String>();
        columnNameMappings.put("vehicleId", "vehicleid");
        columnNameMappings.put("vehicleType", "vehicletype");
        columnNameMappings.put("routeId", "routeid");
        columnNameMappings.put("longitude", "longitude");
        columnNameMappings.put("latitude", "latitude");
        columnNameMappings.put("temperature", "temperature");
        columnNameMappings.put("assetCount", "assetcount");
        columnNameMappings.put("assetDetails", "assetdetails");
        columnNameMappings.put("timestamp", "timestamp");
        columnNameMappings.put("speed", "speed");
        columnNameMappings.put("fuelLevel", "fuellevel");
        columnNameMappings.put("startLocation", "startlocation");
        columnNameMappings.put("endLocation", "endlocation");

      javaFunctions(vehicleDetails).writerBuilder(keyspace, tableName,
              CassandraJavaUtil.mapToRow(VehicleDetails.class,columnNameMappings)).saveToCassandra();
      System.out.println(javaFunctions(vehicleDetails));


      javaStreamingContext.start();
      javaStreamingContext.awaitTermination();
    } catch(Exception e){
      e.printStackTrace();
    }


  }

  private static final Function<Tuple2<String, IoTData>, VehicleDetails> vehicleDetailsfunc =
      (tuple -> {
    VehicleDetails vehicleDetails = new VehicleDetails();
    vehicleDetails.setVehicleId(tuple._2().getVehicleId());
    vehicleDetails.setVehicleType(tuple._2().getVehicleType());
    vehicleDetails.setRouteId(tuple._2().getRouteId());
    vehicleDetails.setLongitude(tuple._2().getLongitude());
    vehicleDetails.setLatitude(tuple._2().getLatitude());
    vehicleDetails.setTemperature(tuple._2().getTemperature());
    vehicleDetails.setAssetCount(tuple._2().getAssetCount());
    vehicleDetails.setAssetDetails(tuple._2().getAssetDetails().toString());
    vehicleDetails.setTimestamp(tuple._2().getTimestamp());
    vehicleDetails.setSpeed(tuple._2().getSpeed());
    vehicleDetails.setFuelLevel(tuple._2().getFuelLevel());
    vehicleDetails.setStartLocation(tuple._2().getStartLocation());
    vehicleDetails.setEndLocation(tuple._2().getEndLocation());
          //restTemplate.postForObject(requestUrl,vehicleDetails,null);
    return vehicleDetails;

  });

}
