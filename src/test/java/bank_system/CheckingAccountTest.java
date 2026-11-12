
package bank_system;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CheckingAccountTest {

    @Test
    public void testDeposit() {
        CheckingAccount account = new CheckingAccount(123456, 1000);

        account.deposit(500);

        assertEquals(1500, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() {
        CheckingAccount account = new CheckingAccount(123456, 1000);

        account.withdraw(300);

        assertEquals(700, account.getBalance(), 0.001);
    }
}
