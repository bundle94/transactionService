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
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	private long lastTransactionId;

	@BeforeEach
	public void getLastId() {
		Transaction transaction = transactionRepository.findTopByOrderByIdDesc().orElse(null);
		if(transaction != null)
			lastTransactionId = transaction.getId();
	}

	@AfterEach
	public void cleanUpTestCreatedAccount() {
		Transaction transaction = transactionRepository.findTopByOrderByIdDesc().orElse(null);
		if(transaction != null) {
			if(lastTransactionId != transaction.getId())
				transactionRepository.deleteById(transaction.getId());
		}
	}

	@Test
	@Order(1)
	void createTransaction_withValidParameters_returns201created() throws Exception {

		CreateTransaction request = new CreateTransaction(1, 300.00);
		mockMvc.perform(post("/api/v1/transactions/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void createAccount_withValidParameters_returnsSuccessResponse() throws Exception {
		CreateTransaction request = new CreateTransaction(1, 2000.00);
		BaseResponse res = transactionService.CreateTransaction(request);

		assertNotNull(res);
		assertNotNull(res.getResponseCode());
		assertNotNull(res.getResponseMessage());
	}

}
