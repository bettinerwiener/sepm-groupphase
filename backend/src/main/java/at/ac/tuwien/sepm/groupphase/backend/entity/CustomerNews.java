package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer_news")
@IdClass(CustomerNewsKey.class)
public class CustomerNews {

    @Column
    private Boolean read;

    @Id
    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user")
    private User user;

    @Id
    @ManyToOne
    @MapsId("news")
    @JoinColumn(name = "news")
    private News news;

    public CustomerNews() {};

    public CustomerNews(User user, News news) {
        this.user = user;
        this.news = news;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
