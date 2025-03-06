package my.vb.sportbook.datastore.repository;

import my.vb.sportbook.datastore.model.Market;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends MongoRepository<Market, Long> {

}
