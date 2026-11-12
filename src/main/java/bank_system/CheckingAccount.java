package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản vãng lai.
 */
public class CheckingAccount extends Account {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckingAccount.class);

    /**
     * Khởi tạo tài khoản vãng lai.
     *
     * @param accountNumber số tài khoản
     * @param balance số dư ban đầu
     */
    public CheckingAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * Nạp tiền vào tài khoản vãng lai.
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
                    bank_system.Transaction.TYPE_DEPOSIT_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(transaction);
            LOGGER.info("Nạp tiền thành công: accountNumber={}, amount={}, balance={}",
                    getAccountNumber(), amount, finalBalance);
        } catch (bank_system.BankException exception) {
            LOGGER.warn("Nạp tiền thất bại: accountNumber={}, amount={}, reason={}",
                    getAccountNumber(), amount, exception.getMessage());
        }
    }

    /**
     * Rút tiền khỏi tài khoản vãng lai.
     *
     * @param amount số tiền cần rút
     */
    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            doWithdrawing(amount);
            double finalBalance = getBalance();
            bank_system.Transaction transaction = new bank_system.Transaction(
                    bank_system.Transaction.TYPE_WITHDRAW_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(transaction);
            LOGGER.info("Rút tiền thành công: accountNumber={}, amount={}, balance={}",
                    getAccountNumber(), amount, finalBalance);
        } catch (bank_system.BankException exception) {
            LOGGER.warn("Rút tiền thất bại: accountNumber={}, amount={}, reason={}",
                    getAccountNumber(), amount, exception.getMessage());
        }
    }
}
