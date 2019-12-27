package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.NewsDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface NewsMapper {

    @Named("news")
    NewsDto newsToNewsDto(News news);

    News newsDtoToNews(NewsDto newsDto);
}
