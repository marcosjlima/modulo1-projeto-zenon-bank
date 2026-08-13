package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCustomer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
    public  TransactionCustomer{
        Objects.requireNonNull(name);
        Objects.requireNonNull(oldBalance);
        Objects.requireNonNull(newBalance);

        if(name.isBlank()) {
            throw new IllegalArgumentException("name should not be empty");
        }
        if(newBalance.signum() < 0) {
            throw new NumberFormatException("O valor de newBalance deve ser maior igual a 0 (zero)! Valor atual: " + newBalance);
        }
        if(oldBalance.signum() < 0) {
            throw new NumberFormatException("O valor de oldBalance deve ser maior igual a 0 (zero)! Valor atual: " + oldBalance);
        }
    }
}
