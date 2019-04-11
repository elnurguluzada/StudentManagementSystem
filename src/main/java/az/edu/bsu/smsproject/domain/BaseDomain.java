package az.edu.bsu.smsproject.domain;

import java.io.Serializable;

public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 4521808119054728114L;

    protected long id;
    protected String name;

    public BaseDomain(long id, String name) {
        this.id = id;
        this.name = name;
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
}
