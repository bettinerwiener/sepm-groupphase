package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class EventRepositoryImpl implements EventRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> findAllByCriteria(String searchTerm, String category,
                                         LocalDate startDate, LocalDate endDate,
                                         Double price, Double duration,
                                         Long location, Long artist) {
        boolean first_condition = false;
        String query = "select e from Event e " +
            "join EventPerformance i on e.id = i.event " +
            "join Ticket t on i.event = t.performance " +
            "join Room r on r.id = i.room where";
        if (searchTerm != null && !searchTerm.isEmpty()) {
            searchTerm = searchTerm.toLowerCase();
            query = query + " lower(title) like '%" + searchTerm + "%' or lower(abstract) like '%" + searchTerm+
                "%' or lower(contents) like '%" + searchTerm+ "%'";
        } else {
            first_condition = true;
        }
        if (category != null && !category.isEmpty()) {
            if (!first_condition) {
                query += " and category = '" + category + "'";
            } else {
                query += " category = '" + category + "'";
                first_condition = false;
            }

        }
        if (startDate != null) {
            if (!first_condition) {
                query += " and perf_date > '" + startDate + "'";
            } else {
                query += " perf_date > '" + startDate + "'";
                first_condition = false;
            }

        }
        if (endDate != null) {
            if (!first_condition) {
                query += " and perf_date < '" + endDate + "'";
            } else {
                query += " perf_date < '" + endDate + "'";
                first_condition = false;
            }

        }
        if (duration != null) {
            if (!first_condition) {
                query += " and duration < " + (duration + 0.5) + " and duration > " + (duration - 0.5);
            } else {
                query += " duration < " + (duration + 0.5) + " and duration > " + (duration - 0.5);
                first_condition = false;
            }

        }
        if (price != null) {
            if (!first_condition) {
                query += " and price < " + (price + 5);
            } else {
                query += " price < " + (price + 5);
            }

        }
        if (location != null) {
            if (!first_condition) {
                query += " and r.location = " + location;
            } else {
                query += " r.location = " + location;
            }

        }
        if (artist != null) {
            if (!first_condition) {
                query += " and artist = " + artist;
            } else {
                query += " artist = " + artist;
            }

        }
        query += " group by e.id";

        Query tp = entityManager.createQuery(query, at.ac.tuwien.sepm.groupphase.backend.entity.Event.class);
        List<Event> events = tp.getResultList();
        log.info("Number of events: {}", events.size());
        if (events == null || events.isEmpty()) {
            throw new NotFoundException("No events matching the criteria");
        }
        return events;
    }
}
