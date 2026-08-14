package br.com.zenon.fraud.repository;

import br.com.zenon.fraud.Transaction;

import java.util.Optional;

public interface ITransactionRepository {
    Optional<Transaction> findByOriginName(String originName);
}
