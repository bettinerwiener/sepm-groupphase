package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.util.Set;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, name = "postal_code")
    private Long postalCode;

    @OneToMany(mappedBy = "location")
    Set<EventPerformance> eventPerformances;

    @OneToMany(mappedBy = "location")
    Set<Room> rooms;

    public Location() {};

    public Location(String name, String street, String city, Long postalCode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }



    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
