package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(int step, TransactionType type, BigDecimal amount, TransactionCustomer origin, TransactionCustomer recipient, boolean isFraud, boolean isFlaggedFraud) {
    public  Transaction{
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        if(step <= 0) {
            throw new IllegalArgumentException("O valor de Step deve ser maior que 0 (zero)! Valor informado: " + step);
        }
        if(amount.signum() < 0) {
            throw new NumberFormatException("O valor de Amount deve ser maior igual a 0 (zero)!");
        }
    }
}