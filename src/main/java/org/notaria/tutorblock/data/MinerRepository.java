package org.notaria.tutorblock.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MinerRepository extends JpaRepository<Miner, Long> {
    Optional<Miner> findByNombre(String nombre);
}
