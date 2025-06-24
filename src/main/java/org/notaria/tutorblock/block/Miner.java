package org.notaria.tutorblock.block;

import org.notaria.tutorblock.data.Block;
import org.notaria.tutorblock.data.Contants;

public class Miner {
    private double reward;

    public void mine(Block block, BlockChain blockChain) {

        while (notGoldenHash(block)) {
            // generación del hash de bloque
            block.generateHash();
            block.incrementNonce();
        }

        System.out.println(block + " ha sido minado...");
        System.out.println("Hash es: " + block.getHash());
        // añadir el bloque a la cadena de bloques
        blockChain.addBlock(block);
        // cálculo de la recompensa
        reward += Contants.MINER_REWARD;

    }

    // Así que los mineros generarán valores hash hasta que encuentren el hash correcto.
    // que coincide con la variable DIFFICULTY declarada en la clase Constante
    public boolean notGoldenHash(Block block) {

        String leadingZeros = new String(new char[Contants.DIFFICULTY]).replace('\0', '0');

        return !block.getHash().substring(0, Contants.DIFFICULTY).equals(leadingZeros);
    }

    public double getReward() {
        return this.reward;
    }
}
