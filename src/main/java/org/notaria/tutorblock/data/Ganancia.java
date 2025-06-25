package org.notaria.tutorblock.data;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ganancia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ganancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idmin", referencedColumnName = "id")
    private Miner minero;

    @ManyToOne
    @JoinColumn(name = "idbloque", referencedColumnName = "id")
    private Block bloque;

    @Column(nullable = false)
    private BigDecimal ganancia;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;
}

