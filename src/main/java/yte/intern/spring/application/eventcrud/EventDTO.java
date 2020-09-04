package yte.intern.spring.application.eventcrud;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class EventDTO {

    private String eventName;

    private String eventStartDate;

    private String eventEndDate;

    private int eventQuota;

    private String eventQuestion;

}
