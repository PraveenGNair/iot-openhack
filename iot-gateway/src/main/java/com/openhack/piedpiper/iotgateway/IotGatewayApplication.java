package com.openhack.piedpiper.iotgateway;

import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.RingbufferConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@IntegrationComponentScan
@EnableCaching
@EnableScheduling
public class IotGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotGatewayApplication.class, args);
	}


	@Bean
	HazelcastInstance create (){
		RingbufferConfig rbConfig = new RingbufferConfig("rb")
				.setCapacity(10000)
				.setBackupCount(1)
				.setAsyncBackupCount(0)
				.setTimeToLiveSeconds(0)
				.setInMemoryFormat(InMemoryFormat.BINARY);
		Config config = new Config();
		config.addRingBufferConfig(rbConfig);

		HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
		return hz;
	}

	@Bean
	RestTemplate createRestTemplate(){
		return new RestTemplate();
	}
}
