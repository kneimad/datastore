package my.vb.sportbook.datastore.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document(collection = "outcomes")
public class Outcome extends HistoryData implements IndexedEntity {
    private Long id;
    private String description;
    private boolean settled;
    private BigDecimal price;
    private String result; //mb enum ?
}
