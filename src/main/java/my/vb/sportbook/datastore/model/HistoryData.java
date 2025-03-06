package my.vb.sportbook.datastore.model;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
public class HistoryData {
    @LastModifiedDate
    protected Instant updatedDateTime;
    @LastModifiedBy
    protected String updatedUser;
}
