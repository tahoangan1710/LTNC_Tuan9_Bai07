package bank_system;

import java.util.Locale;

/**
 * Ngoại lệ được ném khi số tiền giao dịch không hợp lệ.
 */
public class InvalidFundingAmountException extends bank_system.BankException {
    /**
     * Tạo ngoại lệ cho số tiền không hợp lệ.
     *
     * @param amount số tiền được yêu cầu giao dịch
     */
    public InvalidFundingAmountException(double amount) {
        super("Số tiền không hợp lệ: $" + String.format(Locale.US, "%.2f", amount));
    }
}
