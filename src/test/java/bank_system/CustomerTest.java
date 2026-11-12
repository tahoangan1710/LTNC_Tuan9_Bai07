package bank_system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class CustomerTest {
    @Test
    public void testConstructorsGettersAndSetters() {
        Customer customer = new Customer();
        assertEquals(0, customer.getIdNumber());
        assertEquals("", customer.getFullName());
        customer.setIdNumber(123456789);
        customer.setFullName("Nguyen Van A");
        assertEquals(123456789, customer.getIdNumber());
        assertEquals("Nguyen Van A", customer.getFullName());
        assertTrue(customer.getCustomerInfo().contains("Nguyen Van A"));
    }

    @Test
    public void testAccountListOperations() {
        Customer customer = new Customer(1, "A");
        CheckingAccount account = new CheckingAccount(10, 100);
        customer.addAccount(null);
        customer.addAccount(account);
        customer.addAccount(new CheckingAccount(10, 500));
        assertEquals(1, customer.getAccountList().size());
        customer.removeAccount(null);
        customer.removeAccount(account);
        assertTrue(customer.getAccountList().isEmpty());
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        customer.setAccountList(accounts);
        assertEquals(1, customer.getAccountList().size());
        customer.setAccountList(null);
        assertTrue(customer.getAccountList().isEmpty());
    }
}
