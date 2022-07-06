package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestPaymentsItem {

	public class RequestPaymentsItem {

		private Integer date;
		private Integer distance;
		private Integer teamId;
		private String name;
		private Integer time;
		private Integer userId;

	}


	public class RequestPaymentsemInitiator {

		private List<RequestPaymentsem> requestPaymentsem;

	}




