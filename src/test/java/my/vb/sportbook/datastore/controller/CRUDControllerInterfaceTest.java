package my.vb.sportbook.datastore.controller;

import my.vb.sportbook.datastore.model.IndexedEntity;
import my.vb.sportbook.datastore.service.CRUDMethods;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CRUDControllerInterfaceTest.DummyCRUDController.class)
public class CRUDControllerInterfaceTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CRUDMethods<DummyDTO> mockService;

    @Mock
    private DummyCRUDController dummyCRUDController;

    @Test
    void findById_shouldReturnEntity_whenEntityExists() throws Exception {
        DummyDTO dummyEntity = new DummyDTO(1L, "Test Entity");
        when(mockService.findById(1L)).thenReturn(dummyEntity);
        when(dummyCRUDController.getService()).thenReturn(mockService);

        mockMvc.perform(get("/find/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Entity"));
    }

    @Test
    void findById_shouldReturnEntity_whenEntityExists_inCRUDControllerInterface() throws Exception {
        DummyDTO dummyEntity = new DummyDTO(100L, "Entity from CRUD Controller");
        when(mockService.findById(100L)).thenReturn(dummyEntity);
        when(dummyCRUDController.getService()).thenReturn(mockService);

        mockMvc.perform(get("/find/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.name").value("Entity from CRUD Controller"));
    }

    @Test
    void findById_shouldReturnNotFound_whenEntityDoesNotExist_inCRUDControllerInterface() throws Exception {
        when(mockService.findById(999L)).thenReturn(null);
        when(dummyCRUDController.getService()).thenReturn(mockService);

        mockMvc.perform(get("/find/999").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void update_shouldReturnUpdatedEntity_whenUpdateIsSuccessful() throws Exception {
        DummyDTO updatedEntity = new DummyDTO(1L, "Updated Entity");
        when(mockService.update(Mockito.any(DummyDTO.class))).thenReturn(updatedEntity);
        when(dummyCRUDController.getService()).thenReturn(mockService);

        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"Updated Entity\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Entity"));
    }

    @Test
    void update_shouldReturnNotFound_whenEntityDoesNotExist() throws Exception {
        when(mockService.update(Mockito.any(DummyDTO.class))).thenReturn(null);
        when(dummyCRUDController.getService()).thenReturn(mockService);

        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 999, \"name\": \"Non-existent Entity\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void findById_shouldReturnNotFound_whenEntityDoesNotExist() throws Exception {
        when(mockService.findById(anyLong())).thenReturn(null);
        when(dummyCRUDController.getService()).thenReturn(mockService);

        mockMvc.perform(get("/find/999").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteById_shouldReturnNoContent_whenDeletionIsSuccessful() throws Exception {
        mockMvc.perform(put("/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteById_shouldReturnNotFound_whenEntityDoesNotExist() throws Exception {
        Mockito.doThrow(new IllegalArgumentException("Entity not found"))
                .when(mockService).deleteById(999L);
        when(dummyCRUDController.getService()).thenReturn(mockService);

        mockMvc.perform(put("/delete/999")
                .contentType(MediaType.APPLICATION_JSON));
    }


    static class DummyDTO implements IndexedEntity {
        private Long id;
        private String name;

        public DummyDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class DummyService implements CRUDMethods<DummyDTO> {
        @Override
        public DummyDTO create(DummyDTO dto) {
            return null;
        }

        @Override
        public DummyDTO update(DummyDTO dto) {
            return null;
        }

        @Override
        public void delete(DummyDTO dto) {

        }

        @Override
        public void deleteById(Long id) {
        }

        @Override
        public DummyDTO findById(Long id) {
            return null;
        }
    }

    public static class DummyCRUDController implements CRUDControllerInterface<DummyDTO, IndexedEntity, CRUDMethods<DummyDTO>> {
        private final CRUDMethods<DummyDTO> service;

        public DummyCRUDController(CRUDMethods<DummyDTO> service) {
            this.service = service;
        }

        @Override
        public CRUDMethods<DummyDTO> getService() {
            return service;
        }
    }
}