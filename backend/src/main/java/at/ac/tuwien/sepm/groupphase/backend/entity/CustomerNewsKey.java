package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerNewsKey {

    @Column
    private Long customer;

    @Column
    private Long news;

    public CustomerNewsKey() {};
    public CustomerNewsKey(Long customer, Long news) {
        this.customer = customer;
        this.news = news;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getNews() {
        return news;
    }

    public void setNews(Long news) {
        this.news = news;
    }

}
