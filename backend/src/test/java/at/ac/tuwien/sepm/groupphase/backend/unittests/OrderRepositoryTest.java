package at.ac.tuwien.sepm.groupphase.backend.unittests;
import at.ac.tuwien.sepm.groupphase.backend.basetest.OrderTestData;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;

import at.ac.tuwien.sepm.groupphase.backend.repository.OrderRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class OrderRepositoryTest implements OrderTestData {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    PlatformTransactionManager txm;


    TransactionStatus txstatus;

    @BeforeEach
    public void setupDBTransaction() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        txstatus = txm.getTransaction(def);
        assumeTrue(txstatus.isNewTransaction());
        txstatus.setRollbackOnly();
    }

    @AfterEach
    public void tearDownDBData() {
        txm.rollback(txstatus);
    }

    @Test
    public void saveUserThenCheckSizeWhenFindAllThenFindUserById() {
        Order order = new Order();

        order.setUserId(2L);


        orderRepository.save(order);

        assertAll(
            () -> assertEquals(301, orderRepository.findAll().size()),
            () -> assertNotNull(orderRepository.findById(order.getId()))
        );
    }
}

