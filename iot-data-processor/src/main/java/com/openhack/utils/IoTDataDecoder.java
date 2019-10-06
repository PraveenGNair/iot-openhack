package com.openhack.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhack.domain.IoTData;
import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;

/**
 * Class to deserialize JSON string to IoTData java object
 *
 * @author praveen.nair
 */
public class IoTDataDecoder implements Decoder<IoTData> {

  private static ObjectMapper objectMapper = new ObjectMapper();

  public IoTDataDecoder(VerifiableProperties verifiableProperties) {

  }

  public IoTData fromBytes(byte[] bytes) {
    try {
      return objectMapper.readValue(bytes, IoTData.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
