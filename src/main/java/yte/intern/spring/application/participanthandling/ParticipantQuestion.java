package yte.intern.spring.application.participanthandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.spring.application.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantQuestion extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;


    private String userName;


    private String question;

}


