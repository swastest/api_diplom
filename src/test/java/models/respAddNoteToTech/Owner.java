package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)public class Owner{
	private Object deviceType;
	private Object note;
	private Object address;
	private Role role;
	private Boolean showInCalendar;
	private Org org;
	private Boolean active;
	private Object phones;
	private Double lon;
	private Object setting;
	private Boolean removed;
	private String name;
	private Integer id;
	private Object email;
	private Double lat;
}