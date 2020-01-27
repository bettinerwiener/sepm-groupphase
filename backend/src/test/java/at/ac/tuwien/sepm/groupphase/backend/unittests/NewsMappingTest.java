package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.NewsDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EventMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.NewsMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class NewsMappingTest {

    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void whenMapNewsToNewsDto_NewsDtoHasSameProperties() {
        News news = new News();
        news.setTitle("StarWars");
        news.setShortDescription("What shall i say");
        news.setEntry("Noting to say");

        NewsDto newsDto = newsMapper.newsToNewsDto(news);
        assertAll(
            () -> assertEquals(news.getTitle(), newsDto.getTitle()),
            () -> assertEquals(news.getShortDescription(), newsDto.getShortDescription()),
            () -> assertEquals(news.getEntry(), newsDto.getEntry())
        );
    }

    @Test
    public void whenMapEventDtoToEvent_EventHasSameProperties() {
        NewsDto newsDto = NewsDto.NewsDtoBuilder.aNewsDto()
            .withTitle("Les Misérables")
            .withShortDescription("kenn ich nicht")
            .withEntry("une histoire cruelle")
            .build();

        News news = newsMapper.newsDtoToNews(newsDto);

        assertAll(
            () -> assertEquals(news.getTitle(), newsDto.getTitle()),
            () -> assertEquals(news.getShortDescription(), newsDto.getShortDescription()),
            () -> assertEquals(news.getEntry(), newsDto.getEntry())
        );
    }
}

