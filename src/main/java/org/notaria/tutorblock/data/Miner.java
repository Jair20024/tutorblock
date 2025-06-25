package org.notaria.tutorblock.data;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "miner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Miner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String clave;
}

