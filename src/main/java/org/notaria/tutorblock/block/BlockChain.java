package org.notaria.tutorblock.block;

import java.util.ArrayList;
import java.util.List;

import org.notaria.tutorblock.data.Block;

public class BlockChain {

    //instanciar la lista de bloques
    private List<Block> blockChain;

    public List<Block> getBlockChain() {
        return blockChain;
    }

    public void setBlockChain(List<Block> blockChain) {
        this.blockChain = blockChain;
    }

    public BlockChain() {

        //además, instanciando la lista como ArrayList
        this.blockChain = new ArrayList<>();
    }

    public void addBlock(Block block) {
        this.blockChain.add(block);
    }

    public int size() {
        return this.blockChain.size();
    }

    @Override
    public String toString() {
        String blockChain = "";
        for(Block block : this.blockChain)
            blockChain+=block.toString()+"\n";
        return blockChain;
    }


}
