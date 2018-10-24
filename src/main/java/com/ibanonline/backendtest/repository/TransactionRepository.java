package com.ibanonline.backendtest.repository;

import com.ibanonline.backendtest.api.dtos.GetTransactionsRequestDto;
import com.ibanonline.backendtest.api.dtos.Operation;
import com.ibanonline.backendtest.config.DatabaseConfig;
import com.ibanonline.backendtest.domain.Transaction;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    public List<Transaction> getTransactions(String userId, GetTransactionsRequestDto requestDto) {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        Result<Record> result = create.select()
                .from(TABLE)
                .where("userId = " + userId)
                .and(translate(requestDto))
                .fetch();

        return result.into(Transaction.class);
    }

    private String translate(GetTransactionsRequestDto requestDto) {
        Operation operation = requestDto.getOperation();
        String operationFormat = "amount %s " + requestDto.getAmount();
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
                return "amount BETWEEN " + requestDto.getAmountFrom() + " and " + requestDto.getAmountTo();
        }

        return null;
    }
}
