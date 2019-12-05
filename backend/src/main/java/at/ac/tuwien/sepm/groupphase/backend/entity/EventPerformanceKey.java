package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class EventPerformanceKey implements Serializable {

    private Long event;

    private Long room;

    private LocalDateTime date;
}
