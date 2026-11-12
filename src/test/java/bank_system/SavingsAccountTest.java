package bank_system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SavingsAccountTest {
    @Test
    public void testDepositSuccessAndFailure() {
        SavingsAccount account = new SavingsAccount(1, 6000);
        account.deposit(1000);
        assertEquals(7000, account.getBalance(), 0.001);
        assertEquals(1, account.getTransactionList().size());
        account.deposit(0);
        assertEquals(7000, account.getBalance(), 0.001);
        assertEquals(1, account.getTransactionList().size());
    }

    @Test
    public void testWithdrawSuccessAndFailureCases() {
        SavingsAccount account = new SavingsAccount(2, 7000);
        account.withdraw(1000);
        assertEquals(6000, account.getBalance(), 0.001);
        assertEquals(1, account.getTransactionList().size());
        account.withdraw(1001);
        account.withdraw(1000);
        account.withdraw(1);
        account.withdraw(0);
        assertEquals(5000, account.getBalance(), 0.001);
        assertEquals(2, account.getTransactionList().size());
        assertTrue(account.getTransactionHistory().contains("Rút tiền tiết kiệm"));
    }
}
