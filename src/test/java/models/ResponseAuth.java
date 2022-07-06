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
public class ResponseAuth {

    private Data data;
    private String message;
    private String status;

public class DeviceType {

    private String code;
    private String name;
    private Integer id;

}


public class Role {

    private String code;
    private String name;
    private Integer id;

}


public class Setting {

    private Integer ringCentralSyncSmsTime;
    private Integer ringCentralSyncCallTime;
    private Boolean displayMerchantButton;
    private Boolean displayPromoterButton;
    private Integer ringCentralExtension;
    private String taskConfirmationLink;
    private String serviceRatingText;
    private Integer phoneCode;
    private Integer id;
    private Integer firstHour;
    private String taskConfirmationText;
    private String serviceRatingLink;
    private String ringCentralPassword;
    private Integer clientNumber;
    private Integer ringCentralSenderNum;
    private Integer ringCentralAccountId;
    private String ringCentralClientId;
    private Boolean callRecording;
    private Boolean saveToPhoneBook;
    private String ringCentralSyncCallToken;
    private String ringCentralClientSecret;
    private Integer ringCentralLogin;
    private Integer timeFormat;
    private String ringCentralSyncSmsToken;
    private String orderTimeFormat;

}


public class Org {

    private String note;
    private Boolean removed;
    private Integer userCount;
    private String name;
    private Integer endLicenseDate;
    private Integer id;
    private Setting setting;

}


public class Phones {

    private String note;
    private Integer phoneNumber;
    private Boolean mobile;
    private Boolean main;
    private Integer id;
    private String token;

}

/*
public class Setting {

    private Boolean displayPush;
    private Integer id;

}
*/

public class Data {

    private DeviceType deviceType;
    private String note;
    private String address;
    private Role role;
    private Boolean showInCalendar;
    private Org org;
    private Boolean active;
    private List<Phones> phones;
    private Double lon;
    private Setting setting;
    private Boolean removed;
    private String name;
    private Integer id;
    private String email;
    private Double lat;

}

}
