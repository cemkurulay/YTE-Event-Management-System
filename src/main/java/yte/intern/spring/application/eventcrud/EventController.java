package yte.intern.spring.application.eventcrud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping("/addEvent")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addEvent(@RequestBody EventDTO eventDTO) {
        System.out.println(eventDTO);
        return eventService.addEvent(eventDTO);
    }

    @GetMapping("/events")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<EventDTO> listAllStudents() {
        List<Event> events = eventService.listAllEvents();
        return eventMapper.mapToDto(events);
    }

    @PutMapping("/{eventName}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateEvent(@PathVariable String eventName, @Valid @RequestBody EventDTO eventDTO) {
        Event event = eventMapper.mapToEntity(eventDTO);
        return eventService.updateEvent(eventName, event);
    }

    @DeleteMapping("/{eventName}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteEvent(@PathVariable String eventName) {
        return eventService.deleteEvent(eventName);
    }

    //TODO:frontend show toast message if status code == OK

}
