package my.vb.sportbook.datastore.controller;

import lombok.RequiredArgsConstructor;
import my.vb.sportbook.datastore.dto.EventDTO;
import my.vb.sportbook.datastore.model.Event;
import my.vb.sportbook.datastore.service.CRUDProcessor;
import my.vb.sportbook.datastore.service.EventService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController implements CRUDControllerInterface<EventDTO, Event, CRUDProcessor<Event, MongoRepository<Event, Long>, EventDTO>> {

    private final EventService eventService;

    @Override
    public EventService getService() {
        return eventService;
    }

}
