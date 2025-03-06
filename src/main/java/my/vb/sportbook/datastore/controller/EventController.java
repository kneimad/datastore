package my.vb.sportbook.datastore.controller;

import lombok.RequiredArgsConstructor;
import my.vb.sportbook.datastore.service.CacheService;
import my.vb.sportbook.datastore.service.EventService;
import my.vb.sportbook.datastore.service.MarketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController implements CRUDControllerInterface {

    private final EventService eventService;

    @Override
    public EventService getService() {
        return eventService;
    }

}
