// Gabriel Souza Michaliszyn | RA: 1112024101187
// Fundamentos da Programação Orientada a Objetos (11100010550_20241_21)

package main;

import model.Mortgage;
import model.House;
import model.Apartment;
import model.Land;
import util.UserInterface;
import java.util.ArrayList;

public class Main {

    final static int houses = 2;
    final static int apartments = 2;
    final static int lands = 1;

    private static UserInterface user = new UserInterface();
    private static ArrayList<Mortgage> mortgages = new ArrayList<Mortgage>();

    public static void main(String[] args) {
        System.out.println("CADASTRO FINANCIAMENTOS");
        System.out.print("##############################");
        System.out.print("\n\n");
        System.out.println("Financiamento:");
        System.out.println("==============================");

        var value = user.getUserRealEstateValue();
        var rate = user.getUserAnnualPercentageRate();
        var term = user.getUserLoanTerm();

        user.closeScanner();

        for (var i = 0; i < houses; i++) {
            if (i != 0) {
                mortgages.add(new House(
                        getRandomNumber() * 1000000,
                        getRandomNumber() * 100,
                        (int)(getRandomNumber() * 50))
                );
            } else {
                mortgages.add(new House(value, rate, term));
            }
        }

        for (var i = 0; i < apartments; i++) {
            mortgages.add(new Apartment(
                    getRandomNumber() * 1000000,
                    getRandomNumber() * 100,
                    (int)(getRandomNumber() * 50))
            );
        }

        for (var i = 0; i < lands; i++) {
            mortgages.add(new Land(
                    getRandomNumber() * 1000000,
                    getRandomNumber() * 100,
                    (int)(getRandomNumber() * 50))
            );
        }

        System.out.print("\n\n\n");
        System.out.println("RESULTADO FINANCIAMENTOS");
        System.out.print("##############################");

        for (var i = 0; i < mortgages.size(); i++) {
            System.out.print("\n\n");
            System.out.printf("Financiamento #%d:\n", i + 1);
            System.out.print("==============================");
            System.out.print("\n");

            Mortgage mortgage = mortgages.get(i);
            mortgage.updateTotalMortgageValues();
            mortgage.printMortgageInfo();
        }

        Mortgage.printTotalMortgageValues(mortgages.size());
    }

    private static float getRandomNumber() {
        return (float) ((int)(Math.random() * 1000) + 1) / 1000;
    }
}