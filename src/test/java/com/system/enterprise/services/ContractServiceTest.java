package com.system.enterprise.services;

import com.system.enterprise.entities.Contract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ContractServiceTest {

    @Test
    @DisplayName("should return three Installments")
    public void processInstallments() {
        ContractService contractService = new ContractService(new PaypalService());
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
    public void getValueTotalInstallments() {
        ContractService contractService = new ContractService(new PaypalService());
        Contract contract = new Contract(1L, LocalDate.now(), 600.00);
        Integer numberOfInstallment = 3;

        contractService.processContract(contract, numberOfInstallment);

        Assertions.assertEquals(624.24, contractService.getValueTotal(contract));
    }

    @Test
    @DisplayName("should return a installment only with payment fee")
    public void oneInstallmentContract() {
        ContractService contractService = new ContractService(new PaypalService());
        Contract contract = new Contract(1L, LocalDate.now(), 200.00);
        Integer uniqueInstallment = 1;

        contractService.processContract(contract, uniqueInstallment);

        Assertions.assertEquals(204.0, contract.getInstallments().get(0).getValue());
    }

    @Test
    @DisplayName("should throw exception if the number of installment < 1")
    public void errorMinimumInstallment() {
        ContractService contractService = new ContractService(new PaypalService());
        Contract contract = new Contract(1L, LocalDate.now(), 200.00);
        Integer noInstallment = 0;

        String messageError = "Minimum one installment";

        Assertions.assertThrows(RuntimeException.class, () -> {
            contractService.processContract(contract, noInstallment);
        });

        String message = Assertions.assertThrows(RuntimeException.class, () -> {
           contractService.processContract(contract, noInstallment);
        }).getMessage();

        Assertions.assertEquals(messageError, message);
    }

    @Test
    @DisplayName("should throw exception if the number of installment > 48")
    public void errorMaximumInstallments() {
        ContractService contractService = new ContractService(new PaypalService());
        Contract contract = new Contract(1L, LocalDate.now(), 200.00);
        Integer limitInstallments = 49;

        String messageError = "Maximum 48 installments";

        Assertions.assertThrows(RuntimeException.class, () -> {
            contractService.processContract(contract, limitInstallments);
        });

        String message = Assertions.assertThrows(RuntimeException.class, () -> {
            contractService.processContract(contract, limitInstallments);
        }).getMessage();

        Assertions.assertEquals(messageError, message);
    }

}
