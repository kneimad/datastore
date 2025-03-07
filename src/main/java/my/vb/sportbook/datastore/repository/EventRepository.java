package my.vb.sportbook.datastore.repository;

import my.vb.sportbook.datastore.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, Long> {

}

