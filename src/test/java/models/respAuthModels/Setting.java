package models.respAuthModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import models.SettingDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Setting extends SettingDto {

}