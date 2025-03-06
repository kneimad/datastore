package my.vb.sportbook.datastore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.vb.sportbook.datastore.dto.MarketDTO;
import my.vb.sportbook.datastore.dto.OutcomeDTO;
import my.vb.sportbook.datastore.model.Market;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.repository.OutcomeRepository;
import my.vb.sportbook.datastore.service.CRUDProcessor;
import my.vb.sportbook.datastore.service.CacheService;
import my.vb.sportbook.datastore.service.OutcomeService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OutcomeServiceImpl extends CRUDProcessor<Outcome, MongoRepository<Outcome, Long>, OutcomeDTO> implements OutcomeService {

    private final OutcomeRepository outcomeRepository;

    public OutcomeServiceImpl(CacheService cacheService, OutcomeRepository outcomeRepository) {
        super(cacheService);
        this.outcomeRepository = outcomeRepository;
    }

    @Override
    public OutcomeRepository getRepository() {
        log.info("getRepository: OutcomeRepository");
        return outcomeRepository;
    }

    @Override
    public Outcome convertTo(OutcomeDTO dto) {
        log.info("OutcomeDTO convert");
        return dto.convertTo();
    }

    @Override
    public OutcomeDTO convertFrom(Outcome outcome) {
        return new OutcomeDTO(outcome);
    }
}
