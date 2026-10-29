package edu.fpdual.webapplication.utilities;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Email {
    private static final List<String> commonDomains = Arrays.asList(
            "gmail.com", "yahoo.com", "outlook.com", "hotmail.com",
            "aol.com", "icloud.com", "mail.com", "protonmail.com",
            "yandex.com", "gmx.com"
    );
    private String email;

    public Email(String email) throws InvalidEmailException {
        try {
            checkEmail(email.trim());
            this.email = email;
        } catch (InvalidEmailException e) {
            throw new InvalidEmailException(e.getMessage());
        }
    }

    public void checkEmail(String email) throws InvalidEmailException {
        if (email == null || !email.contains("@") || !email.contains(".") || email.contains(" ")) {
            throw new InvalidEmailException("El correo electrónico no es válido.");
        }

        String[] parts = email.split("@");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new InvalidEmailException("El correo electrónico no es válido.");
        }

        String[] domain = parts[1].toLowerCase().split("\\.");

        boolean isProvider = Arrays.stream(CommonProviders.values())
                .anyMatch(provider -> provider.name().equalsIgnoreCase(domain[0]));

        boolean isExtension = Arrays.stream(CommonExtensions.values())
                .anyMatch(extension -> extension.name().equalsIgnoreCase(domain[1]));

        if (!isProvider || !isExtension) {
            throw new InvalidEmailException("El dominio del correo electrónico no es válido.");
        }
    }

    @Override
    public String toString() {
        return email;
    }
}
