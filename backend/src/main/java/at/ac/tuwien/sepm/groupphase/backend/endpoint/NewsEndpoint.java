package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.NewsDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.NewsMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.service.NewsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/news")
public class NewsEndpoint {

    private NewsMapper newsMapper;
    private NewsService newsService;

    public NewsEndpoint (NewsService newsService, NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
        this.newsService = newsService;
    }

    @CrossOrigin(origins="*")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a news entry", authorizations = {@Authorization(value = "apiKey")})
    public NewsDto create(@RequestBody NewsDto newsDto) {
        News toSave = this.newsMapper.newsDtoToNews(newsDto);
        return this.newsMapper.newsToNewsDto(this.newsService.save(toSave));
    }

    @CrossOrigin(origins="*")
    @GetMapping
    @ResponseStatus
    @ApiOperation(value = "Get news by id")
    public NewsDto getById(@PathVariable("id") Long id){
        return this.newsMapper.newsToNewsDto(this.newsService.findById(id));
    }




}
