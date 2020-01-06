package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.CustomerNews;
import at.ac.tuwien.sepm.groupphase.backend.entity.CustomerNewsKey;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerNewsRepository extends JpaRepository<CustomerNews, CustomerNewsKey> {

    /**
     * Links a news entry to a customer
     * @param customerNews the linked item to be created
     * @return the linked item just created
     */
    CustomerNews save(CustomerNews customerNews);

    /**
     * Finds all entries for a news
     * @param news for which entries shall be found
     * @return all entries with an attribute @param news
     */
    List<CustomerNews> findByNews(News news);
}
