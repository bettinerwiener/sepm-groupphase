package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class CustomerNewsKey implements Serializable {

    private Long user;

    private Long news;
}
