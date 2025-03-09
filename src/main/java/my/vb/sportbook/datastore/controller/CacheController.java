package my.vb.sportbook.datastore.controller;

import my.vb.sportbook.datastore.config.CacheInspector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * CacheController is a REST controller responsible for handling cache-related
 * operations within the application.
 * <p>
 * The controller exposes an endpoint to retrieve the current status and details
 * of the cache managed by the application. It works in conjunction with the {@link CacheInspector}
 * to fetch and encapsulate cache details.
 * <p>
 * This controller leverages Spring's {@code @RestController} annotation to handle
 * HTTP requests and produce JSON responses.
 */
@Tag(name = "Cache Management", description = "Endpoints for managing application cache")
@RestController
public class CacheController {

    private final CacheInspector cacheInspector;

    public CacheController(CacheInspector cacheInspector) {
        this.cacheInspector = cacheInspector;
    }

    @Operation(summary = "Get the current cache status", description = "Retrieves the current status and contents of the application cache")
    @GetMapping("/cache/status")
    public ResponseEntity<String> getCacheStatus() {
        String status = cacheInspector.getCacheStatus();
        return ResponseEntity.ok(status);
    }

}
