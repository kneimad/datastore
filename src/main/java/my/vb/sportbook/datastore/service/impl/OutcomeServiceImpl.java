package my.vb.sportbook.datastore.service.impl;

import lombok.RequiredArgsConstructor;
import my.vb.sportbook.datastore.dto.OutcomeDTO;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.repository.OutcomeRepository;
import my.vb.sportbook.datastore.service.OutcomeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutcomeServiceImpl implements OutcomeService {

    private final OutcomeRepository outcomeRepository;

    @Override
    public OutcomeRepository getRepository() {
        return outcomeRepository;
    }

    @Override
    public Outcome convert(OutcomeDTO dto) {
        return dto.convertTo();
    }
}
