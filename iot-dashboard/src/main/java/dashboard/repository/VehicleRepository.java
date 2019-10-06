package dashboard.repository;

import dashboard.entity.VehicleDetails;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by praveen.nair on 2/25/2018.
 */
@Repository
public interface VehicleRepository extends CassandraRepository<VehicleDetails> {

    @Query(value="SELECT * FROM vehicle_details WHERE vehicleid = ?0 ORDER BY timestamp DESC LIMIT 5")
    List<VehicleDetails> findByVehicleIdOrderByTimestampLimit10(String vehicleId);
}
