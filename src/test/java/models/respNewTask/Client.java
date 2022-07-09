package models.respNewTask;

import lombok.Data;

@Data
public class Client{
	private Boolean marked;
	private String country;
	private String zipCode;
	private String note;
	private String town;
	private Org org;
	private Object merchant;
	private Object phones;
	private Double lon;
	private Boolean removed;
	private Long infoLastModified;
	private String streetAddress;
	private String name;
	private String fullAddress;
	private Boolean anonymous;
	private Object promoter;
	private Integer id;
	private String state;
	private Long lastModified;
	private String email;
	private Double lat;
	private Object events;
}