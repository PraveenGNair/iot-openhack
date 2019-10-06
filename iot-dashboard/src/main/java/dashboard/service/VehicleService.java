package dashboard.service;

import dashboard.entity.VehicleDetails;
import dashboard.entity.VehicleId;
import dashboard.repository.VehicleIdRepository;
import dashboard.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Praveen
 *
 */
@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private VehicleIdRepository vehicleIdRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    Environment environment;

    @Scheduled(fixedRate = 5000)
	public void getData() {

        List<VehicleDetails> vehicleDetailsList = new ArrayList<>();
		List<VehicleId> vehicleIdList = (List<VehicleId>) vehicleIdRepository.findAll();
		for(VehicleId id : vehicleIdList){
			List<VehicleDetails> vehicleList = (List<VehicleDetails>) vehicleRepository.findByVehicleIdOrderByTimestampLimit10(id.getVehicleId());

			vehicleDetailsList.addAll(vehicleList);
		}


        this.template.convertAndSend("/topic/vehicle", vehicleDetailsList);

	}
}
