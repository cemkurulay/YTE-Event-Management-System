package yte.intern.spring.application.eventcrud;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.application.eventcrud.Event;

import javax.transaction.Transactional;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByEventName(String eventName);

    boolean existsByEventName(String eventName);

    @Transactional
    void deleteByEventName(String studentNumber);
}
