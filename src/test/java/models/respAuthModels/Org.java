package models.respAuthModels;

import lombok.Data;

@Data
public class Org{
	private String note;
	private Boolean removed;
	private Integer userCount;
	private String name;
	private Long endLicenseDate;
	private Integer id;
	private Setting setting;
}