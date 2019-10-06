package dashboard.entity;

import lombok.Data;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by praveen.nair on 3/4/2018.
 */
@Data
@Table("vehicle_ids")
public class VehicleId {
    @Id
    private String vehicleId;
    @Column("timestamp")
    private Date timestamp;

}
