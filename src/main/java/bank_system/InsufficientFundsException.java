package bank_system;

import java.util.Locale;

/**
 * Ngoại lệ được ném khi tài khoản không đủ số dư.
 */
public class InsufficientFundsException extends bank_system.BankException {
    /**
     * Tạo ngoại lệ khi số dư không đủ để thực hiện giao dịch.
     *
     * @param amount số tiền cần giao dịch
     */
    public InsufficientFundsException(double amount) {
        super("Số dư tài khoản không đủ $" + String.format(Locale.US, "%.2f", amount)
                + " để thực hiện giao dịch");
    }
}
