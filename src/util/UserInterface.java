package util;

import model.Apartment;
import model.House;
import model.Land;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scan;
    private String zoning;

    private double value;
    private double rate;
    private double discountRate;
    private double builtArea;
    private double landArea;

    private int term;
    private int parkingSpace;
    private int floor;

    public UserInterface(){ this.scan = new Scanner(System.in); }

    public void closeScanner(){
        this.scan.close();
    }

    public House getUserNewHouse(boolean random){
        this.setDefaultValues();

        String str = "\nFinanciamento de " +
                (random ? "Casa (Simulação)" : "Casa") +
                "\n==============================";
        System.out.println(str);

        while(true){
            try {
                if(value == -1)
                    value = random ? getRandomNumber(1, -1) * 1000 : this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = random ? getRandomNumber(1, 1000) / 1000 : this.getUserMortgageAnnualPercentageRate(1, 100); //To decimal

                if(discountRate == -1)
                    discountRate = random ? getRandomNumber(0, rate * 1000) / 1000 : this.getUserHouseDiscountRate(0, rate * 100); //To decimal

                if(term == -1)
                    term = random ? (int)getRandomNumber(1, 50) : this.getUserMortgageLoanTerm(1, 50);

                if(builtArea == -1)
                    builtArea = random ? (int)getRandomNumber(15, -1) : this.getUserHouseBuiltArea(15);

                if(landArea == -1)
                    landArea = random ? (int)getRandomNumber(builtArea, -1) : this.getUserHouseLandArea(builtArea);

                if (random){
                    str = "Valor do ativo: R$" + value +
                    "\nTaxa de juros (anual): " + (int)(rate * 1000) / 10 + "%" + //To percentage
                    "\nTaxa de desconto (anual): " + (int)(discountRate * 1000) / 10 + "%" + //To percentage
                    "\nPrazo de financiamento (anos): " + term +
                    "\nÁrea construida: " + builtArea + " m²" +
                    "\nÁrea do terreno: " + landArea + " m²\n";

                    System.out.println(str);
                }

                return new House(value, rate, discountRate, term, builtArea, landArea);

            } catch (IllegalArgumentException | DiscountRateException e){
                System.out.println(e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Valor inválido!\n");
                this.scan.nextLine();
            }
        }
    }

    public Apartment getUserNewApartment(boolean random){
        this.setDefaultValues();

        String str = "\nFinanciamento de " +
                (random ? "Apartamento (Simulação)" : "Apartamento") +
                "\n==============================";
        System.out.println(str);

        while(true){
            try {
                if(value == -1)
                    value = random ? getRandomNumber(1, -1) * 1000 : this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = random ? getRandomNumber(1, 1000) / 1000 : this.getUserMortgageAnnualPercentageRate(1, 100);

                if(term == -1)
                    term = random ? (int)getRandomNumber(1, 50) : this.getUserMortgageLoanTerm(1, 50);

                if(parkingSpace == -1)
                    parkingSpace = random ? (int)getRandomNumber(1, 20) : this.getUserApartmentParkingSpace(1);

                if(floor == -1)
                    floor = random ? (int)getRandomNumber(0, 100) : this.getUserApartmentFloor(0, 100);

                if (random){
                    str = "\nValor do ativo: R$" + value +
                    "\nTaxa de juros (anual): " + (int)(rate * 1000) / 10 + "%" + //To percentage
                    "\nPrazo de financiamento (anos): " + term +
                    "\nVagas de garagem: " + parkingSpace +
                    "\nAndar do apartamento: " + floor + "°\n";

                    System.out.println(str);
                }

                return new Apartment(value, rate, term, parkingSpace, floor);

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            } catch (InputMismatchException e){
                System.out.println("Valor inválido!\n");
                this.scan.nextLine();
            }
        }
    }

    public Land getUserNewLand(boolean random){
        this.setDefaultValues();

        String str = "\nFinanciamento de " +
                (random ? "Terreno (Simulação)" : "Terreno") +
                "\n==============================";
        System.out.println(str);

        while(true){
            try {
                if(value == -1)
                    value = random ? getRandomNumber(1, -1) * 1000 : this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = random ? getRandomNumber(1, 1000) / 1000 : this.getUserMortgageAnnualPercentageRate(1, 100);

                if(term == -1)
                    term = random ? (int)getRandomNumber(1, 50) : this.getUserMortgageLoanTerm(1, 50);

                if(zoning == null){
                    if(random){
                        zoning = switch ((int)getRandomNumber(100, 300) / 100) {
                            case 1 -> "Residencial";
                            case 2 -> "Comercial";
                            case 3 -> "Industrial";
                            default -> throw new IllegalArgumentException("Erro do Sistema! Zona inválida.\n");
                        };
                    }
                    else
                        zoning = this.getUserLandZoning();
                }

                if (random){
                    str = "\nValor do ativo: R$" + value +
                    "\nTaxa de juros (anual): " + (int)(rate * 1000) / 10 + "%" + //To percentage
                    "\nPrazo de financiamento (anos): " + term +
                    "\nZona do terreno: " + zoning + "\n";

                    System.out.println(str);
                }

                return new Land(value, rate, term, zoning);

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            } catch (InputMismatchException e){
                System.out.println("Valor inválido!\n");
                this.scan.nextLine();
            }
        }
    }

    public double getUserMortgageRealEstateValue(double lowerLimit) throws IllegalArgumentException {
        System.out.print("Digite o valor do ativo: R$");
        double value = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(value, lowerLimit))
            throw new IllegalArgumentException("Valor inválido! O valor mínimo do ativo é R$" + lowerLimit + "0.\n");

        return value;
    }

    public double getUserMortgageAnnualPercentageRate(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("Digite a taxa de juros (anual): ");
        double rate = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(rate, lowerLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa mínima é " + lowerLimit + "% a.a.\n");

        else if (validateInputValueOverUpperLimit(rate, upperLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa máxima é " + upperLimit + "% a.a.\n");

        // Convert to decimal
        return rate / 100;
    }

    public int getUserMortgageLoanTerm(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("Digite o prazo de financiamento (anos): ");
        int term = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(term, lowerLimit))
            throw new IllegalArgumentException("Prazo inválido! O prazo mínimo é " + (int)lowerLimit + " anos.\n");

        else if (validateInputValueOverUpperLimit(term, upperLimit))
            throw new IllegalArgumentException("Prazo inválido! O prazo máximo é " + (int)upperLimit + " anos.\n");

        return term;
    }

    public double getUserHouseDiscountRate(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("Digite a taxa de desconto (anual): ");
        double rate = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(rate, lowerLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa mínima é " + lowerLimit + "% a.a.\n");

        else if (validateInputValueOverUpperLimit(rate, upperLimit))
            throw new IllegalArgumentException("Taxa inválida! O desconto não pode ser maior que o juros (" + upperLimit + "% a.a.)\n");

        // Convert to decimal
        return rate / 100;
    }

    public double getUserHouseBuiltArea(double lowerLimit) throws IllegalArgumentException {
        System.out.print("Digite a área construida: ");
        double area = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(area, lowerLimit))
            throw new IllegalArgumentException("Área inválida! Digite uma área acima de " + lowerLimit + "m².\n");

        return area;
    }

    public double getUserHouseLandArea(double lowerLimit) throws IllegalArgumentException {
        System.out.print("Digite a área do terreno: ");
        double area = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(area, lowerLimit))
            throw new IllegalArgumentException("Área inválida! A área do terreno precisa ser maior da área construida (" + lowerLimit + "m²)\n");

        return area;
    }

    public int getUserApartmentParkingSpace(double lowerLimit) throws IllegalArgumentException {
        System.out.print("Digite a quantidade de vagas de garagem: ");
        int spaces = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(spaces, lowerLimit))
            throw new IllegalArgumentException("Valor inválido! O número de vagas mínimo é " + lowerLimit + " vagas\n");

        return spaces;
    }

    public int getUserApartmentFloor(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("Digite o andar do apartamento: ");
        int floor = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(floor, lowerLimit))
            throw new IllegalArgumentException("Andar inválido! O andar mínimo é " + lowerLimit + "°.\n");

        else if (validateInputValueOverUpperLimit(floor, upperLimit))
            throw new IllegalArgumentException("Andar inválido! O andar máximo é " + upperLimit + "°.\n");

        return floor;
    }

    public String getUserLandZoning() throws IllegalArgumentException {
        System.out.println("Digite a zona do terreno:");
        System.out.println("1 - Residencial");
        System.out.println("2 - Comercial");
        System.out.println("3 - Industrial");
        System.out.print("\nZona: ");

        return switch (this.scan.next()) {
            case "1", "Residencial", "residencial" -> "Residencial";
            case "2", "Comercial", "comercial" -> "Comercial";
            case "3", "Industrial", "industrial" -> "Industrial";
            default -> throw new IllegalArgumentException("Zona inválida! Digite uma zona válida.\n");
        };
    }

    public String getUserAction() throws IllegalArgumentException {
        System.out.println("\nSelecione a opção:");
        System.out.println("1 - Cadastro de financiamento (manual)");
        System.out.println("2 - Cadastro de financiamento (automatico)");
        System.out.println("3 - Mostrar resultados");
        System.out.println("4 - Sair");
        System.out.print("\nOpção: ");

        return switch (this.scan.next()) {
            case "1", "Manual", "manual" -> "manual";
            case "2", "Automatico", "automatico", "auto" -> "automatico";
            case "3", "Resultado", "resultado", "Resultados", "resultados" -> "resultado";
            case "4", "Sair", "sair", "esc", "exit" -> "sair";
            default -> throw new IllegalArgumentException("Opção inválida!\n");
        };
    }

    public String getUserMortgageType() throws IllegalArgumentException {
        System.out.println("\nSelecione o tipo de financiamento:");
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento");
        System.out.println("3 - Terreno");
        System.out.print("\nOpção: ");

        return switch (this.scan.next()) {
            case "1", "Casa", "casa" -> "casa";
            case "2", "Apartamento", "apartamento" -> "apartamento";
            case "3", "Terreno", "terreno" -> "terreno";
            default -> throw new IllegalArgumentException("Tipo de financiamento inválido!\n");
        };
    }

    public String getUserResultType() throws IllegalArgumentException {
        System.out.println("\nSelecione o tipo de resultado:");
        System.out.println("1 - Imprimir financiamentos cadastrados");
        System.out.println("2 - Imprimir financiamentos do arquivo");
        System.out.print("\nOpção: ");

        return switch (this.scan.next().toLowerCase()) {
            case "1", "cadastrados" -> "cadastrados";
            case "2", "arquivo" -> {
                System.out.println("\nAceitos arquivos de recibo (.txt), ou deixar em branco para acessar a base de dados!");
                yield getUserFileName();
            }
            default -> throw new IllegalArgumentException("Opção inválida!\n");
        };
    }

    public String getUserFileName() throws IllegalArgumentException {
        System.out.print("Digite o nome do arquivo: ");
        if (this.scan.hasNextLine())
            this.scan.nextLine();
        var fileName = this.scan.nextLine();

        if ((!fileName.isEmpty() || !fileName.isBlank()) && !fileName.endsWith(".txt"))
            throw new IllegalArgumentException("Nome do arquivo inválido! O arquivo deve ser .txt\n");

        return fileName;
    }

    public boolean getUserSaveOption() throws IllegalArgumentException {
        System.out.print("\nVocê possui financiamentos cadastrados, deseja salva-los? (S/N): ");

        return switch (this.scan.next()) {
            case "S", "s", "Sim", "sim" -> true;
            case "N", "n", "Não", "não", "nao" -> false;
            default -> throw new IllegalArgumentException("Opção inválida!\n");
        };
    }

    public boolean getUserReceiptOption() throws IllegalArgumentException {
        System.out.print("\nDeseja gerar recibo? (S/N): ");

        return switch (this.scan.next()) {
            case "S", "s", "Sim", "sim" -> true;
            case "N", "n", "Não", "não", "nao" -> false;
            default -> throw new IllegalArgumentException("Opção inválida!\n");
        };
    }

    private boolean validateLowerLimitOverInputValue(double value, double lowerLimit) { return lowerLimit > value; }

    private boolean validateInputValueOverUpperLimit(double value, double upperLimit) { return value > upperLimit; }

    private void setDefaultValues(){
        this.value = -1;
        this.rate = -1;
        this.discountRate = -1;
        this.term = -1;
        this.builtArea = -1;
        this.landArea = -1;
        this.parkingSpace = -1;
        this.floor = -1;
        this.zoning = null;
    }

    // Default values (not defined) = -1
    // Value range = [0, 1000]
    private static double getRandomNumber(double lowerLimit, double upperLimit) {
        if (upperLimit == -1)
            upperLimit = 1000;
        else if (upperLimit > 1000)
            throw new IllegalArgumentException("Erro do Sistema! Limite superior fora do range.\n");

        if (lowerLimit == -1)
            lowerLimit = 0;
        else if (lowerLimit < 0)
            throw new IllegalArgumentException("Erro do Sistema! Limite inferior fora do range.\n");

        if (lowerLimit >= upperLimit)
            throw new IllegalArgumentException("Erro do Sistema! Limite inferior >= limite superior.\n");

        double value;
        while(true) {
            value = (int)(Math.random() * 1000) + 1;

            if(value < lowerLimit)
                continue;
            else if (value > upperLimit)
                continue;

            return value;
        }
    }
}