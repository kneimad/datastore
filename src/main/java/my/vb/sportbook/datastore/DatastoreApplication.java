package my.vb.sportbook.datastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DatastoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatastoreApplication.class, args);
    }

}
