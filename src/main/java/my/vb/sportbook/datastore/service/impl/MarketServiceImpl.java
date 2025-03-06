package my.vb.sportbook.datastore.service.impl;

import lombok.extern.slf4j.Slf4j;
import my.vb.sportbook.datastore.dto.MarketDTO;
import my.vb.sportbook.datastore.model.Market;
import my.vb.sportbook.datastore.repository.MarketRepository;
import my.vb.sportbook.datastore.service.CRUDProcessor;
import my.vb.sportbook.datastore.service.CacheService;
import my.vb.sportbook.datastore.service.MarketService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MarketServiceImpl extends CRUDProcessor<Market, MongoRepository<Market, Long>, MarketDTO> implements MarketService {

    private final MarketRepository marketRepository;

    public MarketServiceImpl(CacheService cacheService, MarketRepository marketRepository) {
        super(cacheService);
        this.marketRepository = marketRepository;
    }

    @Override
    public MarketRepository getRepository() {
        return marketRepository;
    }

    @Override
    public Market convertTo(MarketDTO dto) {
        log.info("MarketDTO convert");
        return dto.convertTo();
    }

    @Override
    public MarketDTO convertFrom(Market market) {
        return new MarketDTO(market);
    }
}
