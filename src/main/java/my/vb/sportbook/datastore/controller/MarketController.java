package my.vb.sportbook.datastore.controller;

import lombok.RequiredArgsConstructor;
import my.vb.sportbook.datastore.dto.MarketDTO;
import my.vb.sportbook.datastore.model.Market;
import my.vb.sportbook.datastore.service.MongoRepositoryMethods;
import my.vb.sportbook.datastore.service.MarketService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController implements CRUDControllerInterface<MarketDTO, Market, MongoRepositoryMethods<Market, MongoRepository<Market, Long>, MarketDTO>>{

    private final MarketService marketService;


    @Override
    public MongoRepositoryMethods getService() {
        return marketService;
    }
}
