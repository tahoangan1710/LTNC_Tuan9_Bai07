package bank_system;

import java.util.ArrayList;
import java.util.List;

/**
 * Đại diện cho một khách hàng của ngân hàng.
 */
public class Customer {
    private long idNumber;
    private String fullName;
    private List<Account> accountList;

    /**
     * Khởi tạo khách hàng mặc định.
     */
    public Customer() {
        this(0L, "");
    }

    /**
     * Khởi tạo khách hàng với mã định danh và họ tên.
     *
     * @param idNumber số định danh khách hàng
     * @param fullName họ tên khách hàng
     */
    public Customer(long idNumber, String fullName) {
        this.idNumber = idNumber;
        this.fullName = fullName;
        this.accountList = new ArrayList<>();
    }

    /**
     * @return số định danh khách hàng
     */
    public long getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber số định danh mới
     */
    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return họ tên khách hàng
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName họ tên mới
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return danh sách tài khoản của khách hàng
     */
    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * Cập nhật danh sách tài khoản.
     *
     * @param accountList danh sách tài khoản mới
     */
    public void setAccountList(List<Account> accountList) {
        if (accountList == null) {
            this.accountList = new ArrayList<>();
        } else {
            this.accountList = accountList;
        }
    }

    /**
     * Thêm tài khoản cho khách hàng nếu tài khoản chưa tồn tại.
     *
     * @param account tài khoản cần thêm
     */
    public void addAccount(Account account) {
        if (account == null) {
            return;
        }
        if (!accountList.contains(account)) {
            accountList.add(account);
        }
    }

    /**
     * Xóa tài khoản khỏi danh sách của khách hàng.
     *
     * @param account tài khoản cần xóa
     */
    public void removeAccount(Account account) {
        if (account == null) {
            return;
        }
        accountList.remove(account);
    }

    /**
     * Lấy thông tin khách hàng dạng văn bản.
     *
     * @return thông tin khách hàng
     */
    public String getCustomerInfo() {
        return "Số CMND: " + idNumber + ". Họ tên: " + fullName + ".";
    }
}
