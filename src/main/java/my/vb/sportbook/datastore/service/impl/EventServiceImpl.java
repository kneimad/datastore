package my.vb.sportbook.datastore.service.impl;

import lombok.extern.slf4j.Slf4j;
import my.vb.sportbook.datastore.dto.EventDTO;
import my.vb.sportbook.datastore.model.Event;
import my.vb.sportbook.datastore.repository.EventRepository;
import my.vb.sportbook.datastore.service.CRUDProcessor;
import my.vb.sportbook.datastore.service.CacheService;
import my.vb.sportbook.datastore.service.EventService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class EventServiceImpl extends CRUDProcessor<Event, MongoRepository<Event, Long>, EventDTO> implements EventService {

    private final EventRepository eventRepository;
    private final CacheService cacheService;

    public EventServiceImpl(CacheService cacheService, EventRepository eventRepository) {
        super(cacheService);
        this.cacheService = cacheService;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventRepository getRepository() {
        return eventRepository;
    }

    @Override
    public Event convertTo(EventDTO dto) {
        log.info("EventDTO convert to Event");
        return dto.convertTo();
    }

    @Override
    public EventDTO convertFrom(Event event) {
        log.info("Event convert to EventDTO");
        return new EventDTO(event);
    }

    protected void evictNonSettledEvents(String sport) {
        cacheService.evictCollectionCache(sport, false);
        cacheService.evictCollectionCache(null, false);
        cacheService.evictCollectionCache(sport, true);
        cacheService.evictCollectionCache(null, true);
    }

    @Override
    public EventDTO create(EventDTO dto) {
        log.info("EventDTO create");
        evictNonSettledEvents(dto.getSport());
        return super.create(dto);
    }

    @Override
    public EventDTO update(EventDTO dto) {
        log.info("EventDTO update");
        EventDTO oldEntity = findById(dto.getId());
        if (isEvictedDiringUpdate(dto, oldEntity)) {
            log.info("Evicting non settled events for sport {}", dto.getSport());
            if(!oldEntity.getSport().equals(dto.getSport())){
                log.info("Evicting non settled events for updated sport {}", oldEntity.getSport());
                evictNonSettledEvents(oldEntity.getSport());
            }
            evictNonSettledEvents(dto.getSport());
        }
        return super.update(dto);
    }

    private static boolean isEvictedDiringUpdate(EventDTO dto, EventDTO oldEntity) {
        return oldEntity != null && (oldEntity.isSettled() != dto.isSettled()) || (!oldEntity.getSport().equals(dto.getSport()));
    }

    @Override
    public void deleteById(Long id) {
        log.info("EventDTO deleteById");
        Optional.ofNullable(findById(id)).ifPresent(eventDTO -> evictNonSettledEvents(eventDTO.getSport()));
        super.deleteById(id);
    }
}
