package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.entity.*;
import at.ac.tuwien.sepm.groupphase.backend.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class EmployeeNewsEventRepositoryTest {

    @Autowired
    EmployeeNewsEventRepository employeeNewsEventRepository;
    @Autowired
    UserRepository employeeRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    NewsRepository newsRepository;


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
    public void findAllReturns302() {
        List<EmployeeNewsEvent> employeeNewsEventList = employeeNewsEventRepository.findAll();
        assertEquals(302, employeeNewsEventList.size());
    }

    @Test
    public void createEntryReturnsEntry() {
        EmployeeNewsEvent employeeNewsEvent = new EmployeeNewsEvent();
        Optional<News> possibleNews = newsRepository.findById(1L);
        News news = possibleNews.get();
        Optional<User> possibleEmployee = employeeRepository.findById(3L);
        User employee = possibleEmployee.get();
        Optional<Event> possibleEvent = eventRepository.findById(1L);
        Event event = possibleEvent.get();
        employeeNewsEvent.setEmployee(employee);
        employeeNewsEvent.setEvent(event);
        employeeNewsEvent.setNews(news);
        EmployeeNewsEvent result = employeeNewsEventRepository.save(employeeNewsEvent);
        assertAll (
            () -> assertEquals(employeeNewsEvent.getNews().getTitle(), result.getNews().getTitle()),
            () -> assertEquals(employeeNewsEvent.getEmployee(), result.getEmployee()),
            () -> assertEquals(employeeNewsEvent.getEvent(), result.getEvent())
        );
    }

}
