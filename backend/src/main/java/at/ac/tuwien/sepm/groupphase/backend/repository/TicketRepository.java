package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

   /* @Modifying(clearAutomatically = true)
    @Query("UPDATE Company c SET c.address = :address WHERE c.id = :companyId")
    void updateTicketStatusToBought(@Param("companyId") int companyId, @Param("address") String address);*/
    List<Ticket> findTicketsByCustomerOrderId(Long id);

    List<Ticket> findByPerformanceId(Long performanceId);

    Ticket findFirstById (Long id);

    @Query(value = "SELECT user_id FROM ticket JOIN customer_order ON (customer_order.id=customer_order_id) WHERE ticket.id = :ticketId", nativeQuery = true)
    Long findUserIdWhoReserved (@Param("ticketId") Long id);
}
