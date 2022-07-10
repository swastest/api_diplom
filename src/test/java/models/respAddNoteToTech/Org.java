package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Org{
	private Object note;
	private Boolean removed;
	private Integer userCount;
	private Object name;
	private Object endLicenseDate;
	private Integer id;
	private Object setting;
}