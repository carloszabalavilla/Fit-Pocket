package edu.fpdual.webapplication.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test void acceptsValidCustomDomainsInsteadOfAProviderWhitelist() throws Exception {
        assertEquals("demo@example.test", new Email("demo@example.test").toString());
    }
    @Test void normalizesWhitespaceAndCase() throws Exception {
        assertEquals("demo@example.test", new Email(" Demo@Example.test ").toString());
    }
    @Test void rejectsMalformedAddresses() {
        for (String value : new String[]{"invalid", "a@@example.test", "a b@example.test", "demo@localhost"}) {
            assertThrows(InvalidEmailException.class, () -> new Email(value));
        }
    }
}
