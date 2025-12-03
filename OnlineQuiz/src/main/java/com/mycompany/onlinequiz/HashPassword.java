/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 *
 * @author kcuar
 */
public class HashPassword {
    public static void main(String[] args) throws Exception {
        // 1. Set the password you want to hash
        String password = "password"; // <-- replace with your desired password

        // 2. Create SHA-256 hash
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        // 3. Convert byte array to hex string
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        // 4. Print the SHA-256 hash
        System.out.println("SHA-256 hash of password: " + sb.toString());
    }
}
