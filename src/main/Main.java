// Gabriel Souza Michaliszyn | RA: 1112024101187
// Fundamentos da Programação Orientada a Objetos (11100010550_20241_21)

package main;

import model.Mortgage;

import util.FileInterface;
import util.UserInterface;
import java.util.ArrayList;

//TODO - Formatar texto das mensagens
//TODO - Adicionar opção no menu -> Cadastrar dados aleatórios

public class Main {

    private static final String dbFileName = "financiamentos.dat";
    private static final String receiptFileName = "recibo.txt";

    public static void main(String[] args) {
        var mortgages = getMortgages();

        if(mortgages != null) {
            var result = getResult(mortgages);

            FileInterface.updateReceipt(result, receiptFileName);
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
                String type = user.getUserAction();

                switch (type) {
                    case "randomCasa", "casa":
                        mortgages.add(user.getUserNewHouse(type.equals("randomCasa")));
                        break;

                    case "randomApartamento", "apartamento":
                        mortgages.add(user.getUserNewApartment(type.equals("randomApartamento")));
                        break;

                    case "randomTerreno", "terreno":
                        mortgages.add(user.getUserNewLand(type.equals("randomTerreno")));
                        break;

                    case "resultado":
                        while (true) {
                            try{
                                var fileName = user.getResultType();

                                if (fileName.equals("cadastrados") && !mortgages.isEmpty())
                                    updateDataBase(mortgages);

                                else {
                                    if(!mortgages.isEmpty() && user.getUserSaveOption()){
                                        updateDataBase(mortgages);
                                        System.out.println("Financiamentos salvos com sucesso!");
                                    }

                                    else if (fileName.isEmpty() || fileName.isBlank() || fileName.endsWith(".txt")) {
                                        mortgages.clear();
                                        mortgages.addAll(FileInterface.getManyMortgage(dbFileName));

                                        FileInterface.updateReceipt(getResult(mortgages), receiptFileName);
                                        System.out.println(FileInterface.getReceipt(fileName));

                                        return null;
                                    }

                                    else if (fileName.endsWith(".dat")) {
                                        mortgages.clear();
                                        mortgages.addAll(FileInterface.getManyMortgage(fileName));
                                    }
                                }
                                break;

                            } catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                        exit = true;
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

    private static void updateDataBase(ArrayList<Mortgage> mortgages){
        mortgages.addAll(0, FileInterface.getManyMortgage(dbFileName));
        FileInterface.updateManyMortgage(mortgages, dbFileName);
    }
}