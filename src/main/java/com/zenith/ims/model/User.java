package com.zenith.ims.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "users")
@Data 
@NoArgsConstructor 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size(max = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role;
    @Column(nullable = false)
    private String companyId;

    public User(String username, String email, String password, Role role, String companyId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.companyId = companyId;
    }
}

