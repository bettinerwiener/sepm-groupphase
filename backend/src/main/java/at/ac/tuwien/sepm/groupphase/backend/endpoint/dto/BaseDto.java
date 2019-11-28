package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

public abstract class BaseDto {

    private Long id;

    protected BaseDto() {};

    public BaseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
