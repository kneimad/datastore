package my.vb.sportbook.datastore.repository;

import my.vb.sportbook.datastore.model.EventHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventHistoryRepository extends MongoRepository<EventHistory, Long> {

}

