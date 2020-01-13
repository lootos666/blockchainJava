package com.lootos.www;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;


public class Transaction {
    public byte[] signature;
    public static int sequence = 0;
    public float value;

    public String transactionID;
    public PublicKey sender;
    public PublicKey receiver;

    public ArrayList<Transaction> inputs = new ArrayList<Transaction>();
    public ArrayList<Transaction> outputs = new ArrayList<Transaction>();

    Transaction(PublicKey sender, PublicKey receiver, float value, ArrayList<Transaction> inputs) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
        this.inputs = inputs;
    }

    private String calcHash() {
        sequence++;
        return StringHash.applySHA256(sender.toString() + receiver.toString() + Float.toString(value) + sequence);
    }

    public void genSignature(PrivateKey privateKey) {
        String data = StringHash.getStringFromKey(sender) + StringHash.getStringFromKey(receiver) + Float.toString(value);
        signature = StringHash.applyECDSASig(privateKey, data);
    }

    public boolean verifySign() {
        String data = StringHash.getStringFromKey(sender) + StringHash.getStringFromKey(receiver) + Float.toString(value);
        return StringHash.verifyECDSASig(sender, data, signature);
    }
}
