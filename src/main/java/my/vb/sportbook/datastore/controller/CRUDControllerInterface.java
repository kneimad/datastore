package my.vb.sportbook.datastore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.vb.sportbook.datastore.model.IndexedEntity;
import my.vb.sportbook.datastore.service.CRUDMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * Interface representing a generic CRUD (Create, Read, Update, Delete) Controller.
 * This provides default implementations for standard CRUD operations to interact with entities.
 *
 * @param <D> The type of the Data Transfer Object (DTO) which is exposed through the API.
 * @param <T> The type representing the Entity which implements {@link IndexedEntity}, corresponding to the application's data model.
 * @param <E> The type representing the service layer implementing {@link CRUDMethods}, responsible for business logic and repository interaction.
 */

@Tag(name = "CRUD Controller", description = "Endpoints for managing CRUD operations with Events, Markets and Outcomes")
public interface CRUDControllerInterface<D, T extends IndexedEntity, E extends CRUDMethods<D>> {

    Logger logger = LoggerFactory.getLogger(CRUDControllerInterface.class);

    @PostMapping("/create")
    @Operation(summary = "Create a new entity", description = "Adds a new entity to the system.")
    default ResponseEntity<D> create(@RequestBody D dto) {
        D createdEntity = getService().create(dto);
        return new ResponseEntity<>(createdEntity, OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing entity", description = "Updates details of an existing entity in the system.")
    default ResponseEntity<D> update(@RequestBody D dto) {
        D updatedEntity = getService().update(dto);
        return new ResponseEntity<>(updatedEntity, OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an entity", description = "Removes an entity from the system by its ID.")
    default void deleteById(@PathVariable(name = "id") Long id) {
        getService().deleteById(id);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Find an entity by ID", description = "Fetches details of an entity from the system by its ID.")
    default ResponseEntity<D> findById(@PathVariable(name = "id") Long id) {
        logger.info("Fetching entity with ID: {}", id);
        D entity = getService().findById(id);
        if (entity == null) {
            logger.warn("Entity with ID {} not found", id);
            return new ResponseEntity<>(null, NOT_FOUND);
        }
        logger.info("Entity with ID {} found: {}", id, entity);
        return new ResponseEntity<>(entity, OK);
    }

    CRUDMethods<D> getService();
}
