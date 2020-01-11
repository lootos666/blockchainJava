package com.lootos.www;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    private static int difficulty = 6;

    public static void main(String[] args) {
        blockchain.add(new Block("The first block", "0"));
        System.out.println("Trying to mine block 1\n");
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block("The second block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Tryig to mine block 2\n");
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block("The thrid block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to mine block 3\n");
        blockchain.get(2).mineBlock(difficulty);

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calcHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes not equal");
                return false;
            }

            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasnt been mined");
                return false;
            }
        }
        return true;
    }
}
