package az.edu.bsu.smsproject.domain;

import az.edu.bsu.smsproject.domain.Enums.Status;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public abstract class User extends BaseDomain implements Serializable, Comparable<User> {
    private static final long serialVersionUID = -1691632973585761641L;

    protected long roleId;
    @NotBlank(message = "Surname is required")
    protected String surname;
    @NotBlank(message = "Email is required")
    protected String email;
    @NotBlank(message = "Password is required" )
    @Length(min = 5, message = "Password must contain at least 5 characters")
    protected String password;
//    todo regular expression
    protected String phoneNumber;
    protected String faculty;
    protected char gender;

    public User(long id, String name, Status status, long roleId, String surname, String email, String password, String phoneNumber, String faculty, char gender) {
        super(id, name, status);
        this.roleId = roleId;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.faculty = faculty;
        this.gender = gender;
    }

    public User() {
    }



    //TODO make sure
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        User user;
        if (obj instanceof User) {
            user = (User) obj;
            return this.id == user.getId() &&
                    this.name.equals(user.getName()) &&
                    this.surname.equals(user.getSurname()) &&
                    this.email.equals(user.getEmail());
        } else
            return false;

    }

    //TODO make sure
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", faculty='" + faculty + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public int compareTo(User o) {
        if (this.id != o.getId())
            return (int) (this.id - o.getId());
        else {
            if (!this.name.equals(o.getName()))
                return this.name.compareTo(o.getName());
            else
                return this.surname.compareTo(o.getSurname());
        }
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

}
