package my.vb.sportbook.datastore.dto;

import lombok.Data;
import my.vb.sportbook.datastore.model.Event;
import my.vb.sportbook.datastore.model.Market;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class EventDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public EventDTO(Event event) {
        this.id = event.getId();
        this.description = event.getDescription();
        this.homeTeam = event.getHomeTeam();
        this.awayTeam = event.getAwayTeam();
        this.startTime = event.getStartTime();
        this.sport = event.getSport();
        this.country = event.getCountry();
        this.competition = event.getCompetition();
        this.settled = event.isSettled();
        this.markets = event.getMarkets();
    }

    public Event convertTo(){
        return Event.builder()
                .id(id)
                .description(description)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .startTime(startTime)
                .sport(sport)
                .country(country)
                .competition(competition)
                .settled(settled)
                .markets(markets)
                .build();
    }
}
