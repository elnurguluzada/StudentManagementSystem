package az.edu.bsu.smsproject.domain;

import java.io.Serializable;

public class Role extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -3201383828144212802L;

    private String status;
    private String defaultController;
    private String defaultPage;
}
