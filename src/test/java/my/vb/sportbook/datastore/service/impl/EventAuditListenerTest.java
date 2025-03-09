package my.vb.sportbook.datastore.service.impl;

import my.vb.sportbook.datastore.model.Event;
import my.vb.sportbook.datastore.model.EventHistory;
import my.vb.sportbook.datastore.repository.EventHistoryRepository;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@DataMongoTest
public class EventAuditListenerTest {

    @Autowired
    private EventAuditListener eventAuditListener;

    @Mock
    private EventHistoryRepository eventHistoryRepository;

    /**
     * Test to verify that onBeforeDelete method creates and saves an EventHistory entity with the correct attributes.
     */
    @Test
    void testOnBeforeDeleteCreatesEventHistory() {
        // Arrange
        Long eventId = 123L;
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("_id", eventId);

        BeforeDeleteEvent<Event> beforeDeleteEvent = new BeforeDeleteEvent<>((Document) sourceMap, Event.class, "events");

        ArgumentCaptor<EventHistory> captor = ArgumentCaptor.forClass(EventHistory.class);

        eventAuditListener.onBeforeDelete(beforeDeleteEvent);

        verify(eventHistoryRepository).save(captor.capture());
        EventHistory savedEventHistory = captor.getValue();

        assertEquals(eventId, savedEventHistory.getEventId());
        assertEquals("DELETE", savedEventHistory.getChangeType());
        assertEquals(Instant.now().getEpochSecond(), savedEventHistory.getChangeTimestamp().getEpochSecond(), 1);
    }

    /**
     * Test to verify that onBeforeSave method creates and saves an EventHistory entity with the correct attributes.
     */
    @Test
    void testOnBeforeSaveCreatesEventHistory() {
        // Arrange
        Event event = Event.builder()
                .id(123L)
                .description("Event Description")
                .homeTeam("Team A")
                .awayTeam("Team B")
                .startTime(Instant.now())
                .sport("Football")
                .country("Country A")
                .competition("Competition A")
                .settled(false)
                .updatedDateTime(Instant.now())
                .updatedUser("testUser")
                .build();

        BeforeSaveEvent<Event> beforeSaveEvent = new BeforeSaveEvent<>(event, null, "events");

        ArgumentCaptor<EventHistory> captor = ArgumentCaptor.forClass(EventHistory.class);

        // Act
        eventAuditListener.onBeforeSave(beforeSaveEvent);

        // Assert
        verify(eventHistoryRepository).save(captor.capture());
        EventHistory savedEventHistory = captor.getValue();

        assertEquals(event.getId(), savedEventHistory.getEventId());
        assertEquals(event.getDescription(), savedEventHistory.getDescription());
        assertEquals(event.getHomeTeam(), savedEventHistory.getHomeTeam());
        assertEquals(event.getAwayTeam(), savedEventHistory.getAwayTeam());
        assertEquals(event.getStartTime(), savedEventHistory.getStartTime());
        assertEquals(event.getSport(), savedEventHistory.getSport());
        assertEquals(event.getCountry(), savedEventHistory.getCountry());
        assertEquals(event.getCompetition(), savedEventHistory.getCompetition());
        assertEquals(event.isSettled(), savedEventHistory.getSettled());
        assertEquals(event.getUpdatedDateTime(), savedEventHistory.getUpdatedDateTime());
        assertEquals(event.getUpdatedUser(), savedEventHistory.getUpdatedUser());
        assertEquals("UPDATE", savedEventHistory.getChangeType());
    }
}