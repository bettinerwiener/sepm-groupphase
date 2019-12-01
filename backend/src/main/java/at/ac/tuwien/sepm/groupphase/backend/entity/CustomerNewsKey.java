package at.ac.tuwien.sepm.groupphase.backend.entity;

import java.io.Serializable;
import java.util.Objects;

public class CustomerNewsKey implements Serializable {

    private Long user;

    private Long news;

    public CustomerNewsKey() {};
    public CustomerNewsKey(Long user, Long news) {
        this.user = user;
        this.news = news;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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
        return getUser().equals(that.getUser()) &&
            getNews().equals(that.getNews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getNews());
    }
}
