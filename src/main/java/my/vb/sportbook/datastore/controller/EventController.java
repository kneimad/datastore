package my.vb.sportbook.datastore.controller;

import lombok.RequiredArgsConstructor;
import my.vb.sportbook.datastore.service.MongoRepositoryMethods;
import my.vb.sportbook.datastore.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController implements CRUDControllerInterface {

    private final EventService eventService;

    @Override
    public MongoRepositoryMethods getService() {
        return eventService;
    }
}
