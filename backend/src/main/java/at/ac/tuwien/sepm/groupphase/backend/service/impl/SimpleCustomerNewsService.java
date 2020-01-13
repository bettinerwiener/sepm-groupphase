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
import at.ac.tuwien.sepm.groupphase.backend.service.CustomerNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SimpleCustomerNewsService implements CustomerNewsService {

    private EmployeeNewsEventRepository employeeNewsEventRepository;
    private NewsRepository newsRepository;
    private UserRepository userRepository;
    private CustomerNewsRepository customerNewsRepository;

    public SimpleCustomerNewsService(EmployeeNewsEventRepository employeeNewsEventRepository,
                                     NewsRepository newsRepository,
                                     UserRepository userRepository,
                                     CustomerNewsRepository customerNewsRepository) {
        this.employeeNewsEventRepository = employeeNewsEventRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.customerNewsRepository = customerNewsRepository;
    }

    @Override
    public List<CustomerNews> findAll() throws NotFoundException {
        try {
            List<CustomerNews> customerNews;
            customerNews = this.customerNewsRepository.findAll();
            log.debug("The size of customerNews: {}", customerNews.size());
            return customerNews;
        } catch (DataAccessException dae) {
            log.error("Finding all news entries failed: {}", dae.getMessage());
            throw new NotFoundException(String.format("Finding all news entries failed: %s",
                dae.getMessage()));
        }
    }

    public List<CustomerNews> findCustomerNewsByCustomer(String email, Boolean read) throws NotFoundException {
        try {
            List<CustomerNews> customerNews;
            customerNews = this.customerNewsRepository.findCustomerNewsByCustomer(email, read);
            log.debug("The size of customerNews: {}", customerNews.size());
            return customerNews;
        } catch (DataAccessException dae) {
            log.error("Finding all news entries failed: {}", dae.getMessage());
            throw new NotFoundException(String.format("Finding all news entries failed: %s",
                dae.getMessage()));
        }
    }
}
