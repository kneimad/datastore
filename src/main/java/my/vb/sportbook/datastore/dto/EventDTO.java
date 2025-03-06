package my.vb.sportbook.datastore.dto;

import lombok.Data;
import my.vb.sportbook.datastore.model.Market;

import java.time.Instant;
import java.util.List;

@Data
public class EventDTO {
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
