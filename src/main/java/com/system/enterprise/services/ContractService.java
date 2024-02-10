package com.system.enterprise.services;

import com.system.enterprise.entities.Contract;
import com.system.enterprise.entities.Installment;

public class ContractService {

    private IPaymentService paymentService;

    public ContractService(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, Integer numberOfInstallment) {
        if (numberOfInstallment < 1) {
            throw new RuntimeException("Minimum one installment");
        }

        if (numberOfInstallment > 48) {
            throw new RuntimeException("Maximum 48 installments");
        }

        Double valueParcel = contract.getValue() / numberOfInstallment;

        if (numberOfInstallment == 1) {
            Double payment = paymentService.paymentFee(valueParcel);

            contract.addInstallment(new Installment(contract.getDate().plusMonths(1), payment));
        } else {
            for (int i = 0; i < numberOfInstallment; i++) {
                Double interest = paymentService.interestMonthly(valueParcel, i + 1);

                contract.addInstallment(new Installment(contract.getDate().plusMonths(i + 1), paymentService.paymentFee(interest)));
            }

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
