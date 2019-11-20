package hu.bme.szoftarch.library.libbackend.model;

import hu.bme.szoftarch.library.libbackend.model.enums.RoleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "role may not be blank")
    private RoleType name;

    public Role() {}

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

}
