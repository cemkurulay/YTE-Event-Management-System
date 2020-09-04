package yte.intern.spring.application.eventcrud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.spring.application.usercrud.SystemUser;
import yte.intern.spring.application.participanthandling.ParticipantAnswer;
import yte.intern.spring.application.participanthandling.ParticipantQuestion;
import yte.intern.spring.application.participanthandling.ParticipantReview;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> listAllEvents() {
        return eventRepository.findAll();
    }

    public String addEvent(EventDTO eventDTO) {

        //check start date and end date as local date time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime eventStartTime = LocalDateTime.parse(eventDTO.getEventStartDate(), formatter);
        LocalDateTime eventEndTime = LocalDateTime.parse(eventDTO.getEventEndDate(), formatter);

        if(eventEndTime.isAfter(eventStartTime)){

            //since there is no answers,participants,questions,reviews yet, they are created as empty Sets
            List<ParticipantAnswer> answers = new ArrayList<>();

            Set<SystemUser> participants  = new HashSet<>();

            Set<ParticipantQuestion> questions = new HashSet<>();

            Set<ParticipantReview> reviews = new HashSet<>();



            Event events = new Event(eventDTO.getEventName(), eventDTO.getEventStartDate(), eventDTO.getEventEndDate(), eventDTO.getEventQuota(),
                    eventDTO.getEventQuestion(), answers, participants, questions, reviews, null
            );

            eventRepository.save(events);

            return "Event added!";
        }else{
            return "Event could not be added.";
        }


    }

    @Transactional
    public String updateEvent(String eventName, Event event) {


        Optional<Event> eventOptional = eventRepository.findByEventName(eventName);

        if (eventOptional.isPresent()) {
            Event eventFromDB = eventOptional.get();

            //check start date and end date as local date time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime eventStartTime = LocalDateTime.parse(eventFromDB.getEventStartDate(), formatter);

            if(eventStartTime.isAfter(LocalDateTime.now())){
                updateEventFromDB(event, eventFromDB);
                eventRepository.save(eventFromDB);
                return "Event has been updated successfully!";
            }else{
                return "Event cannot be updated!";
            }

        }
        return "Event can't be found!";


    }

    private void updateEventFromDB(Event event, Event eventFromDB) {
        eventFromDB.setEventName(event.getEventName());
        eventFromDB.setEventStartDate(event.getEventStartDate());
        eventFromDB.setEventEndDate(event.getEventEndDate());
        eventFromDB.setEventQuota(event.getEventQuota());
    }

    public String deleteEvent(String eventName) {
        Optional<Event> eventOptional = eventRepository.findByEventName(eventName);
        if (eventOptional.isPresent()) {
            Event eventFromDB = eventOptional.get();
            //check start date and end date as local date time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime eventStartTime = LocalDateTime.parse(eventFromDB.getEventStartDate(), formatter);
            if(eventStartTime.isAfter(LocalDateTime.now())){
                eventRepository.deleteByEventName(eventName);
                return "Event has been deleted successfully!";
            }else{
                return "Event cannot be deleted!";
            }

        } else {
            return "Event can't be found!";
        }
    }
}

