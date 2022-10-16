package Application;


import Exeptions.InvalidAmountException;
import Exeptions.InvalidPasswordException;
import Exeptions.NotFoundException;
import database.OldDatabase;
import model.Account;
import model.AccountType;
import model.Transaction;
import model.User;

import java.util.*;
import java.util.stream.Collectors;

public class AccountService {
    private OldDatabase database = OldDatabase.getInstance();


    public Optional<Account> findAccount(String cardNumber){
        for (Account account : database.getAccountList()) {
            if (account.getCard().getCardNumber().equals(cardNumber))
                return Optional.of(account);
        }
        return Optional.empty();
    }

    public Account login(String cardNumber, String password) {
        if (this.findAccount(cardNumber).isPresent()) {
            Account account = this.findAccount(cardNumber).get();
            if (account.getCard().validation(password))
                return account;
            else
                throw new InvalidPasswordException();
        }else
            throw new NotFoundException("account not found");
    }

    public Account createAccount(User user, String password,Integer type) {

        AccountType accountType = AccountType.getAccountType(type);
        Account account = new Account(user, password, accountType);
        database.getAccountList().add(account);
        return account;
    }

    public List<Transaction> LastTenTransactions(String cardNumber) {
        List<Transaction> transactions = findAccount(cardNumber).get().getTransactions();
        if (transactions.isEmpty())
            throw new NotFoundException("There is no transaction");
        Collections.reverse(transactions);
        transactions.subList(0,transactions.size()<10?transactions.size():9);
        System.out.println();
        return transactions;
    }

    public void moneyTransfer(String cardNum1,String cardNum2,double amount){
        Account account1 = findAccount(cardNum1).get();
        if (!findAccount(cardNum2).isPresent())
            throw new NotFoundException("no account by this card number");
        Account account2 = findAccount(cardNum2).get();
        account1.withDraw(amount);
        account2.deposit(amount);
    }
}
