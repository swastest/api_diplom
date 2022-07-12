package models;

import lombok.Data;
import models.respAuthModels.Setting;

@Data
public class OrgDto {
    private String note;
    private Boolean removed;
    private Integer userCount;
    private String name;
    private Long endLicenseDate;
    private Integer id;
    private Setting setting;
}
