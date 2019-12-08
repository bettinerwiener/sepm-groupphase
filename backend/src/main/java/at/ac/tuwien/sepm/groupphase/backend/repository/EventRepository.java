package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
    /**
     * for now there are no extra methods needed except those defined int JpaRepository
     */
    @Query(value="select e.* from event e left join (select e.*, count(t.id) from event e left join ticket t on e.id = t.event where t.`status` = 'BOUGHT' group by e.id order by count(t.id)) as q on e.id = q.id",
    nativeQuery = true)
    List<Event> findTopEvents();
}
