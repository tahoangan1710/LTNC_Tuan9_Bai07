package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp trừu tượng đại diện cho một tài khoản ngân hàng.
 */
public abstract class Account {
    private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);

    public static final String CHECKING_TYPE = "CHECKING";
    public static final String SAVINGS_TYPE = "SAVINGS";

    private long accountNumber;
    private double balance;
    private List<bank_system.Transaction> transactionList;

    /**
     * Khởi tạo tài khoản.
     *
     * @param accountNumber số tài khoản
     * @param balance số dư ban đầu
     */
    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionList = new ArrayList<>();
    }

    /**
     * @return số tài khoản
     */
    public long getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber số tài khoản mới
     */
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return số dư hiện tại
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance số dư mới
     */
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return danh sách giao dịch
     */
    public List<bank_system.Transaction> getTransactionList() {
        return transactionList;
    }

    /**
     * Cập nhật danh sách giao dịch.
     *
     * @param transactionList danh sách giao dịch mới
     */
    public void setTransactionList(List<bank_system.Transaction> transactionList) {
        if (transactionList == null) {
            this.transactionList = new ArrayList<>();
        } else {
            this.transactionList = transactionList;
        }
    }

    /**
     * Nạp tiền vào tài khoản.
     *
     * @param amount số tiền cần nạp
     */
    public abstract void deposit(double amount);

    /**
     * Rút tiền khỏi tài khoản.
     *
     * @param amount số tiền cần rút
     */
    public abstract void withdraw(double amount);

    /**
     * Thực hiện phần cập nhật số dư khi nạp tiền.
     *
     * @param amount số tiền cần nạp
     * @throws bank_system.InvalidFundingAmountException nếu số tiền không hợp lệ
     */
    protected void doDepositing(double amount) throws bank_system.InvalidFundingAmountException {
        if (amount <= 0) {
            throw new bank_system.InvalidFundingAmountException(amount);
        }
        balance += amount;
        LOGGER.debug("Đã cập nhật số dư sau khi nạp: accountNumber={}", accountNumber);
    }

    /**
     * Thực hiện phần cập nhật số dư khi rút tiền.
     *
     * @param amount số tiền cần rút
     * @throws bank_system.BankException nếu số tiền không hợp lệ hoặc số dư không đủ
     */
    protected void doWithdrawing(double amount) throws bank_system.BankException {
        if (amount <= 0) {
            throw new bank_system.InvalidFundingAmountException(amount);
        }
        if (amount > balance) {
            throw new bank_system.InsufficientFundsException(amount);
        }
        balance -= amount;
        LOGGER.debug("Đã cập nhật số dư sau khi rút: accountNumber={}", accountNumber);
    }

    /**
     * Thêm giao dịch vào lịch sử tài khoản.
     *
     * @param transaction giao dịch cần thêm
     */
    public void addTransaction(bank_system.Transaction transaction) {
        if (transaction != null) {
            transactionList.add(transaction);
        }
    }

    /**
     * Lấy lịch sử giao dịch của tài khoản.
     *
     * @return lịch sử giao dịch dạng văn bản
     */
    public String getTransactionHistory() {
        StringBuilder history = new StringBuilder();
        history.append("Lịch sử giao dịch của tài khoản ")
                .append(accountNumber).append(":\n");

        for (int index = 0; index < transactionList.size(); index++) {
            history.append(transactionList.get(index).getTransactionSummary());
            if (index < transactionList.size() - 1) {
                history.append('\n');
            }
        }

        LOGGER.info("Đã truy xuất lịch sử giao dịch: accountNumber={}, transactionCount={}",
                accountNumber, transactionList.size());
        return history.toString();
    }

    /**
     * So sánh tài khoản theo số tài khoản.
     *
     * @param object đối tượng cần so sánh
     * @return true nếu hai tài khoản có cùng số tài khoản
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        return accountNumber == other.accountNumber;
    }

    /**
     * Tạo mã băm dựa trên số tài khoản.
     *
     * @return mã băm của tài khoản
     */
    @Override
    public int hashCode() {
        return (int) (accountNumber ^ (accountNumber >>> 32));
    }
}
