package com.lootos.www;

public class Main {
    public static void main(String[] args) {
        Block genesisBlock = new Block("The first block", "0");
        System.out.println("Hash for block 1 : " + genesisBlock.hash);

        Block secondBlock = new Block("The second block", genesisBlock.hash);
        System.out.println("Has for block 2 : " + secondBlock.hash);

        Block thridBlock = new Block("The thrid block", secondBlock.hash);
        System.out.println("Hash for block 3 : " + thridBlock.hash);
    }
}
