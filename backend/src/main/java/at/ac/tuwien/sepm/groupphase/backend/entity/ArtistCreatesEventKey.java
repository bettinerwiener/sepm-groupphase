package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArtistCreatesEventKey implements Serializable {

    private Long artist;

    private Long event;

}
