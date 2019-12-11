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
    public List<Event> findAllByCriteria(String searchTerm, String category, LocalDate startDate, LocalDate endDate, Double price, Double duration) {
        boolean first_condition = false;
        String query = "select e.id, e.title, e.abstract, e.contents, e.category, e.employee, e.duration from event e join is_performed_at i on e.id = i.event join ticket t on i.event = t.event where";
        if (searchTerm != null && !searchTerm.isEmpty()) {
            query = query + "title like '%" + searchTerm + "%' or abstract like'%" + searchTerm+
                "%' or contents like'%" + searchTerm+ "%'";
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
                query += " and `date` > " + startDate;
            } else {
                query += " `date` > " + startDate;
                first_condition = false;
            }

        }
        if (endDate != null) {
            if (!first_condition) {
                query += " and `date` < " + endDate;
            } else {
                query += " `date` < " + endDate;
                first_condition = false;
            }

        }
        if (duration != null) {
            if (!first_condition) {
                query += " and duration < " + duration;
            } else {
                query += " duration < " + duration;
                first_condition = false;
            }

        }
        if (price != null) {
            if (!first_condition) {
                query += " and price < " + price;
            } else {
                query += " price < " + price;
            }

        }
        query += " group by e.id;";

        Query tp = entityManager.createNativeQuery(query,"Events");
        List<Event> events = tp.getResultList();
        log.info("Number of events: {}", events.size());
        if (events == null || events.isEmpty()) {
            throw new NotFoundException("No events matching the criteria");
        }
        return events;
    }
}
