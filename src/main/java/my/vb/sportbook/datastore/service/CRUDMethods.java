package my.vb.sportbook.datastore.service;

import my.vb.sportbook.datastore.model.IndexedEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CRUDMethods<T extends IndexedEntity, D>{
    T create(D dto);

    T update(D dto);

    void delete(D dto);

    void deleteById(Long id);

    T findById(Long id);
}
