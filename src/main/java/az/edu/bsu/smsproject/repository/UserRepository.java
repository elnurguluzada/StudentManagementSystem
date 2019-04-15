package az.edu.bsu.smsproject.repository;

import az.edu.bsu.smsproject.domain.User;

public interface UserRepository {
    public User authenticate( String email, String password );
}
