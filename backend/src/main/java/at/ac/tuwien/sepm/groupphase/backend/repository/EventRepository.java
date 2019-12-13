package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository <Event, Long>, EventRepositoryCustom {
    /**
     * for now there are no extra methods needed except those defined int JpaRepository
     */
    @Query(value="select * from event e inner join (select t.event, count(t.id) from ticket t  where t.`status` = 'BOUGHT' group by t.event order by count(t.id)) as q on e.id = q.event",
    nativeQuery = true)
    List<Event> findTopEvents();

}
