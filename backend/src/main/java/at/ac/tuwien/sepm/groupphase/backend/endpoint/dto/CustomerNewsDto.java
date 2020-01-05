package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CustomerNewsDto {

    @NotNull
    private News news;

    @NotNull
    private User user;

    @NotNull
    private boolean read;

    public static final class CustomerNewsDtoBuilder {

        private News news;
        private User user;
        private boolean read;

        public CustomerNewsDtoBuilder(){};

        public static CustomerNewsDtoBuilder anCustomerNewstoBuilder() {
            return new CustomerNewsDtoBuilder();
        }

        public CustomerNewsDto.CustomerNewsDtoBuilder withBoolean(Boolean read) {
            this.read = read;
            return this;
        }

        public CustomerNewsDto.CustomerNewsDtoBuilder withNews(News news) {
            this.news = news;
            return this;
        }

        public CustomerNewsDto.CustomerNewsDtoBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public CustomerNewsDto build() {
            CustomerNewsDto customerNewsDto = new CustomerNewsDto();
            customerNewsDto.setRead(read);
            customerNewsDto.setNews(news);
            customerNewsDto.setUser(user);
            return customerNewsDto;
        }
    }
}
