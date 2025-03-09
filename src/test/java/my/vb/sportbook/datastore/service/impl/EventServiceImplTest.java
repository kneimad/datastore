package my.vb.sportbook.datastore.service.impl;

import my.vb.sportbook.datastore.dto.EventDTO;
import my.vb.sportbook.datastore.exception.EntityNotFoundException;
import my.vb.sportbook.datastore.model.Event;
import my.vb.sportbook.datastore.repository.EventRepository;
import my.vb.sportbook.datastore.service.CacheService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private EventServiceImpl eventServiceImpl;

    public EventServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteById_WhenEventExists() {
        Long eventId = 1L;
        String mockSport = "Football";
        EventDTO mockEventDTO = new EventDTO();
        mockEventDTO.setId(eventId);
        mockEventDTO.setSport(mockSport);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(new Event()));
        doNothing().when(eventRepository).deleteById(eventId);
        when(eventServiceImpl.convertFrom(any(Event.class))).thenReturn(mockEventDTO);

        eventServiceImpl.deleteById(eventId);

        verify(eventRepository, times(1)).findById(eventId);
        verify(cacheService, times(4)).evictCollectionCache(anyString(), anyBoolean());
        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    void testDeleteById_WhenEventDoesNotExist() {
        Long eventId = 1L;

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        eventServiceImpl.deleteById(eventId);

        verify(eventRepository, times(1)).findById(eventId);
        verify(cacheService, never()).evictCollectionCache(anyString(), anyBoolean());
        verify(eventRepository, never()).deleteById(eventId);
    }

    @Test
    void testUpdate_WhenSportChanges_ShouldEvictRelatedCaches() {
        Long eventId = 1L;
        EventDTO oldEventDTO = new EventDTO();
        oldEventDTO.setId(eventId);
        oldEventDTO.setSport("Football");
        oldEventDTO.setSettled(false);

        EventDTO newEventDTO = new EventDTO();
        newEventDTO.setId(eventId);
        newEventDTO.setSport("Basketball");
        newEventDTO.setSettled(false);

        when(eventServiceImpl.findById(eventId)).thenReturn(oldEventDTO);
        when(eventRepository.save(any(Event.class))).thenReturn(new Event());
        when(eventServiceImpl.convertFrom(any(Event.class))).thenReturn(newEventDTO);

        eventServiceImpl.update(newEventDTO);

        verify(cacheService, times(4)).evictCollectionCache(eq("Football"), anyBoolean());
        verify(cacheService, times(4)).evictCollectionCache(eq("Basketball"), anyBoolean());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void testUpdate_WhenEntityDoesNotExist_ShouldThrowException() {
        Long eventId = 1L;
        EventDTO newEventDTO = new EventDTO();
        newEventDTO.setId(eventId);
        newEventDTO.setSport("Basketball");

        when(eventServiceImpl.findById(eventId)).thenThrow(new EntityNotFoundException("Entity not found"));

        assertThatThrownBy(() -> eventServiceImpl.update(newEventDTO))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity not found");

        verify(cacheService, never()).evictCollectionCache(anyString(), anyBoolean());
        verify(eventRepository, never()).save(any(Event.class));
    }

    @Test
    void testCreate_WhenEventIsCreated_ShouldEvictNonSettledEvents() {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setSport("Football");

        Event mockEvent = new Event();
        when(eventRepository.save(any(Event.class))).thenReturn(mockEvent);
        when(eventServiceImpl.convertFrom(mockEvent)).thenReturn(eventDTO);
        when(eventServiceImpl.convertTo(eventDTO)).thenReturn(mockEvent);

        EventDTO createdEvent = eventServiceImpl.create(eventDTO);

        verify(cacheService, times(4)).evictCollectionCache(eq("Football"), anyBoolean());
        verify(eventRepository, times(1)).save(any(Event.class));
        assertThat(createdEvent).isEqualTo(eventDTO);
    }

    @Test
    void testCreate_WhenEventIsInvalid_ShouldThrowException() {
        EventDTO eventDTO = null;

        assertThatThrownBy(() -> eventServiceImpl.create(eventDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("DTO cannot be converted to an entity or id is null");

        verify(cacheService, never()).evictCollectionCache(anyString(), anyBoolean());
        verify(eventRepository, never()).save(any(Event.class));
    }

    @Test
    void testEvictNonSettledEvents_WithValidSport_ShouldEvictCaches() {
        String sport = "Football";

        doNothing().when(cacheService).evictCollectionCache(eq(sport), eq(false));
        doNothing().when(cacheService).evictCollectionCache(eq(sport), eq(true));
        doNothing().when(cacheService).evictCollectionCache(eq(null), eq(false));
        doNothing().when(cacheService).evictCollectionCache(eq(null), eq(true));

        eventServiceImpl.evictNonSettledEvents(sport);

        verify(cacheService, times(1)).evictCollectionCache(eq(sport), eq(false));
        verify(cacheService, times(1)).evictCollectionCache(eq(sport), eq(true));
        verify(cacheService, times(1)).evictCollectionCache(eq(null), eq(false));
        verify(cacheService, times(1)).evictCollectionCache(eq(null), eq(true));
    }

    @Test
    void testEvictNonSettledEvents_WithNullSport_ShouldEvictCaches() {
        String sport = null;

        doNothing().when(cacheService).evictCollectionCache(eq(null), eq(false));
        doNothing().when(cacheService).evictCollectionCache(eq(null), eq(true));

        eventServiceImpl.evictNonSettledEvents(sport);

        verify(cacheService, times(1)).evictCollectionCache(eq(null), eq(false));
        verify(cacheService, times(1)).evictCollectionCache(eq(null), eq(true));
    }

    @Test
    void testUpdate_WhenSportDoesNotChange_ShouldEvictOnlyCurrentSportCaches() {
        Long eventId = 2L;
        EventDTO oldEventDTO = new EventDTO();
        oldEventDTO.setId(eventId);
        oldEventDTO.setSport("Football");
        oldEventDTO.setSettled(false);

        EventDTO newEventDTO = new EventDTO();
        newEventDTO.setId(eventId);
        newEventDTO.setSport("Football");
        newEventDTO.setSettled(false);

        when(eventServiceImpl.findById(eventId)).thenReturn(oldEventDTO);
        when(eventRepository.save(any(Event.class))).thenReturn(new Event());
        when(eventServiceImpl.convertFrom(any(Event.class))).thenReturn(newEventDTO);

        eventServiceImpl.update(newEventDTO);

        verify(cacheService, times(4)).evictCollectionCache(eq("Football"), anyBoolean());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void testUpdate_WhenSettledChanges_ShouldEvictCaches() {
        Long eventId = 3L;
        EventDTO oldEventDTO = new EventDTO();
        oldEventDTO.setId(eventId);
        oldEventDTO.setSport("Tennis");
        oldEventDTO.setSettled(false);

        EventDTO newEventDTO = new EventDTO();
        newEventDTO.setId(eventId);
        newEventDTO.setSport("Tennis");
        newEventDTO.setSettled(true);

        when(eventServiceImpl.findById(eventId)).thenReturn(oldEventDTO);
        when(eventRepository.save(any(Event.class))).thenReturn(new Event());
        when(eventServiceImpl.convertFrom(any(Event.class))).thenReturn(newEventDTO);

        eventServiceImpl.update(newEventDTO);

        verify(cacheService, times(4)).evictCollectionCache(eq("Tennis"), anyBoolean());
        verify(eventRepository, times(1)).save(any(Event.class));
    }
}