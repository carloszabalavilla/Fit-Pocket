package edu.fpdual.webapplication.utilities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Data
@NoArgsConstructor
public class Password {
    private String password;

    public Password(String password) {
        this.password = hashPassword(password);
    }

    public Password(String password, String userName) throws InvalidPasswordException {
        try {
            checkPassword(password, userName);
            this.password = hashPassword(password);
        } catch (InvalidPasswordException e) {
            throw new InvalidPasswordException(e.getMessage());
        }
    }

    public static String resetPassword() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int LENGTH = 8;
        SecureRandom random = new SecureRandom();
        StringBuilder newCodePassword = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            newCodePassword.append(randomChar);
        }
        return newCodePassword.toString();
    }

    private String hashPassword(String password) {
        String hashedPassword;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            hashedPassword = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            hashedPassword = null;
        }
        return hashedPassword;
    }

    public void checkPassword(String password, String userName) throws InvalidPasswordException {
        if (password.length() < 5) {
            throw new InvalidPasswordException("La contraseña debe tener al menos 5 caracteres.");
        }

        if (password.contains(userName)) {
            throw new InvalidPasswordException("La contraseña no puede contener el nombre de usuario.");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new InvalidPasswordException("La contraseña debe contener al menos una letra mayúscula.");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new InvalidPasswordException("La contraseña debe contener al menos una letra minúscula.");
        }

        if (!password.matches(".*\\d.*")) {
            throw new InvalidPasswordException("La contraseña debe contener al menos un número.");
        }

        if (!password.matches(".*[!@#$%^&*()].*")) {
            throw new InvalidPasswordException("La contraseña debe contener al menos un carácter especial.");
        }
    }

    @Override
    public String toString() {
        return password;
    }

}
