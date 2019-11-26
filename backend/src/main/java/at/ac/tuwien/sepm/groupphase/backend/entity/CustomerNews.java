package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer_news")
public class CustomerNews {

    @EmbeddedId
    private CustomerNewsKey id;

    @Column
    private Boolean read;

    @ManyToOne
    @MapsId("customer")
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @MapsId("news")
    @JoinColumn(name = "news")
    private News news;

    public CustomerNews() {};

    public CustomerNews(Customer customer, News news) {
        this.customer = customer;
        this.news = news;
    }

    public CustomerNewsKey getId() {
        return id;
    }

    public void setId(CustomerNewsKey id) {
        this.id = id;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
