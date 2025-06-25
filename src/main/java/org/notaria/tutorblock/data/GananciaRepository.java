package org.notaria.tutorblock.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GananciaRepository extends JpaRepository<Ganancia, Long> {}

