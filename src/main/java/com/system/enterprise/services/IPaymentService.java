package com.system.enterprise.services;

public interface IPaymentService {

    Double paymentFee(Double value);
    Double interestMonthly(Double value, Integer months);

}
