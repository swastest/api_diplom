package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainInfo {
	private Long date;
	private Owner owner;
	private Integer id;
	private Object team;
	private String text;
	private User user;
}