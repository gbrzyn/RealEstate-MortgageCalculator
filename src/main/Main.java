// Gabriel Souza Michaliszyn | RA: 1112024101187
// Fundamentos da Programação Orientada a Objetos (11100010550_20241_21)

package main;

import model.Mortgage;

import util.UserInterface;
import java.util.ArrayList;

//TODO - Formatar texto das mensagens
//TODO - Adicionar opção de no menu -> Visualizar resultados
//TODO - Adicionar opção no menu -> Cadastrar dados aleatórios

public class Main {

    private final static int mortgageSimulations = 1;
    private static final UserInterface user = new UserInterface();
    private static final ArrayList<Mortgage> mortgages = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("CADASTRO FINANCIAMENTOS");
        System.out.print("##############################");
        System.out.print("\n\n");

        for (var i = 0; i < mortgageSimulations; i++) {
            try{
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
                        throw new Exception("Tipo de financiamento inválido!");
                }

            } catch (Exception e){
                System.out.println(e.getMessage());
                i--;
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