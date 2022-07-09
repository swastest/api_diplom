package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceType{
	private String code;
	private String name;
	private Integer id;
}