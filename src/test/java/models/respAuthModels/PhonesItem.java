package models.respAuthModels;

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
public class PhonesItem{
	private String note;
	private String phoneNumber;
	private Boolean mobile;
	private Boolean main;
	private Integer id;
	private String token;
}