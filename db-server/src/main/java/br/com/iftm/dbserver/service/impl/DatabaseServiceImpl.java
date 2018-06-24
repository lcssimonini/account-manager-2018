package br.com.iftm.dbserver.service.impl;

import br.com.iftm.dbserver.helper.DatabaseFileHelper;
import br.com.iftm.dbserver.model.Account;
import br.com.iftm.dbserver.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {

    private static final String FILE_PATH = "database";
    private Path path = Paths.get(FILE_PATH);
    private DatabaseFileHelper helper;

    @PostConstruct
    public void prepareDatabase() {
        File databaseFile = new File(FILE_PATH);
        boolean isCreated = false;
        try {
            isCreated = databaseFile.createNewFile();
        } catch (IOException ignored) {}
        if (isCreated) {
            helper = new DatabaseFileHelper(path);
            helper.initializeRandomAccounts();
            log.debug("database created successfully");
        }
    }

    @Override
    public Account deposit(Integer id, Long ammount) {
        return null;
    }

    @Override
    public Account withdraw(Integer id, Long ammount) {
        return null;
    }

    @Override
    public Account balance(Integer id) {
        Account account = null;
        try {
            account = helper.getAccount(id);
        } catch (IOException e) {
            log.error("account does not exist", e);
        }
        return account;
    }
 }
