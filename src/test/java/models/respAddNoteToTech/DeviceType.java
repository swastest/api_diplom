package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import models.DeviceTypeDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceType extends DeviceTypeDto {
}