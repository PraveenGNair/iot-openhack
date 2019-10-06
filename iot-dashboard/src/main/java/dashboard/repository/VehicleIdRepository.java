package dashboard.repository;

import dashboard.entity.VehicleDetails;
import dashboard.entity.VehicleId;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * Created by praveen.nair on 3/4/2018.
 */
public interface VehicleIdRepository extends CassandraRepository<VehicleId> {
}
