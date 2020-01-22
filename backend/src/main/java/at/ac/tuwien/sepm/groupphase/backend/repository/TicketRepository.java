package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


    /**
     * Find tickets with the given customerOrderId.
     * @param id the customerOrderId
     * @return all tickets with given customerOrderId.
     */
    List<Ticket> findTicketsByCustomerOrderId(Long id);

    Ticket findFirstById (Long id);

    /**
     * Find the User who reserved or bought the Ticket .
     * @param id Id of the ticket
     * @return userId of the User who reserved or bought the Ticket
     */
    @Query(value = "SELECT user_id FROM ticket JOIN customer_order ON (customer_order.id=customer_order_id) WHERE ticket.id = :ticketId", nativeQuery = true)
    Long findUserIdToTicket (@Param("ticketId") Long id);
    

    /**
     * Get all reserved Tickets where Event starts in less than 30min.
     * @param currentTime the current time +30min
     * @return List of all reserved Tickets from Events that start in less than 30min.
     */
    @Query(value = "SELECT ticket.id, ticket.price,is_performed_at_id,customer_order_id, seat_id, status  FROM event " +
        "JOIN is_performed_at ON(event.id = is_performed_at.event) JOIN ticket ON(is_performed_at.id = ticket.is_performed_at_id) " +
        "WHERE status = 'RESERVED' AND perf_date <= :currentTime", nativeQuery = true)
    List<Ticket> getAllTicketsWhereReservationRunsOut (@Param("currentTime") LocalDateTime currentTime);


    /**
     * Get the number of tickets in the Order with orderId
     * @param orderId to get the ticketcount
     * @return Number of Tickets in Order with orderId
     */
    @Query(value = "SELECT COUNT(*) FROM ticket JOIN customer_order ON (customer_order.id=ticket.customer_order_id) WHERE customer_order.id= :orderId", nativeQuery = true)
    int getNumberOfTicketsInOrder (@Param("orderId") Long orderId);



    /*
    @Query(value = "SELECT ticket.customer_order_id FROM ticket JOIN customer_order ON( ticket.customer_order_id = customer_order.id) WHERE ticket.id = :ticketId", nativeQuery = true)
    Long findOrderIdforTicket (@Param("ticketId") Long id);*/
    @Query(value="select * from ticket t where t.is_performed_at_id = ?1",
    nativeQuery = true)
    List<Ticket> findByPerformanceId(Long id);


}
