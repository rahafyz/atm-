package Application;

import Exeptions.AgeException;
import Exeptions.InvalidInputException;
import database.OldDatabase;
import model.Account;
import model.User;
import util.MyMethod;

import java.time.LocalDate;
import java.util.Optional;

public class UserService {

    private OldDatabase database = OldDatabase.getInstance();

    public User createUser(String fName, String lName, String nationalCode, LocalDate bDay) {
        if (this.findAccountByNationalCode(nationalCode).isPresent())
            throw new InvalidInputException("User already exist.");

        //age validation
        if (bDay.getYear() < 18)
            throw new AgeException();

        User user = new User(fName, lName, nationalCode, bDay);
        MyMethod.print(user);
        return user;
    }

    public Optional<User> findAccountByNationalCode(String nationalCode) {
        for (Account account : database.getAccountList()) {
            if (account.getUser().getNationalCode().equals(nationalCode))
                return Optional.of(account.getUser());
        }
        return Optional.empty();
    }
}
