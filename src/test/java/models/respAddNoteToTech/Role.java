package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import models.RoleDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role extends RoleDto {
}