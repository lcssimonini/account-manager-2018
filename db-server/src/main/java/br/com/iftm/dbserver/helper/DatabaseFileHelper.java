package br.com.iftm.dbserver.helper;

import br.com.iftm.dbserver.model.Account;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.Comparator.comparing;

@Slf4j
public class DatabaseFileHelper {

    private final File databaseFile;
    private Gson gson = new Gson();

    public DatabaseFileHelper(File databaseFile) {
        this.databaseFile = databaseFile;
    }

    public Account getAccount(Integer id) {
        Account account = null;
        try {
            account = getAccountStream()
                    .filter(a -> id.equals(a.getId()))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void writeAccount(Account account) {
        try {
            synchronized (databaseFile) {
                Path path = get(databaseFile.getPath());
                Stream <String> lines = Files.lines(path);
                List<String> replaced = lines
                        .map(this::fromString)
                        .map(oldAccount -> getUpdatedBalanceAccount(account, oldAccount))
                        .map(this::toJson)
                        .collect(Collectors.toList());

                Files.write(path, replaced);
                lines.close();
            }
        } catch (Exception ignored){}
    }

    private Account getUpdatedBalanceAccount(Account account, Account oldAccount) {
        if (oldAccount.getId().equals(account.getId())) {
            oldAccount.setBalance(account.getBalance());
        }
        return oldAccount;
    }

    private void writeAccounts(List<Account> accounts) throws IOException {
        Files.write(get(databaseFile.getPath()), accounts.stream()
                .map(this::toJson)
                .collect(Collectors.toList()));
    }

    private Stream<Account> getAccountStream() throws IOException {
        synchronized (databaseFile){
            return readAllLines(get(databaseFile.getPath())).stream()
                    .map(this::fromString);
        }
    }

    private Account fromString(String account) {
        return gson.fromJson(account, Account.class);
    }

    private String toJson(Account account) {
        return gson.toJson(account);
    }

    public void initializeRandomAccounts() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            accounts.add(Account.builder().id(i).balance(getRandom()).build());
        }
        try {
            writeAccounts(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Double getRandom() {
        double leftLimit = 1D;
        double rightLimit = 10000D;
        return new RandomDataGenerator().nextUniform(leftLimit, rightLimit);
    }
}
