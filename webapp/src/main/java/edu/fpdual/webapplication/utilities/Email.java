package edu.fpdual.webapplication.utilities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Locale;

@Data
@NoArgsConstructor
public class Email {
    private String email;
    public Email(String email) throws InvalidEmailException {
        checkEmail(email);
        this.email = email.trim().toLowerCase(Locale.ROOT);
    }
    public void checkEmail(String email) throws InvalidEmailException {
        if (email == null || email.isBlank() || email.trim().contains(" ")) {
            throw new InvalidEmailException("El correo electrónico no es válido.");
        }
        try {
            InternetAddress address = new InternetAddress(email.trim(), true);
            address.validate();
            String value = address.getAddress();
            if (!value.equals(email.trim()) || !value.substring(value.indexOf('@') + 1).contains(".")) {
                throw new AddressException("Invalid domain");
            }
        } catch (AddressException ex) {
            throw new InvalidEmailException("El correo electrónico no es válido.");
        }
    }
    @Override
    public String toString() { return email; }
}
