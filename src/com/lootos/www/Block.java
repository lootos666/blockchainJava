package com.lootos.www;

import java.util.Date;

public class Block {
    public String hash; // цифровая подпись даного блока
    public String previousHash; // цифровая подпись предедущего блока
    private String data; // данные которые местит в себе блок
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calcHash();
    }

    public String calcHash() {
        String calcHash = StringHash.applySHA256(previousHash + Long.toString(timeStamp) + data + Integer.toString(nonce));
        return calcHash;
    }

    public void mineBlock(int difficult) {
        String target = new String(new char[difficult]).replace('\0', '0');
        while (!hash.substring(0, difficult).equals(target)) {
            nonce++;
            hash = calcHash();
        }
        System.out.println("Block mined : " + hash);
    }
}
