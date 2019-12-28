package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EmployeeNewsEventRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.NewsRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EmployeeNewsEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleEmployeeNewsEventService implements EmployeeNewsEventService {

    private EmployeeNewsEventRepository employeeNewsEventRepository;
    private NewsRepository newsRepository;

    public SimpleEmployeeNewsEventService(EmployeeNewsEventRepository employeeNewsEventRepository,
                                          NewsRepository newsRepository) {
        this.employeeNewsEventRepository = employeeNewsEventRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public EmployeeNewsEvent create(EmployeeNewsEvent employeeNewsEvent) throws NotCreatedException {
        log.info("Creating news entry {} for event {} ...", employeeNewsEvent.getNews().getTitle(),
            employeeNewsEvent.getEvent().getTitle());
        try {
            News news = this.newsRepository.saveAndFlush(employeeNewsEvent.getNews());
            employeeNewsEvent.setNews(news);
            EmployeeNewsEvent result = this.employeeNewsEventRepository.saveAndFlush(employeeNewsEvent);
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
}
