package my.vb.sportbook.datastore.service.impl;

import lombok.RequiredArgsConstructor;
import my.vb.sportbook.datastore.dto.MarketDTO;
import my.vb.sportbook.datastore.model.Market;
import my.vb.sportbook.datastore.repository.MarketRepository;
import my.vb.sportbook.datastore.service.MarketService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl extends MarketService {

    private final MarketRepository marketRepository;

    @Override
    public MarketRepository getRepository() {
        return marketRepository;
    }

    @Override
    public Market convert(MarketDTO dto) {
        return null;
    }
}
