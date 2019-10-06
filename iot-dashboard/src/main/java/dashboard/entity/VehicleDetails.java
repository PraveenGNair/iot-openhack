package dashboard.entity;

import lombok.Data;

import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by praveen.nair on 2/23/2018.
 */
@Data
@Table("vehicle_details")
public class VehicleDetails {
    @PrimaryKeyColumn(name = "vehicleid",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String vehicleId;
    @Column("vehicletype")
    private String vehicleType;
    @Column("routeid")
    private String routeId;
    private String longitude;
    private String latitude;
    private double temperature;
    @Column("assetcount")
    private long assetCount;
    @Column("assetdetails")
    private String assetDetails;
    @PrimaryKeyColumn(name = "timestamp",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private Date timestamp;
    private double speed;
    @Column("fuellevel")
    private double fuelLevel;
    @Column("startlocation")
    private String startLocation;
    @Column("endlocation")
    private String endLocation;
}
