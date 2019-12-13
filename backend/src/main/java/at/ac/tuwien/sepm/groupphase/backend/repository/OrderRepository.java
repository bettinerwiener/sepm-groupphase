package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
    @Override
    List<Order> findAll();

    /**
     * for now there are no extra methods needed except those defined int JpaRepository
     */

    List<Order> findByUserId(Long id);
}
