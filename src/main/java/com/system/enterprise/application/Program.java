package com.system.enterprise.application;

import com.system.enterprise.entities.Contract;
import com.system.enterprise.services.ContractService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entre com os dados do contrato: ");

        System.out.print("Número: ");
        Long number = scanner.nextLong();

        System.out.print("Data (dd/mm/yyyy): ");
        LocalDate date = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Valor do contrato: ");
        Double value = scanner.nextDouble();

        System.out.print("Número de parcelas: ");
        Integer parcels = scanner.nextInt();

        Contract contract = new Contract(number, date, value);

        ContractService contractService = new ContractService();

        System.out.println("-----------------------------");
//        System.out.println("PARCELAS:");
//        for (String parcel : contractService.processContract(contract)) {
//            System.out.println(parcel);
//        }
    }

}
