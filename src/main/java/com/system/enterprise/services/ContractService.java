package com.system.enterprise.services;

import com.system.enterprise.entities.Contract;
import com.system.enterprise.entities.Installment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ContractService {

    public void processContract(Contract contract, Integer numberOfInstallment) {
        IPaymentService paypalService = new PaypalService();

        Double valueParcel = contract.getValue() / numberOfInstallment;

        for (int i = 0; i < numberOfInstallment; i++) {
            Double interest = paypalService.interest(valueParcel, i + 1);

            contract.addInstallment(new Installment(contract.getDate().plusMonths(i + 1), paypalService.paymentFee(interest)));
        }
    }

    public Double getValueTotal(Contract contract) {
        Double total = 0.0;
        for (Installment installment : contract.getInstallments()) {
            total += installment.getValue();
        }
        return total;
    }

}
