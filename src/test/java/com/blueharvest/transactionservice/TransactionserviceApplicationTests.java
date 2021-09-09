package com.blueharvest.transactionservice;

import com.blueharvest.transactionservice.model.BaseResponse;
import com.blueharvest.transactionservice.model.CreateTransaction;
import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.repository.TransactionRepository;
import com.blueharvest.transactionservice.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionserviceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionService transactionService;

	Transaction transaction = new Transaction();


	@AfterEach
	public void cleanUpTestCreatedTransaction() {
		Transaction transaction = transactionRepository.findTopByOrderByIdDesc().orElse(null);
		if(transaction != null) {
			transactionRepository.deleteById(transaction.getId());
		}

	}

	@Test
	@Order(1)
	void createTransaction_throughTheController_returns200OK() throws Exception {

		CreateTransaction request = new CreateTransaction(1, 300.00);
		mockMvc.perform(post("/api/v1/transactions/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void createTransaction_withValidParameters_returnsSuccessResponse() {
		CreateTransaction request = new CreateTransaction(1, 2000.00);
		BaseResponse res = transactionService.CreateTransaction(request);

		assertNotNull(res);
		assertNotNull(res.getResponseCode());
		assertNotNull(res.getResponseMessage());
	}

	@Test
	@Order(3)
	void fetchTransaction_withValidRequest_returns200OK() throws Exception {
		saveTransaction();
		mockMvc.perform(get("/api/v1/transactions/fetch/140")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	void fetchTransaction_withInvalidRequest_returns400BadRequest() throws Exception {
		saveTransaction();
		mockMvc.perform(get("/api/v1/transactions/fetch/string")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(5)
	void fetchTransaction_withCorrectAccountId_returnsBaseResponse() throws Exception {
		saveTransaction();
		BaseResponse<List<Transaction>> res = transactionService.fetchTransactions(140);

		assertNotNull(res);
		assertNotNull(res.getResponseCode());
		assertNotNull(res.getResponseMessage());
		//assertNotNull(res.get(0).getData());
	}

	private void saveTransaction() {
		Transaction transaction = new Transaction();
		transaction.setAmount(20000.00);
		transaction.setAccountId(140);
		transaction.setTransactionDate(new Date());
		transactionRepository.save(transaction);
	}

}
