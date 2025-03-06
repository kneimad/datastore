package my.vb.sportbook.datastore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.vb.sportbook.datastore.dto.OutcomeDTO;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.repository.OutcomeRepository;
import my.vb.sportbook.datastore.service.OutcomeService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutcomeServiceImpl extends OutcomeService {

    private final OutcomeRepository outcomeRepository;

    @Override
    public OutcomeRepository getRepository() {
        log.info("getRepository: OutcomeRepository");
        return outcomeRepository;
    }

    @Override
    public Outcome convert(OutcomeDTO dto) {
        log.info("OutcomeDTO convert");
        return dto.convertTo();
    }
}
