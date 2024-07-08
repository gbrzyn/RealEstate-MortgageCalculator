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


    public static void main(String[] args) {
        var mortgages = getMortgages();

        if(mortgages != null) {
            var result = getResult(mortgages);
            System.out.println(result);
        }

    }

    private static ArrayList<Mortgage> getMortgages() {
        UserInterface user = new UserInterface();
        ArrayList<Mortgage> mortgages = new ArrayList<>();

        System.out.println("CADASTRO FINANCIAMENTOS");
        System.out.print("##############################\n");

        while(true) {
            boolean exit = false;

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

                    case "sair":
                        if(!mortgages.isEmpty() && user.getUserSaveOption()) {
                            updateDataBase(mortgages);
                            System.out.println("Financiamentos salvos com sucesso!");
                        }
                        return null;

                    default:
                        throw new Exception("Tipo de financiamento inválido!");
                }

            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            if(exit) break;
        }
        user.closeScanner();

        return mortgages;
    }

    private static String getResult(ArrayList<Mortgage> mortgages) {
        StringBuilder str = new StringBuilder();

        if(mortgages.isEmpty()) {
            str.append("Nenhum financiamento cadastrado!");
            return str.toString();
        }

        str.append("\n\n\nRESULTADO FINANCIAMENTOS\n")
                .append("##############################");

        for (var i = 0; i < mortgages.size(); i++) {
            Mortgage mortgage = mortgages.get(i);
            mortgage.updateTotalMortgageValues();

            str.append(String.format("\n\nFinanciamento #%d:\n", i + 1))
                    .append("==============================\n")
                    .append(mortgage.getMortgageInfo());
        }
        str.append(Mortgage.getTotalMortgageValues(mortgages.size()));

        return str.toString();
    }

    private static float getRandomNumber() {
        return (float) ((int)(Math.random() * 1000) + 1) / 1000;
    }
}