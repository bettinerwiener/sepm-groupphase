package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.basetest.NewsTestData;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.repository.CustomerNewsRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.NewsRepository;
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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class NewsRepositoryTest implements NewsTestData {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    CustomerNewsRepository customerNewsRepository;

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
    public void creatingNewsReturnsNews() {
        News news = new News();
        news.setEntry("Luke is a wiser Jedi knight than his father.");
        news.setTitle("Star Wars");
        news.setShortDescription("A long time ago in a galaxy far, far away ...");
        news.setPublishedAt(LocalDateTime.now());
        News result = newsRepository.saveAndFlush(news);
        assertAll (
            () -> assertEquals(news.getTitle(), result.getTitle()),
            () -> assertEquals(news.getShortDescription(), result.getShortDescription()),
            () -> assertEquals(news.getEntry(), result.getEntry())
        );
    }
}
