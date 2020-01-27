package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository <Event, Long>, EventRepositoryCustom {
    /**
     * for now there are no extra methods needed except those defined int JpaRepository
     */

    @Query (value = "select e from Event e " +
        "inner join User u on e.employee=u.id " +
        "where e.id=?1")
    Optional<Event> findById(Long id);

    @Query(value="select * from event e inner join " +
        "(select i.event, count(t.id) from ticket t  " +
        "inner join is_performed_at i on i.id = t.is_performed_at_id where t.`status` = 'BOUGHT' group by i.event order by count(t.id) desc) " +
        "as q on e.id = q.event",
    nativeQuery = true)
    List<Event> findTopEvents();

    @Query(value = "select e from Event e order by title")
    List<Event> findAllOrderByTitle();

    @Query(value = "select min(t.price) from ticket t " +
        "right join is_performed_at i on t.is_performed_at_id = i.id " +
        "right join event e on e.id = i.id " +
        "where e.id = ?1 and status = 'AVAILABLE'", nativeQuery = true)
    Long getMinPricePerEvent(Long id);
}
