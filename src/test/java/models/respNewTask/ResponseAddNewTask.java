package models.respNewTask;

import lombok.Data;

@Data
public class ResponseAddNewTask{
	private DataTask data;
	private Object message;
	private String status;
}