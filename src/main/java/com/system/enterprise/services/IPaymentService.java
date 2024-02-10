package com.system.enterprise.services;

public interface IPaymentService {

    Double paymentFee(Double value);
    Double interest(Double value, Integer months);

}
