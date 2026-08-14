package br.com.zenon.fraud.repository;

import br.com.zenon.fraud.Transaction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository  implements ITransactionRepository {
    private final Map<String, Transaction> transactionsOriginByName;

    public TransactionMapRepository(List<Transaction> transactions) {
        transactionsOriginByName = transactions
                .stream()
                .collect(
                        Collectors.toMap(t -> t.origin().name(), Function.identity())
                );
    }

    @Override
    public Optional<Transaction> findByOriginName(String originName) {
        return Optional.ofNullable(transactionsOriginByName.get(originName));
    }
}
