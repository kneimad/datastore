package my.vb.sportbook.datastore.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.vb.sportbook.datastore.model.Outcome;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private boolean settled;
    private BigDecimal price;
    private String result;
    private Long marketId;

    public OutcomeDTO(Outcome outcome) {
        this.id = outcome.getId();
        this.description = outcome.getDescription();
        this.settled = outcome.isSettled();
        this.price = outcome.getPrice();
        this.result = outcome.getResult();
        this.marketId = outcome.getMarketId();
    }

    public Outcome convertTo(){
        return Outcome.builder()
                .id(id)
                .description(description)
                .settled(settled)
                .price(price)
                .result(result)
                .marketId(marketId)
                .build();
    }
}
