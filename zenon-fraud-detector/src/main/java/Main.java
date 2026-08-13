import br.com.zenon.fraud.Service.TransactionIngestor;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionCustomer;
import br.com.zenon.fraud.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    void main() {
        Transaction t1 = new Transaction(1, TransactionType.CASH_IN, new BigDecimal("98.364"), new TransactionCustomer("Fulano", new BigDecimal("0.01"), new BigDecimal("0.01")), new TransactionCustomer("Sicrano", new BigDecimal("0.01"), new BigDecimal("0.01")), false, false);
        Transaction t2 = new Transaction(1, TransactionType.CASH_IN, new BigDecimal("98.322"), new TransactionCustomer("Fulano", new BigDecimal("0.01"), new BigDecimal("0.01")), new TransactionCustomer("Sicrano", new BigDecimal("0.01"), new BigDecimal("0.01")), false, false);

        IO.println(t1);
        IO.println(t2);

        TransactionIngestor service = new TransactionIngestor();
        List<Transaction> transactions2 = service.read("data/PS_20174392719_1491204439457_log.csv");
        transactions2.stream().limit(10).forEach(IO::println);
    }
}
