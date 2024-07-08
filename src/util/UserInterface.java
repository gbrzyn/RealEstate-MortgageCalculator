package util;

import model.Apartment;
import model.House;
import model.Land;

import java.util.Scanner;

public class UserInterface {
    private Scanner scan;
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
        if(this.scan != null)
            this.scan.close();
    }

    public House getUserNewHouse(boolean simulation){
        this.setDefaultValues();

        String str = "\nFinanciamento de " +
                (simulation ? "Casa (Simulação)" : "Casa") +
                "\n==============================";
        System.out.println(str);

        while(true){
            try {
                if(value == -1)
                    value = simulation ? getRandomNumber(1, -1) * 1000 : this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = simulation ? getRandomNumber(1, 100) : this.getUserMortgageAnnualPercentageRate(1, 100);

                if(discountRate == -1)
                    discountRate = simulation ? getRandomNumber(0, rate) : this.getUserHouseDiscountRate(0, rate * 100);

                if(term == -1)
                    term = simulation ? (int)getRandomNumber(1, 50) : this.getUserMortgageLoanTerm(1, 50);

                if(builtArea == -1)
                    builtArea = simulation ? (int)getRandomNumber(15, -1) : this.getUserHouseBuiltArea(15);

                if(landArea == -1)
                    landArea = simulation ? (int)getRandomNumber(builtArea, -1) : this.getUserHouseLandArea(builtArea);

                return new House(value, rate, discountRate, term, builtArea, landArea);

            } catch (IllegalArgumentException | DiscountRateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Apartment getUserNewApartment(boolean simulation){
        this.setDefaultValues();

        String str = "\nFinanciamento de " +
                (simulation ? "Apartamento (Simulação)" : "Apartamento") +
                "\n==============================";
        System.out.println(str);

        while(true){
            try {
                if(value == -1)
                    value = simulation ? getRandomNumber(1, -1) * 1000 : this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = simulation ? getRandomNumber(1, 100) : this.getUserMortgageAnnualPercentageRate(1, 100);

                if(term == -1)
                    term = simulation ? (int)getRandomNumber(1, 50) : this.getUserMortgageLoanTerm(1, 50);

                if(parkingSpace == -1)
                    parkingSpace = simulation ? (int)getRandomNumber(1, -1) : this.getUserApartmentParkingSpace(1);

                if(floor == -1)
                    floor = simulation ? (int)getRandomNumber(0, 100) : this.getUserApartmentFloor(0, 100);

                return new Apartment(value, rate, term, parkingSpace, floor);

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Land getUserNewLand(boolean simulation){
        this.setDefaultValues();

        String str = "\nFinanciamento de " +
                (simulation ? "Terreno (Simulação)" : "Terreno") +
                "\n==============================";
        System.out.println(str);

        while(true){
            try {
                if(value == -1)
                    value = simulation ? getRandomNumber(1, -1) * 1000 : this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = simulation ? getRandomNumber(1, 100) : this.getUserMortgageAnnualPercentageRate(1, 100);

                if(term == -1)
                    term = simulation ? (int)getRandomNumber(1, 50) : this.getUserMortgageLoanTerm(1, 50);

                if(zoning == null){
                    if(simulation){
                        int random = (int)getRandomNumber(100, 300) / 100;
                        zoning = switch (random) {
                            case 1 -> "Residencial";
                            case 2 -> "Comercial";
                            case 3 -> "Industrial";
                            default -> throw new IllegalArgumentException("Erro do Sistema! Zona inválida.");
                        };
                    }
                    else
                        zoning = this.getUserLandZoning();
                }

                return new Land(value, rate, term, zoning);

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public double getUserMortgageRealEstateValue(double lowerLimit) throws IllegalArgumentException {
        System.out.print("Digite o valor do ativo: R$");
        double value = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(value, lowerLimit))
            throw new IllegalArgumentException("Valor inválido! O valor mínimo do ativo é R$" + lowerLimit + "0.");

        return value;
    }

    public double getUserMortgageAnnualPercentageRate(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("Digite a taxa de juros (anual): ");
        double rate = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(rate, lowerLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa mínima é " + lowerLimit + "% a.a.");

        else if (validateInputValueOverUpperLimit(rate, upperLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa máxima é " + upperLimit + "% a.a.");

        // Convert to decimal
        return rate / 100;
    }

    public int getUserMortgageLoanTerm(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("Digite o prazo de financiamento (anos): ");
        int term = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(term, lowerLimit))
            throw new IllegalArgumentException("Prazo inválido! O prazo mínimo é " + (int)lowerLimit + " anos.");

        else if (validateInputValueOverUpperLimit(term, upperLimit))
            throw new IllegalArgumentException("Prazo inválido! O prazo máximo é " + (int)upperLimit + " anos.");

        return term;
    }

    public double getUserHouseDiscountRate(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("Digite a taxa de desconto (anual): ");
        double rate = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(rate, lowerLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa mínima é " + lowerLimit + "% a.a.");

        else if (validateInputValueOverUpperLimit(rate, upperLimit))
            throw new IllegalArgumentException("Taxa inválida! O desconto não pode ser maior que o juros (" + upperLimit + "% a.a.)");

        // Convert to decimal
        return rate / 100;
    }

    public double getUserHouseBuiltArea(double lowerLimit) throws IllegalArgumentException {
        System.out.print("Digite a área construida: ");
        double area = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(area, lowerLimit))
            throw new IllegalArgumentException("Área inválida! Digite uma área acima de " + lowerLimit + "m².");

        return area;
    }

    public double getUserHouseLandArea(double lowerLimit) throws IllegalArgumentException {
        System.out.print("Digite a área do terreno: ");
        double area = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(area, lowerLimit))
            throw new IllegalArgumentException("Área inválida! A área do terreno precisa ser maior da área construida (" + lowerLimit + "m²)");

        return area;
    }

    public int getUserApartmentParkingSpace(double lowerLimit) throws IllegalArgumentException {
        System.out.print("Digite a quantidade de vagas de garagem: ");
        int spaces = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(spaces, lowerLimit))
            throw new IllegalArgumentException("Valor inválido! O número de vagas mínimo é " + lowerLimit + " vagas");

        return spaces;
    }

    public int getUserApartmentFloor(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("Digite o andar do apartamento: ");
        int floor = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(floor, lowerLimit))
            throw new IllegalArgumentException("Andar inválido! O andar mínimo é " + lowerLimit + "°.");

        else if (validateInputValueOverUpperLimit(floor, upperLimit))
            throw new IllegalArgumentException("Andar inválido! O andar máximo é " + upperLimit + "°.");

        return floor;
    }

    public String getUserLandZoning() throws IllegalArgumentException {
        System.out.println("Digite a zona do terreno:");
        System.out.println("1 - Residencial");
        System.out.println("2 - Comercial");
        System.out.println("3 - Industrial");

        return switch (this.scan.next()) {
            case "1", "Residencial", "residencial" -> "Residencial";
            case "2", "Comercial", "comercial" -> "Comercial";
            case "3", "Industrial", "industrial" -> "Industrial";
            default -> throw new IllegalArgumentException("Zona inválida! Digite uma zona válida.");
        };
    }

    public String getUserAction() throws IllegalArgumentException {
        System.out.println("\nSelecione a opção:");
        System.out.println("1 - Cadastro de financiamento");
        System.out.println("2 - Simulação de financiamento (aleatório)");
        System.out.println("3 - Resultado de financiamento");
        System.out.println("4 - Sair");
        System.out.print("Opção: ");

        return switch (this.scan.next()) {
            case "1", "Cadastro", "cadastro" -> getMortgageType(false);
            case "2", "Simulação", "simulação", "Simulacao", "simulacao" -> getMortgageType(true);
            case "3", "Resultado", "resultado", "Resultados", "resultados" -> "resultado";
            case "4", "Sair", "sair", "esc", "exit" -> "sair";
            default -> throw new IllegalArgumentException("Opção inválida!");
        };
    }

    public String getMortgageType(boolean simulation) throws IllegalArgumentException {
        System.out.println("\nSelecione o tipo de financiamento:");
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento");
        System.out.println("3 - Terreno");
        System.out.print("Opção: ");

        return switch (this.scan.next()) {
            case "1", "Casa", "casa" -> simulation ? "randomCasa" : "casa";
            case "2", "Apartamento", "apartamento" -> simulation ? "randomApartamento" : "apartamento";
            case "3", "Terreno", "terreno" -> simulation ? "randomTerreno" : "terreno";
            default -> throw new IllegalArgumentException("Tipo de financiamento inválido!");
        };
    }

    public String getResultType() throws IllegalArgumentException {
        System.out.println("\nOpção de resultado:");
        System.out.println("1 - Imprimir cadastrados");
        System.out.println("2 - Imprimir do recibo");
        System.out.print("\nOpção: ");

        return switch (this.scan.next()) {
            case "1", "Cadastrados", "cadastrados", "cadastro"  -> "cadastrados";
            case "2", "Recibo", "recibo" -> {
                System.out.print("\nDigite o nome do arquivo do recibo (.txt) ou base de dados (.dat) ");
                System.out.println("(Deixar vazio para arquivo padrão):\n");

                this.scan.nextLine();
                String fileName = this.scan.nextLine();

                if(fileName.isEmpty() || fileName.isBlank())
                    yield fileName;

                if(!fileName.endsWith(".txt") && !fileName.endsWith(".dat"))
                    throw new IllegalArgumentException("Nome de arquivo inválido! Digite um nome de arquivo válido.");

                yield fileName;
            }
            default -> throw new IllegalArgumentException("Tipo de resultado inválido!");
        };
    }

    public boolean getUserSaveOption() throws IllegalArgumentException {
        System.out.println("Deseja salvar financiamentos cadastrados? (S/N)");

        return switch (this.scan.next()) {
            case "S", "s", "Sim", "sim" -> true;
            case "N", "n", "Não", "não", "nao" -> false;
            default -> throw new IllegalArgumentException("Opção inválida!");
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
        if (upperLimit != -1 && upperLimit > 1000)
            throw new IllegalArgumentException("Erro do Sistema! Limite superior fora do range.");
        else if (lowerLimit != -1 && lowerLimit > 1000)
            throw new IllegalArgumentException("Erro do Sistema! Limite inferior fora do range.");
        else if (lowerLimit != -1 && upperLimit != -1 && (lowerLimit >= upperLimit))
            throw new IllegalArgumentException("Erro do Sistema! Limite inferior >= limite superior.");

        double value;

        while(true) {
            value = (int)(Math.random() * 1000) + 1;

            System.out.println(value);

            if(lowerLimit != -1 && value < lowerLimit)
                continue;
            else if (upperLimit != -1 && value > upperLimit)
                continue;

            return value;
        }
    }
}