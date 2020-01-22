package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findAllByCriteria(@Param("searchTerm") String searchTerm, @Param("category") String category,
                                  @Param("startDate") LocalDateTime startDate,
                                  @Param("endDate") LocalDateTime endDate,
                                  @Param("price") Double price, @Param("duration") Double duration,
                                  @Param("location") String location, @Param("artist") String artist);
}

