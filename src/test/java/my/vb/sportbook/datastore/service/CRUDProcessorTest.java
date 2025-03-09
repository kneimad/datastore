//package my.vb.sportbook.datastore.service;
//
//import my.vb.sportbook.datastore.dto.EventDTO;
//import my.vb.sportbook.datastore.model.IndexedEntity;
//import my.vb.sportbook.datastore.repository.EventRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class CRUDProcessorTest {
//
//    @Autowired
//    private TestCRUDProcessor crudProcessor;
//
//    @MockBean
//    private EventRepository eventRepository;
//
//    // A custom implementation for testing purposes
//    static class TestCRUDProcessor extends CRUDProcessor<IndexedEntity, EventRepository, EventDTO> {
//
//        public TestCRUDProcessor(EventRepository repository) {
//            super(null); // Set the CacheService to null for this test
//            this.repository = repository;
//        }
//
//        private final EventRepository repository;
//
//        @Override
//        public EventRepository getRepository() {
//            return repository;
//        }
//
//        @Override
//        public IndexedEntity convertTo(EventDTO dto) {
//            IndexedEntity entity = new IndexedEntity();
//            entity.setId(dto.getId());
//            entity.setName(dto.getName());
//            entity.setCreatedAt(dto.getCreatedAt());
//            return entity;
//        }
//
//        @Override
//        public EventDTO convertFrom(IndexedEntity entity) {
//            EventDTO dto = new EventDTO();
//            dto.setId(entity.getId());
//            dto.setName(entity.getName());
//            dto.setCreatedAt(entity.getCreatedAt());
//            return dto;
//        }
//    }
//
//    @Test
//    void testCreate_shouldSaveEntityAndReturnDTO() {
//        // Given
//        EventDTO eventDTO = new EventDTO();
//        eventDTO.setId(1L);
//        eventDTO.setName("Test Event");
//        eventDTO.setCreatedAt(System.currentTimeMillis());
//
//        IndexedEntity entity = new IndexedEntity();
//        entity.setId(eventDTO.getId());
//        entity.setName(eventDTO.getName());
//        entity.setCreatedAt(eventDTO.getCreatedAt());
//
//        when(eventRepository.save(any(IndexedEntity.class))).thenReturn(entity);
//
//        // When
//        EventDTO createdDTO = crudProcessor.create(eventDTO);
//
//        // Then
//        assertThat(createdDTO).isNotNull();
//        assertThat(createdDTO.getId()).isEqualTo(eventDTO.getId());
//        assertThat(createdDTO.getName()).isEqualTo(eventDTO.getName());
//        assertThat(createdDTO.getCreatedAt()).isEqualTo(eventDTO.getCreatedAt());
//
//        // Verify interaction with repository
//        Mockito.verify(eventRepository).save(any(IndexedEntity.class));
//    }
//
//}