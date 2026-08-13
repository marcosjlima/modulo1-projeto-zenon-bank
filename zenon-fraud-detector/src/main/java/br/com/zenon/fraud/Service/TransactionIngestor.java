package br.com.zenon.fraud.Service;

import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionCustomer;
import br.com.zenon.fraud.TransactionType;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.FileNameMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TransactionIngestor {
    public List<Transaction> read(String fileName) {
        try{
            Path path = Path.of(fileName);

            List<String> lines = Files.readAllLines(path);

            return lines.stream()
                    .skip(1)
                    .limit(1000)
                    .map(this::parseTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        } catch (Exception ex) {
            throw new RuntimeException("Falha ao ler o "+ fileName, ex);
        }
    }

    private Optional<Transaction> parseTransaction(String line) {
        String[] chunks = line.split(",");

        try{
            int type = Integer.parseInt(chunks[0]);
            TransactionType transactionTypeType = TransactionType.valueOf(chunks[1]);
            BigDecimal amount = new BigDecimal(chunks[2]);
            TransactionCustomer origin = new TransactionCustomer(chunks[3], new BigDecimal(chunks[4]), new BigDecimal(chunks[5]));
            TransactionCustomer recipient = new TransactionCustomer(chunks[6], new BigDecimal(chunks[7]), new BigDecimal(chunks[8]));
            boolean isFraud = "1".equals(chunks[9]);
            boolean isFlag = "1".equals(chunks[10]);

            return Optional.of(new Transaction(type, transactionTypeType, amount, origin,  recipient, isFraud, isFlag));
        }catch (Exception ex) {
            IO.println("Erro ao realizar o parse: "+ line +" | "+ ex.getMessage());
        }

        return Optional.empty();
    }
}
