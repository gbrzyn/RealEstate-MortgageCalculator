// Gabriel Souza Michaliszyn | RA: 1112024101187
// Fundamentos da Programação Orientada a Objetos (11100010550_20241_21)

package main;

import model.Mortgage;

import util.FileInterface;
import util.UserInterface;
import java.util.ArrayList;

public class Main {

    private static final String dbFileName = "financiamentos.dat";
    private static final String receiptFileName = "recibo.txt";

    public static void main(String[] args) {
        UserInterface user = new UserInterface();
        FileInterface fileInterface = new FileInterface(dbFileName);
        fileInterface.openDataBase();

        ArrayList<Mortgage> mortgages = new ArrayList<>();
        String result = "";

        // Main loop
        boolean exit = false;
        while (!exit) {
            try {
                exit = switch (user.getUserAction()) {
                    case "manual" -> {
                        getMortgages(user, mortgages, false);
                        yield false;
                    }

                    case "automatico" -> {
                        getMortgages(user, mortgages, true);
                        yield false;
                    }

                    case "resultado" -> {
                        while (true) {
                            try {
                                var action = user.getUserResultType();

                                if (!mortgages.isEmpty() && user.getUserSaveOption()) {
                                    mortgages.addAll(fileInterface.getManyMortgage());
                                    fileInterface.updateManyMortgage(mortgages);

                                    System.out.println("\nSalvando financiamentos...");
                                    System.out.println("Financiamentos salvos com sucesso!\n");
                                }

                                if (action.equals("cadastrados"))
                                    result = getResult(mortgages);

                                else if (action.isEmpty() || action.isBlank())
                                        result = getResult(fileInterface.getManyMortgage());

                                else if (action.endsWith(".txt"))
                                    result = fileInterface.getReceipt(action);


                                break;

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        yield true;
                    }

                    case "sair" -> {
                        if (!mortgages.isEmpty() && user.getUserSaveOption()) {
                            mortgages.addAll(fileInterface.getManyMortgage());
                            fileInterface.updateManyMortgage(mortgages);

                            System.out.println("\nSalvando financiamentos...");
                            System.out.println("Financiamentos salvos com sucesso!\n");
                        }
                        yield true;
                    }

                    default -> throw new IllegalStateException("Unexpected value -> " + user.getUserAction() + "\n");
                };

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Print result
        System.out.println(result);

        // Generate receipt
        if (!mortgages.isEmpty() && !result.isEmpty() && user.getUserReceiptOption()) {
            while (true) {
                try {
                    System.out.println("Arquivo de recibo .txt (ou deixe em branco para sobrescrever)");
                    var fileName = user.getUserFileName();

                    if (fileName.isEmpty() || fileName.isBlank())
                        fileInterface.updateReceipt(receiptFileName, result);
                    else
                        fileInterface.updateReceipt(fileName, result);

                    System.out.println("\nGerando recibo...");
                    System.out.println("Recibo gerado com sucesso!\n");

                    break;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        user.closeScanner();

        System.out.println("Finalizando Programa...");
    }


    private static void getMortgages(UserInterface user, ArrayList<Mortgage> mortgages, boolean random) {
        System.out.println("\nCADASTRO FINANCIAMENTOS");
        System.out.print("##############################\n");

        while(true) {
            try{
                switch (user.getUserMortgageType()) {
                    case "casa" -> mortgages.add(user.getUserNewHouse(random));
                    case "apartamento" -> mortgages.add(user.getUserNewApartment(random));
                    case "terreno" -> mortgages.add(user.getUserNewLand(random));
                } break;

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static String getResult(ArrayList<Mortgage> mortgages) {
        StringBuilder str = new StringBuilder();

        if(mortgages.isEmpty()) {
            str.append("\nNenhum financiamento cadastrado!\n");
            return str.toString();
        }

        str.append("\n\nRESULTADO FINANCIAMENTOS\n")
                .append("##############################\n");

        for (var i = 0; i < mortgages.size(); i++) {
            Mortgage mortgage = mortgages.get(i);
            mortgage.updateTotalMortgageValues();

            str.append(String.format("\nFinanciamento #%d:\n", i + 1))
                    .append("==============================\n")
                    .append(mortgage.getMortgageInfo())
                    .append("==============================\n\n");
        }
        str.append(Mortgage.getTotalMortgageValues(mortgages.size()));

        return str.toString();
    }
}