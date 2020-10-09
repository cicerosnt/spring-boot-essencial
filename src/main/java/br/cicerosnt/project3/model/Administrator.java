package br.cicerosnt.project3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Administrator extends AbstractEntity{

    @NotEmpty
    @Column(unique = true)
    private String userName;
    @NotEmpty
    @JsonIgnore
    private String userPassword;
    @NotEmpty
    private String name;
    @NotEmpty
    private boolean admin;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
