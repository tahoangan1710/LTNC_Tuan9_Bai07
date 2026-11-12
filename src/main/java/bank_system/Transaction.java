package bank_system;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Đại diện cho một giao dịch trên tài khoản ngân hàng.
 */
public class Transaction {
    private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

    public static final int TYPE_DEPOSIT_CHECKING = 1;
    public static final int TYPE_WITHDRAW_CHECKING = 2;
    public static final int TYPE_DEPOSIT_SAVINGS = 3;
    public static final int TYPE_WITHDRAW_SAVINGS = 4;

    private int type;
    private double amount;
    private double initialBalance;
    private double finalBalance;

    /**
     * Khởi tạo một giao dịch.
     *
     * @param type loại giao dịch
     * @param amount số tiền giao dịch
     * @param initialBalance số dư trước giao dịch
     * @param finalBalance số dư sau giao dịch
     */
    public Transaction(int type, double amount, double initialBalance, double finalBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    /**
     * @return mã loại giao dịch
     */
    public int getType() {
        return type;
    }

    /**
     * @param type mã loại giao dịch mới
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return số tiền giao dịch
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount số tiền giao dịch mới
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return số dư trước giao dịch
     */
    public double getInitialBalance() {
        return initialBalance;
    }

    /**
     * @param initialBalance số dư trước giao dịch mới
     */
    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    /**
     * @return số dư sau giao dịch
     */
    public double getFinalBalance() {
        return finalBalance;
    }

    /**
     * @param finalBalance số dư sau giao dịch mới
     */
    public void setFinalBalance(double finalBalance) {
        this.finalBalance = finalBalance;
    }

    /**
     * Chuyển mã loại giao dịch thành mô tả tiếng Việt.
     *
     * @param transactionType mã loại giao dịch
     * @return mô tả loại giao dịch
     */
    public static String getTypeString(int transactionType) {
        switch (transactionType) {
            case TYPE_DEPOSIT_CHECKING:
                return "Nạp tiền vãng lai";
            case TYPE_WITHDRAW_CHECKING:
                return "Rút tiền vãng lai";
            case TYPE_DEPOSIT_SAVINGS:
                return "Nạp tiền tiết kiệm";
            case TYPE_WITHDRAW_SAVINGS:
                return "Rút tiền tiết kiệm";
            default:
                return "Không rõ";
        }
    }

    /**
     * Tạo nội dung tóm tắt giao dịch.
     *
     * @return chuỗi mô tả giao dịch
     */
    public String getTransactionSummary() {
        LOGGER.debug("Tạo tóm tắt giao dịch: type={}", type);
        String formattedInitialBalance = String.format(Locale.US, "%.2f", initialBalance);
        String formattedAmount = String.format(Locale.US, "%.2f", amount);
        String formattedFinalBalance = String.format(Locale.US, "%.2f", finalBalance);

        return "- Kiểu giao dịch: " + getTypeString(type)
                + ". Số dư ban đầu: $" + formattedInitialBalance
                + ". Số tiền: $" + formattedAmount
                + ". Số dư cuối: $" + formattedFinalBalance + ".";
    }
}
