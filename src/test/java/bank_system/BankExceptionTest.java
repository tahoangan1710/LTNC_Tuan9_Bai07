package bank_system;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BankExceptionTest {
    @Test
    public void testExceptionMessages() {
        BankException base = new BankException("error");
        InvalidFundingAmountException invalid = new InvalidFundingAmountException(-5);
        InsufficientFundsException insufficient = new InsufficientFundsException(100);
        assertTrue(base.getMessage().contains("error"));
        assertTrue(invalid.getMessage().contains("-5.00"));
        assertTrue(insufficient.getMessage().contains("100.00"));
    }
}
