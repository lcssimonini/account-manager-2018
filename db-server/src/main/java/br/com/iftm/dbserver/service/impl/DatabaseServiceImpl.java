package br.com.iftm.dbserver.service.impl;

import br.com.iftm.dbserver.helper.DatabaseFileHelper;
import br.com.iftm.dbserver.model.Account;
import br.com.iftm.dbserver.service.DatabaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    private static final Logger log = Logger.getLogger(DatabaseServiceImpl.class);
    private static final String FILE_PATH = "database" + new Date().getTime();
    private Path path = Paths.get(FILE_PATH);
    private DatabaseFileHelper helper;
    private File databaseFile;

    @PostConstruct
    public void prepareDatabase() {
        databaseFile = new File(FILE_PATH);
        boolean isCreated = false;
        try {
            isCreated = databaseFile.createNewFile();
            helper = new DatabaseFileHelper(databaseFile);
        } catch (IOException ignored) {}
        if (isCreated) {
            helper.initializeRandomAccounts();
            log.debug("database created successfully");
        }
    }

    @Override
    public Account deposit(Integer id, Double ammount) {
        Account account = helper.getAccount(id);
        Account updatedAccount = Optional.ofNullable(account)
                .map(a -> a.deposit(ammount))
                .orElse(account);

        helper.writeAccount(updatedAccount);
        return updatedAccount;
    }

    @Override
    public Account withdraw(Integer id, Double ammount) {
        Account account = helper.getAccount(id);
        Account updatedAccount = Optional.ofNullable(account)
                .map(a -> a.withdraw(ammount))
                .orElse(account);

        helper.writeAccount(updatedAccount);
        return updatedAccount;
    }

    @Override
    public Account balance(Integer id) {
        return helper.getAccount(id);
    }
}
