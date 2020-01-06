package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Slf4j
@ToString
public class NewsDto {

    private Long id;

    @NotNull
    private String entry;

    @NotNull
    private String title;

    @NotNull
    private String shortDescription;

    private LocalDateTime publishedAt;

    private String image;

    public static final class NewsDtoBuilder {

        private Long id;
        private String entry;
        private String title;
        private String shortDescription;
        private String image;
        private LocalDateTime publishedAt;

        public NewsDtoBuilder() {};

        public static NewsDto.NewsDtoBuilder aNewsDto() {
            return new NewsDto.NewsDtoBuilder();
        }

        public NewsDto.NewsDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public NewsDto.NewsDtoBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public NewsDto.NewsDtoBuilder withEntry(String entry) {
            this.entry = entry;
            return this;
        }

        public NewsDto.NewsDtoBuilder withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public NewsDto.NewsDtoBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public NewsDto.NewsDtoBuilder withPublishedAt(LocalDateTime publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }

        public NewsDto build() {
            NewsDto newsDto = new NewsDto();
            newsDto.setId(id);
            newsDto.setTitle(title);
            newsDto.setShortDescription(shortDescription);
            newsDto.setEntry(entry);
            newsDto.setImage(image);
            newsDto.setPublishedAt(publishedAt);
            return newsDto;
        }
    }
}
