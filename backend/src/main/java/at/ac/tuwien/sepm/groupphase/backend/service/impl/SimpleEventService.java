package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Employee;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EmployeeRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Service
public class SimpleEventService implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private EventRepository eventRepository;
    private EmployeeRepository employeeRepository;

    public SimpleEventService(EventRepository eventRepository, EmployeeRepository employeeRepository) {
        this.eventRepository = eventRepository;
        this.employeeRepository = employeeRepository;
    }

    public Event create(Event event, Long empId) throws NotCreatedException {
        LOGGER.info("EventService: creating event");
        try {
            Optional<Employee> employee = this.employeeRepository.findById(empId);
            if (employee.isPresent()) {
                event.setEmployee(employee.get());
                return this.eventRepository.save(event);
            } else {
                throw new NotCreatedException(String.format(
                    "The event %s could not be created: only an employee can add one", event.getTitle()));
            }
        } catch (ConstraintViolationException cve) {
            LOGGER.error("EventService: event could not be created: " + cve.getMessage());
            throw new NotCreatedException(String.format("The event %s could not be created: %s",
                event.getTitle(), cve.getMessage()));
        } catch (DataAccessException dae) {
            LOGGER.error("EventService: event could not be created: " + dae.getMessage());
            throw new NotCreatedException(String.format("The event %s could not be created: %s",
                event.getTitle(), dae.getMessage()));
        }
    }
}
