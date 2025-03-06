package my.vb.sportbook.datastore.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@Document(collection = "events")
public class Event extends HistoryData implements IndexedEntity {
    @Id
    private Long id;
    private String description;
    private String homeTeam;
    private String awayTeam;
    private Instant startTime;
    private String sport;
    private String country;
    private String competition;
    private boolean settled;

    private List<Market> markets;
}
