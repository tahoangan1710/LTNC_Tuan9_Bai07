package bank_system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TransactionTest {
    @Test
    public void testGettersAndSetters() {
        Transaction transaction = new Transaction(1, 10, 20, 30);
        assertEquals(1, transaction.getType());
        assertEquals(10, transaction.getAmount(), 0.001);
        assertEquals(20, transaction.getInitialBalance(), 0.001);
        assertEquals(30, transaction.getFinalBalance(), 0.001);
        transaction.setType(4);
        transaction.setAmount(40);
        transaction.setInitialBalance(50);
        transaction.setFinalBalance(60);
        assertEquals(4, transaction.getType());
        assertEquals(40, transaction.getAmount(), 0.001);
        assertEquals(50, transaction.getInitialBalance(), 0.001);
        assertEquals(60, transaction.getFinalBalance(), 0.001);
    }

    @Test
    public void testTransactionTypesAndSummary() {
        assertEquals("Nạp tiền vãng lai", Transaction.getTypeString(1));
        assertEquals("Rút tiền vãng lai", Transaction.getTypeString(2));
        assertEquals("Nạp tiền tiết kiệm", Transaction.getTypeString(3));
        assertEquals("Rút tiền tiết kiệm", Transaction.getTypeString(4));
        assertEquals("Không rõ", Transaction.getTypeString(99));
        String summary = new Transaction(99, 12.5, 100, 87.5).getTransactionSummary();
        assertTrue(summary.contains("Không rõ"));
        assertTrue(summary.contains("$12.50"));
        assertTrue(summary.contains("$87.50"));
    }
}
