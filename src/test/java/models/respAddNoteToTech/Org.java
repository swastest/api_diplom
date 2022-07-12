package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import models.OrgDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Org extends OrgDto {
}