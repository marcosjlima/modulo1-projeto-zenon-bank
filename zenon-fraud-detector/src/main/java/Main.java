import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionCustomer;
import br.com.zenon.fraud.TransactionType;

import java.math.BigDecimal;

public class Main {
    void main() {
        Transaction t1 = new Transaction(1, TransactionType.CASH_IN, new BigDecimal("98.364"), new TransactionCustomer("Fulano", new BigDecimal("0.01"), new BigDecimal("0.01")), new TransactionCustomer("Sicrano", new BigDecimal("0.01"), new BigDecimal("0.01")), false, false);
        Transaction t2 = new Transaction(1, TransactionType.CASH_IN, new BigDecimal("98.322"), new TransactionCustomer("Fulano", new BigDecimal("0.01"), new BigDecimal("0.01")), new TransactionCustomer("Sicrano", new BigDecimal("0.01"), new BigDecimal("0.01")), false, false);

        IO.println(t1);
        IO.println(t2);

    }
}
