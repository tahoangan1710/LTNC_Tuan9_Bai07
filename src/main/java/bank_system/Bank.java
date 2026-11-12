package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Quản lý danh sách khách hàng và thông tin tài khoản ngân hàng.
 */
public class Bank {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bank.class);
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("\\d{9}");

    private List<bank_system.Customer> customerList;

    /**
     * Khởi tạo ngân hàng với danh sách khách hàng rỗng.
     */
    public Bank() {
        this.customerList = new ArrayList<>();
    }

    /**
     * @return danh sách khách hàng
     */
    public List<bank_system.Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Cập nhật danh sách khách hàng.
     *
     * @param customerList danh sách khách hàng mới
     */
    public void setCustomerList(List<bank_system.Customer> customerList) {
        if (customerList == null) {
            this.customerList = new ArrayList<>();
        } else {
            this.customerList = customerList;
        }
    }

    /**
     * Đọc danh sách khách hàng và tài khoản từ luồng đầu vào.
     * Mỗi dòng khách hàng kết thúc bằng mã định danh gồm 9 chữ số;
     * dòng tài khoản có định dạng: số tài khoản, loại tài khoản, số dư.
     *
     * @param inputStream luồng dữ liệu đầu vào
     */
    public void readCustomerList(InputStream inputStream) {
        if (inputStream == null) {
            LOGGER.warn("Không thể đọc danh sách khách hàng: inputStream là null");
            return;
        }

        LOGGER.info("Bắt đầu đọc danh sách khách hàng");
        bank_system.Customer currentCustomer = null;

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) {
                    continue;
                }

                int lastSpace = trimmedLine.lastIndexOf(' ');
                if (lastSpace <= 0) {
                    LOGGER.warn("Bỏ qua dòng dữ liệu không hợp lệ: {}", trimmedLine);
                    continue;
                }

                String lastToken = trimmedLine.substring(lastSpace + 1).trim();
                if (CUSTOMER_ID_PATTERN.matcher(lastToken).matches()) {
                    String customerName = trimmedLine.substring(0, lastSpace).trim();
                    try {
                        long customerId = Long.parseLong(lastToken);
                        currentCustomer = new bank_system.Customer(customerId, customerName);
                        customerList.add(currentCustomer);
                        LOGGER.info("Đã thêm khách hàng: customerId={}", customerId);
                    } catch (NumberFormatException exception) {
                        LOGGER.warn("Mã khách hàng không hợp lệ: {}", lastToken, exception);
                    }
                } else if (currentCustomer != null) {
                    addAccountFromLine(currentCustomer, trimmedLine);
                } else {
                    LOGGER.warn("Bỏ qua dòng tài khoản vì chưa có khách hàng: {}", trimmedLine);
                }
            }
            LOGGER.info("Hoàn tất đọc danh sách khách hàng: customerCount={}",
                    customerList.size());
        } catch (IOException exception) {
            LOGGER.error("Không thể đọc dữ liệu khách hàng", exception);
        }
    }

    private void addAccountFromLine(bank_system.Customer customer, String line) {
        String[] parts = line.split("\\s+");
        if (parts.length < 3) {
            LOGGER.warn("Dòng tài khoản không đủ dữ liệu: {}", line);
            return;
        }

        try {
            long accountNumber = Long.parseLong(parts[0]);
            double balance = Double.parseDouble(parts[2]);
            if (Account.CHECKING_TYPE.equals(parts[1])) {
                customer.addAccount(new bank_system.CheckingAccount(accountNumber, balance));
            } else if (Account.SAVINGS_TYPE.equals(parts[1])) {
                customer.addAccount(new bank_system.SavingsAccount(accountNumber, balance));
            } else {
                LOGGER.warn("Loại tài khoản không được hỗ trợ: {}", parts[1]);
                return;
            }
            LOGGER.debug("Đã thêm tài khoản: accountNumber={}, customerId={}",
                    accountNumber, customer.getIdNumber());
        } catch (NumberFormatException exception) {
            LOGGER.warn("Dữ liệu số tài khoản không hợp lệ: {}", line, exception);
        }
    }

    /**
     * Lấy thông tin khách hàng theo thứ tự tăng dần của mã định danh.
     *
     * @return thông tin khách hàng đã sắp xếp
     */
    public String getCustomersInfoByIdOrder() {
        customerList.sort(Comparator.comparingLong(bank_system.Customer::getIdNumber));
        StringBuilder result = new StringBuilder();
        for (bank_system.Customer customer : customerList) {
            if (result.length() > 0) {
                result.append('\n');
            }
            result.append(customer.getCustomerInfo());
        }
        return result.toString();
    }

    /**
     * Lấy thông tin khách hàng theo thứ tự họ tên, sau đó theo mã định danh.
     *
     * @return thông tin khách hàng đã sắp xếp
     */
    public String getCustomersInfoByNameOrder() {
        List<bank_system.Customer> sortedCustomers = new ArrayList<>(customerList);
        sortedCustomers.sort(Comparator.comparing(bank_system.Customer::getFullName)
                .thenComparingLong(bank_system.Customer::getIdNumber));

        StringBuilder result = new StringBuilder();
        for (bank_system.Customer customer : sortedCustomers) {
            if (result.length() > 0) {
                result.append('\n');
            }
            result.append(customer.getCustomerInfo());
        }
        return result.toString();
    }
}
