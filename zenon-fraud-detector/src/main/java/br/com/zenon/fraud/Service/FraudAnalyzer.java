package br.com.zenon.fraud.Service;

import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FraudAnalyzer {
    List<Transaction> transactions = new ArrayList<>();

    public FraudAnalyzer(List<Transaction> transactions) {
        transactions = transactions;
    }

    public long countTotalFraud() {
        return getTransactionStream()
                .count();
    }

    public List<Transaction> findTopHighestValueFrauds(int limit) {
        return getSorted()
                .limit(limit)
                .toList();
    }

    public List<String> findTopSuspiciousClients(int limit) {
        return getSorted()
                .map(t -> t.origin().name())
                .distinct()
                .limit(limit)
                .toList();
    }

    public BigDecimal calculateTotalFraudLoss() {
        return getTransactionStream()
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> countTotalFraudByType() {
        return getTransactionStream()
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }

    private Stream<Transaction> getTransactionStream() {
        return transactions.stream()
                .filter(Transaction::isFraud);
    }

    private Stream<Transaction> getSorted() {
        return getTransactionStream()
                .sorted(Comparator.comparing(Transaction::amount).reversed());
    }
}
