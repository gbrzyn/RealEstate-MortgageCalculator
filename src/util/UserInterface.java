package util;

import model.Apartment;
import model.House;
import model.Land;
import java.util.Scanner;

public class UserInterface {

    private boolean valid;
    private Scanner scan;

    public UserInterface(){
        this.scan = new Scanner(System.in);
    }

    public void closeScanner(){
        if(this.scan != null)
            this.scan.close();
    }

    public double getUserMortgageRealEstateValue(){
        double value;

        do{
            this.valid = true;

            System.out.print("\nDigite o valor do imóvel: R$");
            value = this.scan.nextDouble();

            if (value < 0){
                System.out.print("Valor inválido! Digite um valor positivo.\n");
                this.valid = false;
            }
        } while (!this.valid);

        return value;
    }

    public double getUserMortgageAnnualPercentageRate(){
        double rate;

        do{
            this.valid = true;

            System.out.print("\nDigite a taxa de juros (anual):");
            rate = this.scan.nextDouble();

            if (rate < 0){
                System.out.print("Taxa inválida! Digite uma taxa positiva.\n");
                this.valid = false;
            }
            else if (rate > 100){
                System.out.print("Não é permitido taxas maiores que 100% a.a.!\n");
                this.valid = false;
            }
        } while (valid);

        //  Convert to decimal
        return rate / 100;
    }

    public int getUserMortgageLoanTerm(){
        int term;

        do{
            this.valid = true;

            System.out.print("\nDigite o prazo de financiamento (anos):");
            term = this.scan.nextInt();

            if (term < 0){
                System.out.print("Prazo inválido! Digite um prazo positivo.\n");
                this.valid = false;
            }
            else if (term > 50){
                System.out.print("Não é permitido prazos maiores que 50 anos!\n");
                this.valid = false;
            }
        } while (!this.valid);

        return term;
    }

    public double getUserHouseBuiltArea(){
        double area;

        do{
            this.valid = true;

            System.out.print("\nDigite a área construida:");
            area = this.scan.nextDouble();

            if (area < 10){
                System.out.print("Área inválida! Digite uma acima de 10m².\n");
                this.valid = false;
            }
        } while (!this.valid);

        return area;
    }

    public double getUserHouseLandArea(){
        double area;

        do{
            this.valid = true;

            System.out.print("\nDigite a área do terreno:");
            area = this.scan.nextDouble();

            if (area < 20){
                System.out.print("Área inválida! Digite uma área acima de 20m².\n");
                this.valid = false;
            }
        } while (!this.valid);

        return area;
    }

    public int getUserApartmentParkingSpace(){
        int spaces;

        do{
            this.valid = true;

            System.out.print("\nDigite a quantidade de vagas de garagem:");
            spaces = this.scan.nextInt();

            if (spaces < 0){
                System.out.print("Valor inválido! O número de vagas não pode ser negativo.\n");
                this.valid = false;
            }
        } while (!this.valid);

        return spaces;
    }

    public int getUserApartmentFloor(){
        int floor;

        do{
            this.valid = true;

            System.out.print("\nDigite o andar do apartamento:");
            floor = this.scan.nextInt();

            if (floor < 0){
                System.out.print("Andar inválido! Digite andar acima de 0.\n");
                this.valid = false;
            }
            else if (floor > 100){
                System.out.print("Não é permitido prédios com mais de 100 andares!\n");
                this.valid = false;
            }

        } while (!this.valid);

        return floor;
    }

    public String getUserLandZoning(){
        String zone;

        do {
            this.valid = true;

            System.out.println("\nDigite a zona do terreno:");
            System.out.println("1 - Residencial");
            System.out.println("2 - Comercial");
            System.out.println("3 - Industrial");
            zone = this.scan.next();

            switch (zone) {
                case "1", "Residencial", "residencial" -> {
                    zone = "Residencial";
                    this.valid = true;
                }
                case "2", "Comercial", "comercial" -> {
                    zone = "Comercial";
                    this.valid = true;
                }
                case "3", "Industrial", "industrial" -> {
                    zone = "Industrial";
                    this.valid = true;
                }
                default -> {
                    System.out.print("Zona inválida! Digite uma zona válida.\n");
                    this.valid = false;
                }
            }
        } while (!this.valid);

        return zone;
    }

    public String getMortgageType(){
        String type;

        do {
        this.valid = true;

        System.out.println("Selecione o tipo de financiamento:");
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento:");
        System.out.println("3 - Terreno:");
        type = this.scan.next();

        switch (type) {
            case "1", "Casa", "casa" -> {
                type = "Casa";
                this.valid = true;
            }
            case "2", "Apartamento", "apartamento" -> {
                type = "Apartamento";
                this.valid = true;
            }
            case "3", "Terreno", "terreno" -> {
                type = "Terreno";
                this.valid = true;
            }
            default -> {
                System.out.print("Financiamento inválida! Digite um Financiamento válido.\n");
                this.valid = false;
            }
        }
    } while (!this.valid);

        return type;
    }

    public House getUserNewHouse(){
        double value = this.getUserMortgageRealEstateValue();
        double rate = this.getUserMortgageAnnualPercentageRate();
        int term = this.getUserMortgageLoanTerm();
        double builtArea = this.getUserHouseBuiltArea();
        double landArea = this.getUserHouseLandArea();

        return new House(value, rate, term, builtArea, landArea);
    }

    public Apartment getUserNewApartment(){
        double value = this.getUserMortgageRealEstateValue();
        double rate = this.getUserMortgageAnnualPercentageRate();
        int term = this.getUserMortgageLoanTerm();
        int parkingSpace = this.getUserApartmentParkingSpace();
        int floor = this.getUserApartmentFloor();

        return new Apartment(value, rate, term, parkingSpace, floor);
    }

    public Land getUserNewLand(){
        double value = this.getUserMortgageRealEstateValue();
        double rate = this.getUserMortgageAnnualPercentageRate();
        int term = this.getUserMortgageLoanTerm();
        String zoning = this.getUserLandZoning();

        return new Land(value, rate, term, zoning);
    }
}
