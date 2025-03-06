package my.vb.sportbook.datastore.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "markets")
public class Market extends HistoryData implements IndexedEntity {
    @Id
    private Long id;
    private String description;
    private StatusEnum status;
    private boolean settled;
    private List<Outcome> outcomes;
}

