package my.vb.sportbook.datastore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.vb.sportbook.datastore.model.Market;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.model.StatusEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    private Long id;
    private String description;
    private StatusEnum status;
    private boolean settled;
    private List<OutcomeDTO> outcomes;
    private Long eventId;

    public MarketDTO(Market market) {
        this.id = market.getId();
        this.description = market.getDescription();
        this.status = market.getStatus();
        this.settled = market.isSettled();
        this.outcomes = market.getOutcomes().stream().map(OutcomeDTO::new).toList();
        this.eventId = market.getEventId();
    }

    public Market convertTo() {
        return Market.builder()
                .id(id)
                .description(description)
                .status(status)
                .settled(settled)
                .outcomes(outcomes.stream().map(OutcomeDTO::convertTo).toList())
                .eventId(eventId)
                .build();
    }
}

