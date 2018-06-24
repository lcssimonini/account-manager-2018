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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;

@Slf4j
public class DatabaseFileHelper {

    private Path path;
    private Gson gson = new Gson();

    public DatabaseFileHelper(Path path) {
        this.path = path;
    }

    public Account getAccount(Integer id) throws IOException {
        return getAccountStream()
                .filter(a -> id.equals(a.getId()))
                .collect(Collectors.toList()).get(0);
    }

    public void writeAccount(Account account) throws IOException {
        List<Account> accounts = getAccountStream()
                .collect(Collectors.toList());

        accounts.removeIf(a -> a.getId().equals(account.getId()));
        accounts.add(account);

        clearFile();
        writeAccounts(accounts);
    }

    public void writeAccounts(List<Account> accounts) throws IOException {
        Files.write(path, accounts.stream()
                .map(this::toJson)
                .collect(Collectors.toList()));
    }

    public Stream<Account> getAccountStream() throws IOException {
        return readAllLines(path).stream()
                .map(this::fromString);
    }

    public Account fromString(String account) {
        return gson.fromJson(account, Account.class);
    }

    public String toJson(Account account) {
        return gson.toJson(account);
    }

    public void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(String.valueOf(path)));
        writer.print("");
        writer.close();
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
