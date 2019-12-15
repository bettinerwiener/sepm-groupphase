package at.ac.tuwien.sepm.groupphase.backend.unittests;
import at.ac.tuwien.sepm.groupphase.backend.basetest.OrderTestData;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;

import at.ac.tuwien.sepm.groupphase.backend.repository.OrderRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class OrderRepositoryTest implements OrderTestData {

    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void saveUserThenCheckSizeWhenFindAllThenFindUserById() {
        Order order = new Order();

        order.setUserId(2L);


        orderRepository.save(order);

        assertAll(
            () -> assertEquals(4, orderRepository.findAll().size()),
            () -> assertNotNull(orderRepository.findById(order.getId()))
        );
    }
}

