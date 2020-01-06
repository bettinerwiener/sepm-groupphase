package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class EmployeeNewsEventDto {

    @NotNull
    private Event event;

    @NotNull
    private News news;

    @NotNull
    private User employee;

    public static final class EmployeeNewsEventDtoBuilder {

        private Event event;
        private News news;
        private User employee;

        public EmployeeNewsEventDtoBuilder() {};

        public static EmployeeNewsEventDtoBuilder anEmployeeNewsEventDtoBuilder() {
            return new EmployeeNewsEventDtoBuilder();
        }

        public EmployeeNewsEventDto.EmployeeNewsEventDtoBuilder withEvent(Event event) {
            this.event = event;
            return this;
        }

        public EmployeeNewsEventDto.EmployeeNewsEventDtoBuilder withNews(News news) {
            this.news = news;
            return this;
        }

        public EmployeeNewsEventDto.EmployeeNewsEventDtoBuilder withEmployee(User employee) {
            this.employee = employee;
            return this;
        }

        public EmployeeNewsEventDto build() {
            EmployeeNewsEventDto employeeNewsEventDto = new EmployeeNewsEventDto();
            employeeNewsEventDto.setEvent(event);
            employeeNewsEventDto.setNews(news);
            employeeNewsEventDto.setEmployee(employee);
            return employeeNewsEventDto;
        }
    }
}
