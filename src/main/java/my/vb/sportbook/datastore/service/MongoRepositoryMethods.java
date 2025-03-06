package my.vb.sportbook.datastore.service;

import my.vb.sportbook.datastore.model.IndexedEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoRepositoryMethods<T extends IndexedEntity, E extends MongoRepository<T, Long>, D> extends CRUDMethods<T, D>{
    default T create(D dto) {
        return getRepository().save(convert(dto));
    }

    default T update(D dto) {
        T t = convert(dto);
        return Optional.of(getRepository().findById(t.getId()))
                .map(v -> getRepository().save(t))
                .orElse(null);
    }

    default void delete(D dto) {
        getRepository().delete(convert(dto));
    }

    default void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    default T findById(Long id) {
        return getRepository().findById(id).orElse(null);
    }

    E getRepository();

    T convert(D dto);
}
