package bank_system;

/**
 * Ngoại lệ cơ sở cho các lỗi nghiệp vụ ngân hàng.
 */
public class BankException extends Exception {
    /**
     * Tạo ngoại lệ ngân hàng với thông báo cụ thể.
     *
     * @param message nội dung thông báo lỗi
     */
    public BankException(String message) {
        super(message);
    }
}
