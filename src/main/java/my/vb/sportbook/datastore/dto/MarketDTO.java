package my.vb.sportbook.datastore.dto;

import lombok.Data;
import my.vb.sportbook.datastore.model.HistoryData;
import my.vb.sportbook.datastore.model.IndexedEntity;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.model.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class MarketDTO {
    @Id
    private Long id;
    private String description;
    private StatusEnum status;
    private boolean settled;
    private List<Outcome> outcomes;
}

