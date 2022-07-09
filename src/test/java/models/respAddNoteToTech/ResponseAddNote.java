package models.respAddNoteToTech;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAddNote{
	private MainInfo data;
	private Object message;
	private String status;
}