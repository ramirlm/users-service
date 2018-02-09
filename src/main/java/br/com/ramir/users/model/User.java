package br.com.ramir.users.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user")
@Validated
public class User implements Serializable {

    private static final long serialVersionUID = 5449082533640415196L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String name;

    @Column(nullable = false,unique = true)
    @NotNull
    @NotEmpty
    private String login;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String password;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(nullable = false,unique = true)
    @NotNull
    @NotEmpty
    private String email;

    @Column(nullable = false)
    @NotNull
    private Boolean admin;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void updateFields(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.admin = user.getAdmin();
        this.email = user.getEmail();
        this.name = user.getName();
        this.updatedDate = new Date();
    }
}
