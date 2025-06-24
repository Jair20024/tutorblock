package org.notaria.tutorblock.data;

public class Contants {

    //difícultad en minado se define por los ceros a la izquierda
    //aquí, la dificultad se establece en 1 por lo tanto, tendrá 1 cero
    public static final int DIFFICULTY = 1;

    public static final double MINER_REWARD = 10;

    // valor hash anterior va a contener todos los ceros porque
    //el bloque Génesis es el primer bloque de la cadena de bloques.
    //Por lo tanto no tiene bloque anterior y lo asignaremos manualmente
    public static final String GENESIS_PREV_HASH = "0000000000000000000000000000000000000000000000000000000000000000";

    public Contants() {
        // TODO Auto-generated constructor stub
    }

}
