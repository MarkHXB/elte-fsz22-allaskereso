package hu.elte.joooble.domain.user;


import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class Credential {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }
}
