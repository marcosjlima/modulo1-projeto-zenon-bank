package br.com.zenon.fraud.repository;

import br.com.zenon.fraud.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class TransactionListRepository implements ITransactionRepository {
    List<Transaction> transactions = new ArrayList<>();

    public TransactionListRepository(List<Transaction> transactions) {
        transactions = transactions;
    }

    @Override
    public Optional<Transaction> findByOriginName(String originName) {
        return transactions.stream()
                .filter(t->t.origin().name().equalsIgnoreCase(originName))
                .findFirst();
    }
}
