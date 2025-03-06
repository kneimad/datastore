package my.vb.sportbook.datastore.service.impl;

import lombok.RequiredArgsConstructor;
import my.vb.sportbook.datastore.dto.EventDTO;
import my.vb.sportbook.datastore.model.Event;
import my.vb.sportbook.datastore.repository.EventRepository;
import my.vb.sportbook.datastore.service.EventService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl extends EventService {

    private final EventRepository eventRepository;

    @Override
    public EventRepository getRepository() {
        return eventRepository;
    }

    @Override
    public Event convert(EventDTO dto) {
        return null;
    }
}
