package com.system.enterprise.services;

import com.system.enterprise.entities.Contract;
import com.system.enterprise.entities.Installment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ContractServiceTest {

    @Test
    @DisplayName("should return three Installments")
    public void processInstallments() {
        ContractService contractService = new ContractService();
        Contract contract = new Contract(1L, LocalDate.now(), 600.00);
        Integer numberOfInstallment = 3;

        contractService.processContract(contract, numberOfInstallment);

        Assertions.assertEquals(206.04, contract.getInstallments().get(0).getValue());
        Assertions.assertEquals(208.08, contract.getInstallments().get(1).getValue());
        Assertions.assertEquals(210.12, contract.getInstallments().get(2).getValue());
        Assertions.assertEquals(LocalDate.parse("2024-03-10"), contract.getInstallments().get(0).getDueDate());
        Assertions.assertEquals(LocalDate.parse("2024-04-10"), contract.getInstallments().get(1).getDueDate());
        Assertions.assertEquals(LocalDate.parse("2024-05-10"), contract.getInstallments().get(2).getDueDate());
    }

    @Test
    @DisplayName("should return value total the all installments")
    public void getValueInstallments() {
        ContractService contractService = new ContractService();
        Contract contract = new Contract(1L, LocalDate.now(), 600.00);
        Integer numberOfInstallment = 3;

        contractService.processContract(contract, numberOfInstallment);

        Assertions.assertEquals(624.24, contractService.getValueTotal(contract));
    }

}
