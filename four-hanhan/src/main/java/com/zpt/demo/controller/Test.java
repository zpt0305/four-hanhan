package com.zpt.demo.controller;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.jnlp.ClipboardService;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Test {

    private final static int SIZE = 1000000 / 5;

    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static String privateKeyStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOUubn4qizJtpx+n\n" +
            "yHOOfV+XbBPsC7aWblIS3Pqv7/DBJ+rkp9gfyrUA78L3YjASmmU1uupiY5dC7o/T\n" +
            "uaDSVA4K8Tmx22YC8PZ7HfAJ5/yVaaB2HmpSjg4xyahLPcgCn/v8WcxqDUh6VJ92\n" +
            "uic8EPFrOno1SZBkhOxgroKwSX97AgMBAAECgYEAmQx28X6L0rNzpiowLSt9Andm\n" +
            "z68U62xuZBUAydDwlYEInU7x39zrTBFCDJuUULI7tVc6aggmpf8mvZoRHxsW0b5j\n" +
            "HLqOwy7fKfAzBuFxWYJYKZmha4Tmll0QmTqU505rLtOQTjlf9Zzsr2Jmza64HuzK\n" +
            "Ui8r8dXHGK3oUZ49rhECQQD175WgBvEG/vwfLFFxK1b4pzeWYTjNgEYBvYhjHjxQ\n" +
            "Q3fic/msjxO6yqzLsJneu9IyLUKlWWPZ5AoVTwXNUXKPAkEA7o9T2ruKXw215x29\n" +
            "gGQMSuUJrBJ4J2T2zv2F6VPQyuD1xZpVYU6fRTGBPnkw9dYZ0xFdRQ4I2cyKVrD+\n" +
            "nt0qVQJAOmMZ67caK+YHZ0M3Rp3adQgF+26zdJ5agHlF0vpPqWKLKLkN8ni5X2RU\n" +
            "p7sSnL2MhpsWMnlJamZoOmzbXMZUYwJBAIEe8rQhbfOk6B//6OHlRQIElgect4wb\n" +
            "CbtfXWu9AfXNbTlXH39bnrlE4j9+ORHWoIOtkl4eCoxYOUhS5H34F0ECQAjz4laB\n" +
            "Yt1Zx5df0+67go2Ya6THgZnDafne8wqnQW6m5DEyidFZM36QDvpyrxAiPzG4QGR3\n" +
            "HLU0VEwSQa7rvmY=";

    private static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDlLm5+Kosybacfp8hzjn1fl2wT\n" +
            "7Au2lm5SEtz6r+/wwSfq5KfYH8q1AO/C92IwEpplNbrqYmOXQu6P07mg0lQOCvE5\n" +
            "sdtmAvD2ex3wCef8lWmgdh5qUo4OMcmoSz3IAp/7/FnMag1IelSfdronPBDxazp6\n" +
            "NUmQZITsYK6CsEl/ewIDAQAB";

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        List<List<Integer>> list2 = new ArrayList<>();
        for (int i =0; i < 1000000; i = i + SIZE) {
            list2.add(list.subList(i,i + SIZE));
        }

        CountDownLatch latch = new CountDownLatch(5);
        List<FutureTask<Long>> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MyCallable myCallable = new MyCallable(latch,i,list2.get(i));
            FutureTask<Long> futureTask = new FutureTask(myCallable);
            Thread t = new Thread(futureTask);
            t.start();
            result.add(futureTask);
        }
        latch.await();

        long sum = 0L;
        for (FutureTask<Long> longFutureTask : result) {
            sum += longFutureTask.get();
        }
        System.out.println(sum);

        /*long sum1 = 0;
        for (Integer integer : list) {
            sum1 += integer;
        }
        System.out.println(sum1);*/
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //数字签名
    public static byte[] sign(byte[] data) throws Exception {
        PrivateKey priK = getPrivateKey(privateKeyStr);
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initSign(priK);
        sig.update(data);
        return sig.sign();
    }

    public static byte[] encrypt(byte[] bt_plaintext) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bt_encrypted = cipher.doFinal(bt_plaintext);
        return bt_encrypted;
    }

    //根据字符串获取公钥
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    //签名验证
    public static boolean verify(byte[] data, byte[] sign) throws Exception {
        PublicKey pubK = getPublicKey(publicKeyString);
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(pubK);
        sig.update(data);
        return sig.verify(sign);
    }

    //解密
    public static byte[] decrypt(byte[] bt_encrypted) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyString);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bt_original = cipher.doFinal(bt_encrypted);
        return bt_original;
    }

}

class MyCallable implements Callable {
    CountDownLatch countDownLatch;
    int i = 0;
    List<Integer> list = new ArrayList<>();

    public MyCallable(){}

    public MyCallable(CountDownLatch countDownLatch,int i,List<Integer> list){
        this.countDownLatch = countDownLatch;
        this.i = i;
        this.list = list;
    }

    @Override
    public Long call() throws Exception {
        Long sum = new Long(0);
        for (Integer integer : list) {
            sum += integer;
        }
        countDownLatch.countDown();
        return sum;
    }
}
