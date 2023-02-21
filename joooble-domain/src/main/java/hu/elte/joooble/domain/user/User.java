package hu.elte.joooble.domain.user;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @OneToOne
    private Credential credential;

    @NonNull
    @Column(name = "IsActive",  nullable = false)
    private boolean isActive;


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Credential getCredential() {
        return credential;
    }
    public void setCredential(@NonNull Credential credential) {
        this.credential = credential;
    }
}
