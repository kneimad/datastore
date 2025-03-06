package my.vb.sportbook.datastore.dto;


import lombok.Data;
import my.vb.sportbook.datastore.model.Outcome;

import java.math.BigDecimal;

@Data
public class OutcomeDTO {
    private Long id;
    private String description;
    private boolean settled;
    private BigDecimal price;
    private String result; //mb enum ?

    public Outcome convertTo(){
        return Outcome.builder()
                .id(id)
                .description(description)
                .settled(settled)
                .price(price)
                .result(result)
                .build();
    }
}
