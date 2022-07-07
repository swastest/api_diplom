package models.respAuthModels;

import java.util.List;

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
public class DataJson{
	private DeviceType deviceType;
	private Object note;
	private String address;
	private Role role;
	private Boolean showInCalendar;
	private Org org;
	private Boolean active;
	private List<PhonesItem> phones;
	private Double lon;
	private Setting setting;
	private Boolean removed;
	private String name;
	private Integer id;
	private String email;
	private Double lat;
}