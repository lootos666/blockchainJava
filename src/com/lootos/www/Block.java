package com.lootos.www;

import java.util.Date;

public class Block {
    public String hash; // цифровая подпись даного блока
    public String previousHash; // цифровая подпись предедущего блока
    private String data; // данные которые местит в себе блок
    public long timeStamp;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calcHash();
    }

    public String calcHash() {
        String calcHash = StringHash.applySHA256(previousHash + Long.toString(timeStamp) + data);
        return calcHash;
    }
}
