// Gabriel Souza Michaliszyn | RA: 1112024101187
// Fundamentos da Programação Orientada a Objetos (11100010550_20241_21)

package main;

import model.Mortgage;
import util.UserInterface;
import java.util.ArrayList;

public class Main {

    private final static int mortgageSimulations = 1;
    private static UserInterface user = new UserInterface();
    private static ArrayList<Mortgage> mortgages = new ArrayList<Mortgage>();

    public static void main(String[] args) {
        System.out.println("CADASTRO FINANCIAMENTOS");
        System.out.print("##############################");
        System.out.print("\n\n");

        for (var i = 0; i < mortgageSimulations; i++) {
            String type = user.getMortgageType();

            System.out.printf("Financiamento de %s\n", type);
            System.out.println("==============================");

            switch (type) {
                case "Casa":
                    mortgages.add(user.getUserNewHouse());
                    break;

                case "Apartamento":
                    mortgages.add(user.getUserNewApartment());
                    break;

                case "Terreno":
                    mortgages.add(user.getUserNewLand());
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    i--;
                    break;
            }
        }

        user.closeScanner();

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