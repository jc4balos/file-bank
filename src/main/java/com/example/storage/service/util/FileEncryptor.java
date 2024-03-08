package com.example.storage.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class FileEncryptor {
    public String encryptFileName(String currentFileName) {
        try {

            String salt = generateSalt();

            // Combine filename and ID (if provided) into a single string
            String combinedString = currentFileName;
            combinedString += "-" + salt;

            // Create a message digest object with a secure algorithm
            MessageDigest hasher = MessageDigest.getInstance("SHA-256"); // Use SHA-256 for stronger security

            // Update the hasher with the combined string
            hasher.update(combinedString.getBytes());

            // Generate the hash and convert it to a hexadecimal string
            byte[] hashBytes = hasher.digest();
            return bytesToHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            // Handle the unlikely scenario where SHA-256 is not supported
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }

    // Helper method to convert a byte array to a hexadecimal string
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

}
