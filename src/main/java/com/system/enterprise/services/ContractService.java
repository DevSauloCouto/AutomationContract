package com.system.enterprise.services;

import com.system.enterprise.entities.Contract;
import com.system.enterprise.entities.Installment;

public class ContractService {

    public void processContract(Contract contract, Integer numberOfInstallment) {
        IPaymentService paypalService = new PaypalService();

        Double valueParcel = contract.getValue() / numberOfInstallment;

        if (numberOfInstallment == 1) {
            Double payment = paypalService.paymentFee(valueParcel);

            contract.addInstallment(new Installment(contract.getDate().plusMonths(1), payment));
        } else {
            for (int i = 0; i < numberOfInstallment; i++) {
                Double interest = paypalService.interestMonthly(valueParcel, i + 1);

                contract.addInstallment(new Installment(contract.getDate().plusMonths(i + 1), paypalService.paymentFee(interest)));
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
