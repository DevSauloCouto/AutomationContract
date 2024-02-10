package com.system.enterprise.services;

public class PaypalService implements IPaymentService {

    @Override
    public Double paymentFee(Double value) {
        return value + value * 0.02;
    }

    @Override
    public Double interestMonthly(Double value, Integer months) {
        return value + (value * 0.01) * months;
    }

}
