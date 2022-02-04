package com.kinandcarta.transactionsapi.service;

import com.kinandcarta.transactionsapi.domain.entity.Account;
import com.kinandcarta.transactionsapi.domain.response.AccountTransactionResponse;
import com.kinandcarta.transactionsapi.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionsServiceTest {

    private static final Account ACCOUNT_WITH_NO_TRANSACTIONS = 
            new Account(
            123L,
            "Bruce Wayne",
            emptySet()
    );
    private static final AccountTransactionResponse RESPONSE_WITH_NO_TRANSACTIONS = 
            new AccountTransactionResponse(
            123L,
            "Bruce Wayne",
            emptyList()
    );

    @Mock
    private AccountRepository mockAccountRepository;

    private TransactionsService transactionsService;

    @BeforeEach
    void setUp() {
        transactionsService = new TransactionsService(mockAccountRepository);
    }

    @Test
    void returnsAccountTransactionResponseWithNoTransactionsWhenThereAreNoTransactionsPresentForGivenAccount() {
        given(mockAccountRepository.findById(anyLong()))
                .willReturn(Optional.of(ACCOUNT_WITH_NO_TRANSACTIONS));

        AccountTransactionResponse actualResponse = transactionsService.getTransactions(123L);

        assertThat(actualResponse).isEqualTo(RESPONSE_WITH_NO_TRANSACTIONS);
    }
}