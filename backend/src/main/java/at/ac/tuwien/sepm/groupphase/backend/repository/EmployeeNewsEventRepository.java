package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEventKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeNewsEventRepository extends JpaRepository<EmployeeNewsEvent, EmployeeNewsEventKey> {
}
