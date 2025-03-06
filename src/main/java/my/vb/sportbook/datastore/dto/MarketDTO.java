package my.vb.sportbook.datastore.dto;

import lombok.Data;
import my.vb.sportbook.datastore.model.Market;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.model.StatusEnum;

import java.io.Serializable;
import java.util.List;

@Data
public class MarketDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private StatusEnum status;
    private boolean settled;
    private List<Outcome> outcomes;
    private Long eventId;

    public MarketDTO(Market market) {
        this.id = market.getId();
        this.description = market.getDescription();
        this.status = market.getStatus();
        this.settled = market.isSettled();
        this.outcomes = market.getOutcomes();
        this.eventId = market.getEventId();
    }

    public Market convertTo() {
        return Market.builder()
                .id(id)
                .description(description)
                .status(status)
                .settled(settled)
                .outcomes(outcomes)
                .eventId(eventId)
                .build();
    }
}

