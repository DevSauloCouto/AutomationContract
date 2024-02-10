package com.system.enterprise.entities;

import java.time.LocalDate;

public class Installment {

    private LocalDate dueDate;
    private Double value;

    public Installment(LocalDate dueDate, Double value) {
        this.dueDate = dueDate;
        this.value = value;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
