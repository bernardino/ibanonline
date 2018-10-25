package com.ibanonline.backendtest.repository;

import com.google.common.annotations.VisibleForTesting;
import com.ibanonline.backendtest.api.dtos.Operation;
import com.ibanonline.backendtest.config.DatabaseConfig;
import com.ibanonline.backendtest.domain.Transaction;
import com.ibanonline.backendtest.domain.TransactionsRequest;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Ricardo Bernardino
 */
@Repository
public class TransactionRepository {
    private static String TABLE = "transactions";

    private DatabaseConfig databaseConfig;
    private Connection connection;

    @Autowired
    public TransactionRepository(DatabaseConfig databaseConfig) throws SQLException {
        this.databaseConfig = databaseConfig;

        connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUser(), databaseConfig.getPass());
    }

    /**
     * Retrieves a list of transactions from the database for the given user.
     *
     * @param userId The user ID.
     * @param request The filters to filter the transaction by.
     * @return A list of transactions for the given user and filters.
     */
    public List<Transaction> getTransactions(String userId, TransactionsRequest request) {
        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

        Result<Record> result = context.select()
                .from(TABLE)
                .where("userId = " + userId)
                .and(translateAmountOperation(request))
                .and(translateTimeOperation(request))
                .fetch();

        return result.into(Transaction.class);
    }

    /**
     * Truncates the table
     */
    @VisibleForTesting
    public void truncate() {
        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
        context.truncate(TABLE);
    }

    private String translateAmountOperation(TransactionsRequest request) {
        Operation operation = request.getOperation();
        String operationFormat = "amount %s " + request.getAmount();
        switch (operation) {
            case EQ:
                return String.format(operationFormat, "=");
            case GT:
                return String.format(operationFormat, ">");
            case LT:
                return String.format(operationFormat, "<");
            case GTE:
                return String.format(operationFormat, ">=");
            case LTE:
                return String.format(operationFormat, "<=");
            case RANGE:
                return "amount BETWEEN " + request.getAmountFrom() + " and " + request.getAmountTo();
        }

        throw new RuntimeException("Invalid operation provided");
    }

    private String translateTimeOperation(TransactionsRequest request) {
        String durationFormat = "timestamp >= '%s'";
        final LocalDateTime now = LocalDateTime.now();
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        switch (request.getUnit()) {
            case DAYS:
                return String.format(durationFormat, now.minusDays(request.getDuration()).format(dateTimeFormatter));
            case WEEKS:
                return String.format(durationFormat, now.minusWeeks(request.getDuration()).format(dateTimeFormatter));
            case MONTHS:
                return String.format(durationFormat, now.minusMonths(request.getDuration()).format(dateTimeFormatter));
        }

        throw new RuntimeException("Invalid unit provided");
    }
}
