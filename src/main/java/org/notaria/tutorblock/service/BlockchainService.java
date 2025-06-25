package org.notaria.tutorblock.service;

import jakarta.transaction.Transactional;
import org.notaria.tutorblock.block.BlockChain;
import org.notaria.tutorblock.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BlockchainService {

    private BlockChain blockChain = new BlockChain(); // solo si necesitas la cadena en memoria

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private MinerRepository minerRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private GananciaRepository gananciaRepository;

    /**
     * Revisa si hay 3 transacciones pendientes, y si las hay:
     * - Crea un nuevo bloque
     * - Asigna un minero aleatorio
     * - Mina el bloque
     * - Guarda el bloque y la ganancia
     * - Cambia el estado de las transacciones a "procesada"
     */
    @Transactional
    public void verificarYMinarSiCorresponde() {
        List<Transaccion> pendientes = transaccionRepository.findAll().stream()
                .filter(t -> "pendiente".equalsIgnoreCase(t.getEstado()))
                .limit(3)
                .collect(Collectors.toList());

        if (pendientes.size() < 3) return;

        List<org.notaria.tutorblock.data.Miner> mineros = minerRepository.findAll();
        if (mineros.isEmpty()) return;

        org.notaria.tutorblock.data.Miner mineroSeleccionado =
                mineros.get(new Random().nextInt(mineros.size()));

        // Armar contenido de las transacciones
        StringBuilder contenido = new StringBuilder();
        for (Transaccion t : pendientes) {
            contenido.append(t.getId()).append(",");
        }

        // Crear bloque
        Block bloque = Block.builder()
                .nonce(0)
                .timeStamp(System.currentTimeMillis())
                .previousHash(obtenerUltimoHash())
                .transaction(contenido.toString())
                .build();

        // Usar lógica de minado con la clase de minado (block.Miner)
        org.notaria.tutorblock.block.Miner minerLogic = new org.notaria.tutorblock.block.Miner();
        minerLogic.mine(bloque, null); // null porque no se usa la cadena completa en DB

        // Guardar bloque
        blockRepository.save(bloque);

        // Registrar ganancia
        Ganancia ganancia = Ganancia.builder()
                .bloque(bloque)
                .minero(mineroSeleccionado)
                .ganancia(BigDecimal.valueOf(Contants.MINER_REWARD))
                .fecha(LocalDate.now())
                .hora(LocalTime.now())
                .build();
        gananciaRepository.save(ganancia);

        // Marcar transacciones como procesadas
        for (Transaccion t : pendientes) {
            t.setEstado("procesada");
            transaccionRepository.save(t);
        }

        // Consola opcional
        System.out.println("✅ Se minó un nuevo bloque con ID: " + bloque.getId()
                + " y minero: " + mineroSeleccionado.getNombre());
    }

    private String obtenerUltimoHash() {
        return blockRepository.findAll().stream()
                .reduce((first, second) -> second)
                .map(Block::getHash)
                .orElse(Contants.GENESIS_PREV_HASH);
    }
}
