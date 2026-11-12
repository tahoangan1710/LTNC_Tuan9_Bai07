package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản tiết kiệm với giới hạn rút tiền và số dư tối thiểu.
 */
public class SavingsAccount extends Account {
    private static final Logger LOGGER = LoggerFactory.getLogger(SavingsAccount.class);

    private static final double MAX_WITHDRAW = 1000.0;
    private static final double MIN_BALANCE = 5000.0;

    /**
     * Khởi tạo tài khoản tiết kiệm.
     *
     * @param accountNumber số tài khoản
     * @param balance số dư ban đầu
     */
    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * Nạp tiền vào tài khoản tiết kiệm.
     *
     * @param amount số tiền cần nạp
     */
    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();
            bank_system.Transaction transaction = new bank_system.Transaction(
                    bank_system.Transaction.TYPE_DEPOSIT_SAVINGS,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(transaction);
            LOGGER.info("Nạp tiền tiết kiệm thành công: accountNumber={}, amount={}, balance={}",
                    getAccountNumber(), amount, finalBalance);
        } catch (BankException exception) {
            LOGGER.warn("Nạp tiền tiết kiệm thất bại: accountNumber={}, amount={}, reason={}",
                    getAccountNumber(), amount, exception.getMessage());
        }
    }

    /**
     * Rút tiền khỏi tài khoản tiết kiệm.
     *
     * @param amount số tiền cần rút
     */
    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            if (amount > MAX_WITHDRAW) {
                throw new InvalidFundingAmountException(amount);
            }
            if (initialBalance - amount < MIN_BALANCE) {
                throw new InsufficientFundsException(amount);
            }

            doWithdrawing(amount);
            double finalBalance = getBalance();
            bank_system.Transaction transaction = new bank_system.Transaction(
                    bank_system.Transaction.TYPE_WITHDRAW_SAVINGS,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(transaction);
            LOGGER.info("Rút tiền tiết kiệm thành công: accountNumber={}, amount={}, balance={}",
                    getAccountNumber(), amount, finalBalance);
        } catch (BankException exception) {
            LOGGER.warn("Rút tiền tiết kiệm thất bại: accountNumber={}, amount={}, reason={}",
                    getAccountNumber(), amount, exception.getMessage());
        }
    }
}
