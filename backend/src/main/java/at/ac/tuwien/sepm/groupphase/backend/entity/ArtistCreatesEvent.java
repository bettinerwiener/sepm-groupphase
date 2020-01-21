package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "artist_creates_event")
@IdClass(ArtistCreatesEventKey.class)
@Data
public class ArtistCreatesEvent {

    @Id
    @ManyToOne
    @MapsId("artist")
    @JoinColumn(name = "artist")
    private Artist artist;

    @Id
    @ManyToOne
    @MapsId("event")
    @JoinColumn(name = "event")
    private Event event;

}

