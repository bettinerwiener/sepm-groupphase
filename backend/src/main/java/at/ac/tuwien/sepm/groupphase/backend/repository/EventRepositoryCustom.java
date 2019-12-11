package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findAllByCriteria(@Param("searchTerm") String searchTerm, @Param("category") String category,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate,
                                  @Param("price") Double price, @Param("duration") Double duration);
}

