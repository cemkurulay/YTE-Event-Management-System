package yte.intern.spring.application.eventcrud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.spring.application.core.BaseEntity;
import yte.intern.spring.application.usercrud.SystemUser;
import yte.intern.spring.application.participanthandling.ParticipantAnswer;
import yte.intern.spring.application.participanthandling.ParticipantQuestion;
import yte.intern.spring.application.participanthandling.ParticipantReview;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "idgen", sequenceName = "EVENT_SEQ")
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {

    @NotBlank
    private String eventName;

    @NotBlank
    private String eventStartDate;

    @NotBlank
    private String eventEndDate;

    private int eventQuota;

    private String eventQuestion;

    @OneToMany
    @JoinColumn(name = "EVENT_ID")
    private List<ParticipantAnswer> answers;

    @ManyToMany(mappedBy = "events")
    private Set<SystemUser> participants;

    @OneToMany
    @JoinColumn(name = "EVENT_ID")
    private Set<ParticipantQuestion> questions;

    @OneToMany
    @JoinColumn(name = "EVENT_ID")
    private Set<ParticipantReview> reviews;

    @OneToOne
    @JoinColumn(name = "user_id")
    private SystemUser teacher;
}

