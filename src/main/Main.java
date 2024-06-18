package main;

import model.Mortgage;
import util.UserInterface;

import java.util.ArrayList;

public class Main {

    final static int mortgagesNumber = 4;
    static UserInterface user = new UserInterface();
    static ArrayList<Mortgage> mortgages = new ArrayList<Mortgage>();

    public static void main(String[] args) {
        double totalRealEstatesValue = 0;
        double totalMortgagesValue = 0;

        System.out.println("CADASTRO FINANCIAMENTOS");
        System.out.print("##############################");

        for (int i = 0; i < mortgagesNumber; i++){

            System.out.print("\n\n");
            System.out.printf("Financiamento #%d:\n", i+1);
            System.out.println("==============================");

            double value = user.getUserRealEstateValue();
            double rate = user.getUserAnnualPercentageRate();
            int term = user.getUserLoanTerm();

            mortgages.add(new Mortgage(value, rate, term));
        }
        user.closeScanner();

        System.out.print("\n\n\n");
        System.out.println("RESULTADO FINANCIAMENTOS");
        System.out.print("##############################");

        for (int i = 0; i < mortgagesNumber; i++) {
            System.out.print("\n\n");
            System.out.printf("Financiamento #%d:\n", i+1);
            System.out.print("==============================");

            Mortgage mortgage = mortgages.get(i);
            mortgage.printInfo();

            totalRealEstatesValue += mortgage.getRealEstateValue();
            totalMortgagesValue += mortgage.getTotalPayment();
        }

        System.out.print("\n\n\n");
        System.out.println("RESULTADO FINAL");
        System.out.print("##############################");
        System.out.println("\n");
        System.out.printf("Número de financiamentos: %d\n", mortgagesNumber);
        System.out.printf("Valor total Imóveis: R$%.2f\n", totalRealEstatesValue);
        System.out.printf("Valor total Financiado: R$%.2f\n", totalMortgagesValue);
    }
}