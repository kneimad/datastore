package my.vb.sportbook.datastore.repository;

import my.vb.sportbook.datastore.model.Outcome;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends MongoRepository<Outcome, Long> {

}
