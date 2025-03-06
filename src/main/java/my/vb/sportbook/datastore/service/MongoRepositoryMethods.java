package my.vb.sportbook.datastore.service;

import my.vb.sportbook.datastore.exception.EntityNotFoundException;
import my.vb.sportbook.datastore.model.IndexedEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public abstract class MongoRepositoryMethods<T extends IndexedEntity, E extends MongoRepository<T, Long>, D> implements CRUDMethods<T, D> {

    @Override
    public T create(D dto) {
        return getRepository().save(convert(dto));
    }

    @Override
    public T update(D dto) {
        T t = convert(dto);
        if (t == null || t.getId() == null) {
            throw new IllegalArgumentException("DTO cannot be converted to an entity or id is null");
        }
            return Optional.of(getRepository().findById(t.getId()))
                .map(v -> getRepository().save(t))
                .orElse(null);
    }

    @Override
    public void delete(D dto) {
        getRepository().delete(convert(dto));
    }

    @Override
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public T findById(Long id) {
        return getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException
                        ("Entity with id " + id + " not found"));

    }

    protected abstract E getRepository();

    protected abstract T convert(D dto);
}
