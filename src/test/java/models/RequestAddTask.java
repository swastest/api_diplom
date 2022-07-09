package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestAddTask {
    private Long jobEndDate;
    private Integer clientId;
    private Long jobStartDate;
    private Integer teamId;
    private Long customerAvailableEndDate;
    private String description;
    private Integer id;
    private Long customerAvailableStartDate;
    private Integer userId;
}
