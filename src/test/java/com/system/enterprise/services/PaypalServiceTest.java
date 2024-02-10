package com.system.enterprise.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaypalServiceTest {

    @Test
    @DisplayName("should return value parcel with simple interest")
    public void valueParcelsInterest() {
        IPaymentService paypalService = new PaypalService();

        Assertions.assertEquals(202, paypalService.interest(200.00, 1));
        Assertions.assertEquals(204, paypalService.interest(200.00, 2));
        Assertions.assertEquals(206, paypalService.interest(200.00, 3));
    }

    @Test
    @DisplayName("should return value with tax payment")
    public void valueRatePayment() {
        IPaymentService paymentService = new PaypalService();

        Double firstParcel = paymentService.interest(200.00, 1);
        Double secondParcel = paymentService.interest(200.00, 2);
        Double thirdParcel = paymentService.interest(200.00, 3);

        Assertions.assertEquals(206.04, paymentService.paymentFee(firstParcel));
        Assertions.assertEquals(208.08, paymentService.paymentFee(secondParcel));
        Assertions.assertEquals(210.12, paymentService.paymentFee(thirdParcel));
    }

}