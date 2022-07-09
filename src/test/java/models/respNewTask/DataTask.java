package models.respNewTask;

import lombok.Data;

@Data
public class DataTask {
	private Object jobEndDate;
	private Object feedbackLink;
	private Object comments;
	private Org org;
	private Object jobStartDate;
	private Object customerAvailableEndDate;
	private Object icon;
	private Object rating;
	private Object feedbackRequestCanceled;
	private String description;
	private Object copiedFromTask;
	private Object ratingComment;
	private Object team;
	private Object feedbackRequestCancellationTime;
	private Object customerAvailableStartDate;
	private Object ratingDate;
	private Boolean removed;
	private Client client;
	private Object confirmLink;
	private Object payment;
	private Integer id;
	private Object feedbackSent;
	private Object user;
	private Status status;
}