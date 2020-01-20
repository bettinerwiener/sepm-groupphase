package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.CustomerNews;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.CustomerNewsRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.NewsRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SimpleNewsService implements NewsService {

    private NewsRepository newsRepository;
    private UserRepository userRepository;
    private CustomerNewsRepository customerNewsRepository;

    public SimpleNewsService (NewsRepository newsRepository, UserRepository userRepository,
                              CustomerNewsRepository customerNewsRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.customerNewsRepository = customerNewsRepository;
    }

    @Override
    public News save(News news) throws NotCreatedException {
        log.info("Saving news entry {} to database ...", news.getTitle());
        try {
            news.setPublishedAt(LocalDateTime.now());
            News result = this.newsRepository.save(news);
            List<User> users = this.userRepository.findAll();
            List<CustomerNews> customerNews = new ArrayList<>();
            for (User user : users) {
                CustomerNews help = new CustomerNews();
                help.setRead(false);
                help.setUser(user);
                help.setNews(news);
                customerNews.add(help);
            }
            this.customerNewsRepository.saveAll(customerNews);
            return result;
        } catch (DataAccessException dae) {
            log.error("The news entry {} could not be saved: {}", news.getTitle(), dae.getMessage());
            throw new NotCreatedException(String.format("The news entry %s could not be created: %s",
                news.getTitle(), dae.getMessage()));
        }
    }

    @Override
    public News findById(Long id) throws NotFoundException {
        log.info("Finding news entry with id {} ...", id);
        System.out.println("hereservice");
        try {
            Optional<News> result = this.newsRepository.findById(id);
            if (!result.isPresent()) {
                log.info("The news entry with id {} could not be found.", id);
                throw new NotFoundException(String.format("The news entry with id %d could not be found.", id));
            }
            return result.get();
        } catch (DataAccessException dae) {
            log.error("The news with id {} could not be found: {}", id, dae.getMessage());
            throw new NotFoundException(String.format("The news with id %d could not be found: %s",
                id, dae.getMessage()));
        }
    }

    @Override
    public News updateWithImage(Long id, MultipartFile image) throws NotFoundException {
        log.info("Updating news entry {} with image ...", id);
        try {
            Optional<News> result = this.newsRepository.findById(id);
            if (!result.isPresent()) {
                log.info("The news entry with id {} could not be found.", id);
                throw new NotFoundException(String.format("The news entry with id %d could not be found.", id));
            }
            News toUpdate = result.get();
            toUpdate.setImage(IOUtils.toByteArray(image.getInputStream()));
            this.newsRepository.saveAndFlush(toUpdate);
        } catch (IOException | DataAccessException dae) {
            log.error("The news with id {} could not be updated: {}", id, dae.getMessage());
            throw new NotFoundException(String.format("The news with id %d could not be updated: %s",
                id, dae.getMessage()));
        }
        return null;
    }
}
