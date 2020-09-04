package yte.intern.spring.application.participanthandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.spring.application.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "idgen", sequenceName = "REVIEW_SEQ")
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantReview extends BaseEntity {


    @Min(1)
    @Max(5)
    private int rating;


    private String comment;

}



