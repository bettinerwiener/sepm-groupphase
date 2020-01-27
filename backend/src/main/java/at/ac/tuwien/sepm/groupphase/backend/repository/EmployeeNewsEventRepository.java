package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEventKey;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeNewsEventRepository extends JpaRepository<EmployeeNewsEvent, EmployeeNewsEventKey> {

    List<EmployeeNewsEvent> findByNews(News news);
}
