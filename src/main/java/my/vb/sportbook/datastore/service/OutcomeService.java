package my.vb.sportbook.datastore.service;

import my.vb.sportbook.datastore.dto.OutcomeDTO;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.repository.OutcomeRepository;

public abstract class OutcomeService extends MongoRepositoryMethods<Outcome, OutcomeRepository, OutcomeDTO> {
}
