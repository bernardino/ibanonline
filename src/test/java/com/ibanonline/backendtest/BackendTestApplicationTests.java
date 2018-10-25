package com.ibanonline.backendtest;

import com.ibanonline.backendtest.api.dtos.GetTransactionsRequestDto;
import com.ibanonline.backendtest.api.dtos.GetTransactionsResponseDto;
import com.ibanonline.backendtest.api.dtos.Operation;
import com.ibanonline.backendtest.api.dtos.UnitDto;
import com.ibanonline.backendtest.domain.Transaction;
import com.ibanonline.backendtest.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BackendTestApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	public void testGetTransactions() throws Exception {
		String url = "http://localhost:" + port + "/users/1/transactions";
		final GetTransactionsRequestDto request = GetTransactionsRequestDto.builder()
				.amount(new BigDecimal(100))
				.duration(1)
				.unit(UnitDto.DAYS)
				.operation(Operation.GT)
				.build();

		final ResponseEntity<GetTransactionsResponseDto> response = this.restTemplate
				.postForEntity(url, request, GetTransactionsResponseDto.class);

		assertThat(response).isNotNull();

		final List<Transaction> transactions = transactionRepository.getTransactions("1", request.convert());
		assertThat(transactions).hasSize(4);
	}

	@Test
	public void testGetTransactionsFromTo() throws Exception {
		String url = "http://localhost:" + port + "/users/1/transactions";
		final GetTransactionsRequestDto request = GetTransactionsRequestDto.builder()
				.duration(1)
				.unit(UnitDto.DAYS)
				.operation(Operation.RANGE)
				.amountFrom(new BigDecimal(100))
				.amountTo(new BigDecimal(400))
				.build();

		final ResponseEntity<GetTransactionsResponseDto> response = this.restTemplate
				.postForEntity(url, request, GetTransactionsResponseDto.class);

		assertThat(response).isNotNull();

		final List<Transaction> transactions = transactionRepository.getTransactions("1", request.convert());
		assertThat(transactions).hasSize(2);
	}

}
