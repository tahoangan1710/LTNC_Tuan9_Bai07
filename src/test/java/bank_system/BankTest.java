package bank_system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class BankTest {
    @Test
    public void testReadCustomerList() {
        Bank bank = new Bank();
        bank.readCustomerList(null);
        String data = ""
                + "orphan line\n"
                + "Nguyen Van A 123456789\n"
                + "\n"
                + "100 CHECKING 1000\n"
                + "101 SAVINGS 6000\n"
                + "bad line\n"
                + "102 UNKNOWN 500\n"
                + "abc CHECKING 100\n"
                + "Nguyen Van B 987654321\n"
                + "200 CHECKING 2000 extra\n";
        bank.readCustomerList(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
        assertEquals(2, bank.getCustomerList().size());
        assertEquals(2, bank.getCustomerList().get(0).getAccountList().size());
        assertEquals(1, bank.getCustomerList().get(1).getAccountList().size());
    }

    @Test
    public void testSetAndSortCustomers() {
        Bank bank = new Bank();
        bank.setCustomerList(null);
        assertTrue(bank.getCustomerList().isEmpty());
        Customer second = new Customer(2, "Binh");
        Customer first = new Customer(1, "An");
        bank.setCustomerList(new ArrayList<>(Arrays.asList(second, first)));
        String byId = bank.getCustomersInfoByIdOrder();
        assertTrue(byId.indexOf("Số CMND: 1") < byId.indexOf("Số CMND: 2"));
        bank.setCustomerList(new ArrayList<>(Arrays.asList(second, first)));
        String byName = bank.getCustomersInfoByNameOrder();
        assertTrue(byName.indexOf("Họ tên: An") < byName.indexOf("Họ tên: Binh"));
        assertEquals(2, bank.getCustomerList().size());
    }
}
