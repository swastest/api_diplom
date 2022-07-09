package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User{
	private DeviceType deviceType;
	private String note;
	private String address;
	private Role role;
	private Boolean showInCalendar;
	private Org org;
	private Boolean active;
	private Object phones;
	private Double lon;
	private Setting setting;
	private Boolean removed;
	private String name;
	private Integer id;
	private String email;
	private Double lat;
}