package az.edu.bsu.smsproject.domain;

import az.edu.bsu.smsproject.domain.Enums.Status;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//todo is @Data or @Entity annotation needed for domain classes
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 4521808119054728114L;

    protected long id;

    @NotBlank(message = "Name is required")
    protected String name;

    protected Status status;

    public BaseDomain(long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public BaseDomain() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
