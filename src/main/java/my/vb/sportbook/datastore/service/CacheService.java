package my.vb.sportbook.datastore.service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void clearCache(Long id) {
        Cache cache = cacheManager.getCache("entitiesCache");
        if (cache != null) {
            cache.evict(id);
        }
    }

    public void evictCollectionCache(String sport, Boolean sortedByStartTime) {
        Cache cache = cacheManager.getCache("collectionEntitiesCache");
        if (cache != null) {
            cache.evict(String.valueOf(sport).concat("-").concat(sortedByStartTime.toString()));
        }
    }
}

