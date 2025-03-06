package my.vb.sportbook.datastore.controller;

import my.vb.sportbook.datastore.model.IndexedEntity;
import my.vb.sportbook.datastore.service.CRUDMethods;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

//public interface CRUDControllerInterface<D, T extends IndexedEntity, E extends CRUDInterface<T, MongoRepository<T, Long>, D>>{
public interface CRUDControllerInterface<D, T extends IndexedEntity, E extends CRUDMethods<T, D>>{

    @PostMapping("/create")
    default ResponseEntity<T> create(@RequestBody D dto) {
        return new ResponseEntity<>(getService().create(dto), OK);
    }


    @PutMapping("/update")
    default ResponseEntity<T> update(@RequestBody D dto) {
        return new ResponseEntity<>(getService().update(dto), OK);
    }

    @DeleteMapping("/{id}")
    default void deleteById(@PathVariable(name = "id") Long id) {
        getService().deleteById(id);
    }

    @GetMapping("/find/{id}")
    default ResponseEntity<T> findById(@PathVariable(name = "id")  Long id) {
        return new ResponseEntity<>(getService().findById(id), OK);
    }

    E getService();
}
