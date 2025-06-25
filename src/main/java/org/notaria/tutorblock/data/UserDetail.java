package org.notaria.tutorblock.data;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "userdetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private String nomCompleto;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(nullable = false)
    private String firmaDigital;

    @Column(nullable = false, unique = true)
    private String email;
}

