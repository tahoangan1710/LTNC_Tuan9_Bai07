package bank_system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class AccountTest {
    private static class TestAccount extends Account {
        TestAccount(long number, double balance) {
            super(number, balance);
        }

        @Override
        public void deposit(double amount) {
            try {
                doDepositing(amount);
            } catch (BankException exception) {
                // Expected invalid amount.
            }
        }

        @Override
        public void withdraw(double amount) {
            try {
                doWithdrawing(amount);
            } catch (BankException exception) {
                // Expected invalid amount or insufficient funds.
            }
        }

        void changeBalance(double balance) {
            setBalance(balance);
        }
    }

    @Test
    public void testGettersSettersAndBalanceOperations() {
        TestAccount account = new TestAccount(123, 1000);
        assertEquals(123, account.getAccountNumber());
        assertEquals(1000, account.getBalance(), 0.001);
        account.setAccountNumber(456);
        account.changeBalance(2000);
        assertEquals(456, account.getAccountNumber());
        assertEquals(2000, account.getBalance(), 0.001);
        account.deposit(500);
        assertEquals(2500, account.getBalance(), 0.001);
        account.withdraw(300);
        assertEquals(2200, account.getBalance(), 0.001);
    }

    @Test
    public void testInvalidDepositAndWithdraw() {
        TestAccount account = new TestAccount(1, 100);
        account.deposit(0);
        account.withdraw(-1);
        account.withdraw(200);
        assertEquals(100, account.getBalance(), 0.001);
    }

    @Test
    public void testTransactionListAndHistory() {
        TestAccount account = new TestAccount(10, 100);
        assertTrue(account.getTransactionHistory().contains("10"));
        account.addTransaction(null);
        Transaction transaction = new Transaction(1, 20, 100, 120);
        account.addTransaction(transaction);
        assertEquals(1, account.getTransactionList().size());
        assertTrue(account.getTransactionHistory().contains("Nạp tiền vãng lai"));
        List<Transaction> list = new ArrayList<>();
        list.add(transaction);
        account.setTransactionList(list);
        assertEquals(1, account.getTransactionList().size());
        account.setTransactionList(null);
        assertTrue(account.getTransactionList().isEmpty());
    }

    @Test
    public void testEqualsAndHashCode() {
        TestAccount first = new TestAccount(123, 10);
        TestAccount second = new TestAccount(123, 500);
        TestAccount third = new TestAccount(456, 10);
        assertTrue(first.equals(first));
        assertTrue(first.equals(second));
        assertFalse(first.equals(third));
        assertFalse(first.equals("account"));
        assertEquals(first.hashCode(), second.hashCode());
    }
}
