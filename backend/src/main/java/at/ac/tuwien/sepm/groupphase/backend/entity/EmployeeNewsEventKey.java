package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmployeeNewsEventKey implements Serializable {

    @Column(name = "employee")
    private Long employee;

    @Column(name = "news")
    private Long news;

    @Column(name = "event")
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
}
