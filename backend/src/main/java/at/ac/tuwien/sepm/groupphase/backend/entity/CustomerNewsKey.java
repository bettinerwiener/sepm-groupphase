package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerNewsKey implements Serializable {

    private Long user;

    private Long news;

}
