package com.usuarios.microserviciousuarios.infrastructure.shared.dto;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "tbl_user")
public class UserDto {
    @Id
    @Column(unique = true, nullable = false)
    private String identify;
    private String name;
    private String age;
    private Date dateCreate;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_identify", referencedColumnName = "identify"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
