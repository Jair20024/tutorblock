package org.notaria.tutorblock.service;

// Asegúrate de que las rutas de importación sean correctas para tu proyecto
import org.notaria.tutorblock.block.BlockChain;
import org.notaria.tutorblock.block.Miner;
import org.notaria.tutorblock.data.Block;
import org.notaria.tutorblock.data.Contants;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct; // Usar jakarta.annotation.PostConstruct para Spring Boot 3+

@Service // Marca esta clase como un Componente de Servicio de Spring
public class BlockchainService {

    private BlockChain blockChain;
    private Miner miner;

    public BlockchainService() {
        // Constructor: Inicializa las instancias. Spring las manejará como un singleton.
        this.blockChain = new BlockChain();
        this.miner = new Miner();
    }

    @PostConstruct // Este método se ejecuta automáticamente después de que el bean es creado por Spring
    public void initializeBlockchain() {
        System.out.println("--- Inicializando Blockchain desde Spring Boot ---");

        // Lógica original del constructor de Principal:
        Block block0 = new Block(0, "transaction1", Contants.GENESIS_PREV_HASH);
        miner.mine(block0, blockChain);

        Block block1 = new Block(1, "transaction2", blockChain.getBlockChain().get(blockChain.size() - 1).getHash());
        miner.mine(block1, blockChain);

        Block block2 = new Block(2, "transaction3", blockChain.getBlockChain().get(blockChain.size() - 1).getHash());
        miner.mine(block2, blockChain);

        System.out.println("\n" + "BLOCKCHAIN INICIAL EN SPRING BOOT:\n" + blockChain);
        System.out.println("Ganancia del minero inicial: " + miner.getReward());

        System.out.println("--- Blockchain inicializada. Continúa con tus controladores y lógica de negocio ---");
    }

    // Métodos para interactuar con el blockchain desde otros servicios o controladores
    public BlockChain getBlockChain() {
        return blockChain;
    }

    public Miner getMiner() {
        return miner;
    }

    // Aquí agregarías métodos para añadir transacciones, minar, etc.,
    // según las interacciones de tu interfaz web.
    // Por ejemplo:
    public void addTransaction(String transactionDetails) {
        // Lógica para añadir una transacción a la cola y, si hay 3, minar un bloque.
        // Esto es mucho más complejo y será parte de tu implementación futura.
        System.out.println("Nueva transacción simulada: " + transactionDetails);
        // ... (implementar la lógica de 3 transacciones, selección de minero, etc.)
    }
}