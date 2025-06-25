package org.notaria.tutorblock.data;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "transacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "id1", referencedColumnName = "id")
    private UserDetail emisor;

    @ManyToOne
    @JoinColumn(name = "id2", referencedColumnName = "id")
    private UserDetail receptor;

    @Column(nullable = false)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private String estado;
}

