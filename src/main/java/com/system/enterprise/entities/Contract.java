package com.system.enterprise.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {

    private Long numberContract;
    private LocalDate date;
    private Double value;
    private List<Installment> installments = new ArrayList<>();

    public Contract() {}

    public Contract(Long numberContract, LocalDate date, Double value) {
        this.numberContract = numberContract;
        this.date = date;
        this.value = value;
    }

    public Long getNumberContract() {
        return numberContract;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void addInstallment(Installment installment) {
        this.installments.add(installment);
    }
}
