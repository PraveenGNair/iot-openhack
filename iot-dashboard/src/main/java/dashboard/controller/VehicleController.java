package dashboard.controller;

import dashboard.entity.Message;
import dashboard.entity.VehicleDetails;
import dashboard.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by praveen.nair on 2/25/2018.
 */
@RestController
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

  //@PostMapping(value="/dataToUi")
  @SendTo("/topic/vehicle")
  @MessageMapping("/hello")
  public void getData(Message message) {
      vehicleService.getData();
  }

}
