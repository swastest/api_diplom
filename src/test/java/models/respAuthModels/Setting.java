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
public class Setting{
	private Boolean displayPush;
	private Integer id;
	private Long ringCentralSyncSmsTime;
	private Long ringCentralSyncCallTime;
	private Boolean displayMerchantButton;
	private Boolean displayPromoterButton;
	private String ringCentralExtension;
	private String taskConfirmationLink;
	private String serviceRatingText;
	private String phoneCode;
	private Integer firstHour;
	private String taskConfirmationText;
	private String serviceRatingLink;
	private String ringCentralPassword;
	private Integer clientNumber;
	private String ringCentralSenderNum;
	private String ringCentralAccountId;
	private String ringCentralClientId;
	private Boolean callRecording;
	private Boolean saveToPhoneBook;
	private String ringCentralSyncCallToken;
	private String ringCentralClientSecret;
	private String ringCentralLogin;
	private Integer timeFormat;
	private String ringCentralSyncSmsToken;
	private String orderTimeFormat;
}