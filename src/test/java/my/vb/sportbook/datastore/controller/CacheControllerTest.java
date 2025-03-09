package my.vb.sportbook.datastore.controller;

import my.vb.sportbook.datastore.config.CacheInspector;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CacheController.class)
public class CacheControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CacheInspector cacheInspector;

    @Test
    public void testGetCacheStatusSuccess() throws Exception {
        String expectedStatus = "Cache is up and running";
        when(cacheInspector.getCacheStatus()).thenReturn(expectedStatus);

        mockMvc.perform(get("/cache/status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedStatus));
    }

}