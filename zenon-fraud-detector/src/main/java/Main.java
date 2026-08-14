import br.com.zenon.fraud.Service.FraudAnalyzer;
import br.com.zenon.fraud.Service.TransactionIngestor;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionCustomer;
import br.com.zenon.fraud.TransactionType;
import br.com.zenon.fraud.repository.ITransactionRepository;
import br.com.zenon.fraud.repository.TransactionListRepository;
import br.com.zenon.fraud.repository.TransactionMapRepository;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    void main() {
        Transaction t1 = new Transaction(1, TransactionType.CASH_IN, new BigDecimal("98.364"), new TransactionCustomer("Fulano", new BigDecimal("0.01"), new BigDecimal("0.01")), new TransactionCustomer("Sicrano", new BigDecimal("0.01"), new BigDecimal("0.01")), false, false);
        Transaction t2 = new Transaction(1, TransactionType.CASH_IN, new BigDecimal("98.322"), new TransactionCustomer("Fulano", new BigDecimal("0.01"), new BigDecimal("0.01")), new TransactionCustomer("Sicrano", new BigDecimal("0.01"), new BigDecimal("0.01")), false, false);

        IO.println(t1);
        IO.println(t2);

        TransactionIngestor service = new TransactionIngestor();
        List<Transaction> transactions = service.read("data/PS_20174392719_1491204439457_log.csv");
        transactions.stream().limit(10).forEach(IO::println);

        List<Transaction> transactions2 = service.read("data/paysim_with_bad_data.csv");
        transactions2.forEach(IO::println);

        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(transactions);
        var countTotal = fraudAnalyzer.countTotalFraud();
        IO.println(countTotal);

        var topHighestValueFrauds = fraudAnalyzer.findTopHighestValueFrauds(3);
        topHighestValueFrauds.stream().map(Transaction::amount).forEach(IO::println);

        var topSuspiciousClients = fraudAnalyzer.findTopSuspiciousClients(5);

        topSuspiciousClients.forEach(IO::println);

        var totalFraudLoss = fraudAnalyzer.calculateTotalFraudLoss();

        IO.println(totalFraudLoss);

        var totalFraudByType = fraudAnalyzer.countTotalFraudByType();

        IO.println(totalFraudByType);

        ITransactionRepository transactionListRepository;
        transactionListRepository = new TransactionListRepository(transactions);
        final String originName = "C12345";
        transactionListRepository.findByOriginName("C1231006815").ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente "+ originName));
        Long startTime = System.nanoTime();
        final String originName2 = "C1231006815";
        transactionListRepository.findByOriginName("C1231006815").ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente "+ originName2));
        Long endTime = System.nanoTime();

        IO.println("LIST - Tempo (ms) de busca: "+ (endTime - startTime) / 1_000_000.0);


        transactionListRepository = new TransactionMapRepository(transactions);
        final String originName3 = "C12345";
        transactionListRepository.findByOriginName("C1231006815").ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente "+ originName3));
        startTime = System.nanoTime();
        final String originName4 = "C1231006815";
        transactionListRepository.findByOriginName("C1231006815").ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente "+ originName4));
        endTime = System.nanoTime();

        IO.println("MAP - Tempo (ms) de busca: "+ (endTime - startTime) / 1_000_000.0);


    }
}
