package my.vb.sportbook.datastore.service;

import my.vb.sportbook.datastore.model.IndexedEntity;

public interface CRUDMethods<D>{
    D create(D dto);
    D update(D dto);
    void delete(D dto);
    void deleteById(Long id);
    D findById(Long id);
}
