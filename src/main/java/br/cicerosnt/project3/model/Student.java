package br.cicerosnt.project3.model;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;


@Entity
public class Student extends AbstractEntity {

    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
