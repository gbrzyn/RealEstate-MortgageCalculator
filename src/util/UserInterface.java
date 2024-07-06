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

    public House getUserNewHouse(){
        this.setDefaultValues();

        while(true){
            try {
                if(value == -1)
                    value = this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = this.getUserMortgageAnnualPercentageRate(1, 100);

                if(discountRate == -1)
                    discountRate = this.getUserHouseDiscountRate(0, rate * 100);

                if(term == -1)
                    term = this.getUserMortgageLoanTerm(1, 50);

                if(builtArea == -1)
                    builtArea = this.getUserHouseBuiltArea(15);

                if(landArea == -1)
                    landArea = this.getUserHouseLandArea(builtArea);

                return new House(value, rate, discountRate, term, builtArea, landArea);

            } catch (IllegalArgumentException | DiscountRateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Apartment getUserNewApartment(){
        this.setDefaultValues();

        while(true){
            try {
                if(value == -1)
                    value = this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = this.getUserMortgageAnnualPercentageRate(1, 100);

                if(term == -1)
                    term = this.getUserMortgageLoanTerm(1, 50);

                if(parkingSpace == -1)
                    parkingSpace = this.getUserApartmentParkingSpace(15);

                if(floor == -1)
                    floor = this.getUserApartmentFloor(0, 100);

                return new Apartment(value, rate, term, parkingSpace, floor);

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Land getUserNewLand(){
        this.setDefaultValues();

        while(true){
            try {
                if(value == -1)
                    value = this.getUserMortgageRealEstateValue(1000);

                if(rate == -1)
                    rate = this.getUserMortgageAnnualPercentageRate(1, 100);

                if(term == -1)
                    term = this.getUserMortgageLoanTerm(1, 50);

                if(zoning == null)
                    zoning = this.getUserLandZoning();

                return new Land(value, rate, term, zoning);

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public double getUserMortgageRealEstateValue(double lowerLimit) throws IllegalArgumentException {
        System.out.print("\nDigite o valor do ativo: R$");
        double value = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(value, lowerLimit))
            throw new IllegalArgumentException("Valor inválido! O valor mínimo do ativo é R$" + lowerLimit + "0.");

        return value;
    }

    public double getUserMortgageAnnualPercentageRate(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("\nDigite a taxa de juros (anual): ");
        double rate = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(rate, lowerLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa mínima é " + lowerLimit + "% a.a.");

        else if (validateInputValueOverUpperLimit(rate, upperLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa máxima é " + upperLimit + "% a.a.");

        // Convert to decimal
        return rate / 100;
    }

    public int getUserMortgageLoanTerm(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("\nDigite o prazo de financiamento (anos): ");
        int term = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(term, lowerLimit))
            throw new IllegalArgumentException("Prazo inválido! O prazo mínimo é " + (int)lowerLimit + " anos.");

        else if (validateInputValueOverUpperLimit(term, upperLimit))
            throw new IllegalArgumentException("Prazo inválido! O prazo máximo é " + (int)upperLimit + " anos.");

        return term;
    }

    public double getUserHouseDiscountRate(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("\nDigite a taxa de desconto (anual): ");
        double rate = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(rate, lowerLimit))
            throw new IllegalArgumentException("Taxa inválida! A taxa mínima é " + lowerLimit + "% a.a.");

        else if (validateInputValueOverUpperLimit(rate, upperLimit))
            throw new IllegalArgumentException("Taxa inválida! O desconto não pode ser maior que o juros (" + upperLimit + "% a.a.)");

        // Convert to decimal
        return rate / 100;
    }

    public double getUserHouseBuiltArea(double lowerLimit) throws IllegalArgumentException {
        System.out.print("\nDigite a área construida: ");
        double area = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(area, lowerLimit))
            throw new IllegalArgumentException("Área inválida! Digite uma área acima de " + lowerLimit + "m².");

        return area;
    }

    public double getUserHouseLandArea(double lowerLimit) throws IllegalArgumentException {
        System.out.print("\nDigite a área do terreno: ");
        double area = this.scan.nextDouble();

        if (validateLowerLimitOverInputValue(area, lowerLimit))
            throw new IllegalArgumentException("Área inválida! A área do terreno precisa ser maior da área construida (" + lowerLimit + "m²)");

        return area;
    }

    public int getUserApartmentParkingSpace(double lowerLimit) throws IllegalArgumentException {
        System.out.print("\nDigite a quantidade de vagas de garagem: ");
        int spaces = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(spaces, lowerLimit))
            throw new IllegalArgumentException("Valor inválido! O número de vagas mínimo é " + lowerLimit + " vagas");

        return spaces;
    }

    public int getUserApartmentFloor(double lowerLimit, double upperLimit) throws IllegalArgumentException {
        System.out.print("\nDigite o andar do apartamento: ");
        int floor = this.scan.nextInt();

        if (validateLowerLimitOverInputValue(floor, lowerLimit))
            throw new IllegalArgumentException("Andar inválido! O andar mínimo é " + lowerLimit + "°.");

        else if (validateInputValueOverUpperLimit(floor, upperLimit))
            throw new IllegalArgumentException("Andar inválido! O andar máximo é " + upperLimit + "°.");

        return floor;
    }

    public String getUserLandZoning() throws IllegalArgumentException {
        System.out.println("\nDigite a zona do terreno:");
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

    public String getMortgageType() throws IllegalArgumentException {
        System.out.println("Selecione o tipo de financiamento:");
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento:");
        System.out.println("3 - Terreno:");

        return switch (this.scan.next()) {
            case "1", "Casa", "casa" -> "Casa";
            case "2", "Apartamento", "apartamento" -> "Apartamento";
            case "3", "Terreno", "terreno" -> "Terreno";
            default -> throw new IllegalArgumentException("Tipo de financiamento inválido!");
        };
    }

    private boolean validateLowerLimitOverInputValue(double value, double lowerLimit) {
        return lowerLimit > value;
    }

    private boolean validateInputValueOverUpperLimit(double value, double upperLimit) {
        return value > upperLimit;
    }

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
}