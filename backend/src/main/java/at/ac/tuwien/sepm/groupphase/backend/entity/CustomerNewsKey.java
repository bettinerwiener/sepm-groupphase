package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerNewsKey implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerNewsKey)) return false;
        CustomerNewsKey that = (CustomerNewsKey) o;
        return getCustomer().equals(that.getCustomer()) &&
            getNews().equals(that.getNews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getNews());
    }
}
