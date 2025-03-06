package my.vb.sportbook.datastore.service;

import my.vb.sportbook.datastore.dto.MarketDTO;
import my.vb.sportbook.datastore.model.Market;
import my.vb.sportbook.datastore.repository.MarketRepository;

public abstract class MarketService extends MongoRepositoryMethods<Market, MarketRepository, MarketDTO> {
}
