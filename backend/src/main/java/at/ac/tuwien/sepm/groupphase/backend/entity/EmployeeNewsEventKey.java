package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class EmployeeNewsEventKey implements Serializable {

    private Long employee;

    private Long news;

    private Long event;

    public EmployeeNewsEventKey() {};

    public EmployeeNewsEventKey(Long employee, Long news, Long event) {
        this.employee = employee;
        this.news = news;
        this.event = event;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getNews() {
        return news;
    }

    public void setNews(Long news) {
        this.news = news;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeNewsEventKey)) return false;
        EmployeeNewsEventKey that = (EmployeeNewsEventKey) o;
        return employee.equals(that.employee) &&
            news.equals(that.news) &&
            event.equals(that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, news, event);
    }
}
