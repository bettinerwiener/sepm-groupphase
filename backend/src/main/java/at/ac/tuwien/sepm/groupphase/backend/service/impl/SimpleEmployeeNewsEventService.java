package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.CustomerNews;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.CustomerNewsRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.EmployeeNewsEventRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.NewsRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EmployeeNewsEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SimpleEmployeeNewsEventService implements EmployeeNewsEventService {

    private EmployeeNewsEventRepository employeeNewsEventRepository;
    private NewsRepository newsRepository;
    private UserRepository userRepository;
    private CustomerNewsRepository customerNewsRepository;

    public SimpleEmployeeNewsEventService(EmployeeNewsEventRepository employeeNewsEventRepository,
                                          NewsRepository newsRepository,
                                          UserRepository userRepository,
                                          CustomerNewsRepository customerNewsRepository) {
        this.employeeNewsEventRepository = employeeNewsEventRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.customerNewsRepository = customerNewsRepository;
    }

    @Override
    public EmployeeNewsEvent create(EmployeeNewsEvent employeeNewsEvent) throws NotCreatedException {
        log.info("employeeNewsEvent: {}", employeeNewsEvent);
        log.info("Creating news entry {} for event {} and user {}...", employeeNewsEvent.getNews().getTitle(),
            employeeNewsEvent.getEvent().getTitle(), employeeNewsEvent.getEmployee().getEmail());
        try {
            User user = this.userRepository.findFirstByEmail(employeeNewsEvent.getEmployee().getEmail());
            News toAdd = employeeNewsEvent.getNews();
            toAdd.setPublishedAt(LocalDateTime.now());
            News news = this.newsRepository.saveAndFlush(toAdd);
            employeeNewsEvent.setNews(news);
            employeeNewsEvent.setEmployee(user);
            EmployeeNewsEvent result = this.employeeNewsEventRepository.saveAndFlush(employeeNewsEvent);
            List<User> users = this.userRepository.findAll();
            List<CustomerNews> customerNews = new ArrayList<>();
            for (User surrogate : users) {
                CustomerNews help = new CustomerNews();
                help.setRead(false);
                help.setUser(surrogate);
                help.setNews(news);
                customerNews.add(help);
            }
            this.customerNewsRepository.saveAll(customerNews);
            this.customerNewsRepository.flush();
            return result;
        } catch (DataAccessException dae) {
            log.error("The news entry {} for event {} could not be created: {}",
                employeeNewsEvent.getNews().getTitle(), employeeNewsEvent.getEvent().getTitle(),
                dae.getMessage());
            throw new NotCreatedException(String.format("The news entry %s for event %s could not be created: %s",
                employeeNewsEvent.getEvent().getTitle(), employeeNewsEvent.getEvent().getTitle(),
                dae.getMessage()));
        }
    }

    @Override
    public List<EmployeeNewsEvent> findAll() throws NotFoundException {
        try {
            List<EmployeeNewsEvent> employeeNewsEvents;
            employeeNewsEvents = this.employeeNewsEventRepository.findAll();
            log.debug("The size of employeeNewsEvents: {}", employeeNewsEvents.size());
            return employeeNewsEvents;
        } catch (DataAccessException dae) {
            log.error("Finding all news entries failed: {}", dae.getMessage());
            throw new NotFoundException(String.format("Finding all news entries failed: %s",
                dae.getMessage()));
        }
    }
}
